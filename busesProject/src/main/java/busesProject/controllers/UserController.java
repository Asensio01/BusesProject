package busesProject.controllers;

import busesProject.Services.UserService;
import busesProject.models.AuditoriaRol;
import busesProject.models.Usuario;
import busesProject.repositories.AuditoriaRolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final AuditoriaRolRepository auditoriaRolRepository;

    public UserController(UserService userService, AuditoriaRolRepository auditoriaRolRepository) {
        this.userService = userService;
        this.auditoriaRolRepository = auditoriaRolRepository;
    }
    @GetMapping("/me")
    public ResponseEntity<Usuario> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> allUsers() {
        List <Usuario> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{idUsuario}/promote")
    public ResponseEntity<?> promoteUser(
            @PathVariable Long idUsuario,
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", ""); // ✅ Extraer el token del header
            boolean success = userService.promoteUser(idUsuario, token);

            if (success) {
                return ResponseEntity.ok("{\"message\": \"Usuario ascendido exitosamente\"}");
            } else {
                return ResponseEntity.badRequest().body("{\"error\": \"No se pudo ascender al usuario\"}");
            }
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error interno\"}");
        }
    }


    @GetMapping("/auditoria")
    public ResponseEntity<List<AuditoriaRol>> obtenerAuditorias() {
        return ResponseEntity.ok(auditoriaRolRepository.findAll());
    }
}