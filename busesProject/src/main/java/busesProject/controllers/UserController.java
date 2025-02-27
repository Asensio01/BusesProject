package busesProject.controllers;

import busesProject.Services.UserService;
import busesProject.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> authenticatedUser() {
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> allUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    // ✅ Endpoint para ascender a ADMIN a un usuario por su ID
    @PutMapping("/{idUsuario}/promote")
    public ResponseEntity<?> promoteUser(@PathVariable Long idUsuario) {
        boolean promoted = userService.promoteUser(idUsuario);
        if (promoted) {
            return ResponseEntity.ok("{\"message\": \"Usuario ascendido correctamente a ADMIN.\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"error\": \"No se pudo ascender el usuario.\"}");
        }
    }
}
