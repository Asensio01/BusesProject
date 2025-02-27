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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authenticationService;

    public AuthController(JwtService jwtService, AuthService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
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
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLogin loginUserDto){
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
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


}