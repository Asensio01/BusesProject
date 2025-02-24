package busesProject.Services;

import busesProject.dtos.UserRegister;
import busesProject.jwt.JwtUtil;
import busesProject.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtUtil.generateToken(authentication);
    }

    public void registerUser(UserRegister newUserDto) {
        if (userService.existsByUserName(newUserDto.getNombre())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        // Verifica que la contraseña no sea nula
        if (newUserDto.getPassword() == null || newUserDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        Usuario usuario = new Usuario(
                newUserDto.getNombre(),
                newUserDto.getApellido(),
                newUserDto.getEmail(),
                passwordEncoder.encode(newUserDto.getPassword()),
                newUserDto.getTelefono(),
                newUserDto.getRol()
        );

        userService.save(usuario);
    }


}
