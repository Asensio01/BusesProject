package busesProject.controllers;


import busesProject.Responses.LoginResponse;
import busesProject.Services.AuthService;
import busesProject.Services.JwtService;
import busesProject.dtos.UserLogin;
import busesProject.dtos.UserRegister;
import busesProject.dtos.VerifyUserDto;
import busesProject.exceptions.DuplicateEmailException;
import busesProject.exceptions.DuplicateUsernameException;
import busesProject.models.Usuario;
import busesProject.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.usuarioRepository = usuarioRepository; // ✅ Inicializarlo
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody UserRegister registerUserDto) {
        try {
            Usuario registeredUser = authenticationService.signup(registerUserDto);
            return ResponseEntity.ok(registeredUser);
        }catch (DuplicateEmailException e) {
            return ResponseEntity.badRequest().body("{\"email\": \"" + e.getMessage() + "\"}");
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.badRequest().body("{\"username\": \"" + e.getMessage() + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + "Error desconocido en el registro" + "\"}");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLogin loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, 0));
        }

        String jwtToken = jwtService.generateToken(authenticatedUser);
        LocalDateTime expirationTime = jwtService.getExpirationDate(jwtToken);

        // ✅ Guardar el token en la base de datos
        authenticatedUser.setAuthToken(jwtToken);
        authenticatedUser.setAuthTokenExpiration(expirationTime);
        usuarioRepository.save(authenticatedUser);

        return ResponseEntity.ok(new LoginResponse(jwtToken, expirationTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli()));
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("{\"message\": \"Account verified successfully\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("{\"error\": \"El email es obligatorio.\"}");
        }

        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("{\"message\": \"Verification code sent\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            authenticationService.sendPasswordResetEmail(email);
            return ResponseEntity.ok("{\"message\": \"Se ha enviado un correo con instrucciones para restablecer la contraseña.\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        try {
            authenticationService.resetPassword(token, newPassword);
            return ResponseEntity.ok("{\"message\": \"Contraseña restablecida con éxito.\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }


    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Optional<Usuario> optionalUser = usuarioRepository.findByAuthToken(token);

        if (optionalUser.isEmpty() || optionalUser.get().getAuthTokenExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado.");
        }

        Usuario user = optionalUser.get();
        return ResponseEntity.ok(Map.of(
                "nombre", user.getNombre(),
                "rol", user.getRol().toString()
        ));
    }




}