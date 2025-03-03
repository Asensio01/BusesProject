package busesProject.Services;
import busesProject.dtos.UserLogin;
import busesProject.dtos.UserRegister;
import busesProject.dtos.VerifyUserDto;
import busesProject.exceptions.DuplicateEmailException;
import busesProject.exceptions.DuplicateUsernameException;
import busesProject.models.Usuario;
import busesProject.repositories.UsuarioRepository;
import jakarta.mail.MessagingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class AuthService {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    public AuthService(
            UsuarioRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            EmailService emailService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public Usuario signup(UserRegister input) {
        Usuario user = new Usuario(
                input.getNombre(),
                input.getApellido(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()),
                input.getTelefono(),
                input.getRol(),
                input.getUsername()
        );
        try {
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationExpiration(LocalDateTime.now().plusMinutes(15));
            user.setEnabled(false);
            Usuario data = userRepository.save(user);

            sendVerificationEmail(user);
            return data;
        }catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();

            // Verificar si el mensaje contiene el email o el username ingresado
            if (errorMessage.contains(input.getEmail())) {
                throw new DuplicateEmailException("Ya existe una cuenta con ese email.");
            } else if (errorMessage.contains(input.getUsername())) {
                throw new DuplicateUsernameException("Ya existe una cuenta con ese usuario.");
            }

            throw new RuntimeException("Error desconocido al registrar usuario.");
        }
    }

    public Usuario authenticate(UserLogin input) {
        Usuario user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Error: Usuario no encontrado."));

        if (!user.isEnabled()) {
            throw new RuntimeException("Error: Cuenta no verificada. Por favor, revisa tu correo electrónico.");
        }

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Error: Credenciales inválidas. Verifica tu correo y contraseña.");
        }

        return user;
    }



    public void verifyUser(VerifyUserDto input) {
        Optional<Usuario> optionalUser = userRepository.findByEmail(input.getEmail());
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.getVerificationExpiration().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationExpiration(null);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void resendVerificationCode(String email) {
        Optional<Usuario> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.isEnabled()) {
                throw new RuntimeException("Account is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationExpiration(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private void sendVerificationEmail(Usuario user) { //TODO: Update with company logo
        String subject = "Verificación de cuenta";
        String verificationCode = "Codigo de Verificación  " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Bienvenido a Buses App!</h2>"
                + "<p style=\"font-size: 16px;\">Por favor ingrese el código de verificación a continuación para continuar:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }
    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    public void sendPasswordResetEmail(String email) {
        Optional<Usuario> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(30));
            userRepository.save(user);

            String resetLink = "http://localhost:5173/reset-password?token=" + token;
            String subject = "Restablecimiento de Contraseña";
            String message = "<html><body>"
                    + "<h2>Solicitud de Restablecimiento de Contraseña</h2>"
                    + "<p>Hemos recibido una solicitud para restablecer tu contraseña. Si no hiciste esta solicitud, ignora este mensaje.</p>"
                    + "<p>Haz clic en el siguiente enlace para restablecer tu contraseña:</p>"
                    + "<a href='" + resetLink + "'>Restablecer Contraseña</a>"
                    + "<p>Este enlace expirará en 30 minutos.</p>"
                    + "</body></html>";

            try {
                emailService.sendVerificationEmail(user.getEmail(), subject, message);
            } catch (MessagingException e) {
                throw new RuntimeException("Error al enviar el correo de restablecimiento de contraseña.");
            }
        } else {
            throw new RuntimeException("No se encontró una cuenta con este correo.");
        }
    }

    public void resetPassword(String token, String newPassword) {
        Optional<Usuario> optionalUser = userRepository.findByResetToken(token);
        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            if (user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("El enlace de restablecimiento ha expirado.");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            user.setResetTokenExpiration(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Token inválido.");
        }
    }
}
