package busesProject.Services;

import busesProject.enums.Rol;
import busesProject.models.AuditoriaRol;
import busesProject.models.Usuario;
import busesProject.repositories.AuditoriaRolRepository;
import busesProject.repositories.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final AuditoriaRolRepository auditoriaRolRepository;

    public UserService(UsuarioRepository usuarioRepository, AuditoriaRolRepository auditoriaRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.auditoriaRolRepository = auditoriaRolRepository;
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();
        usuarioRepository.findAll().forEach(users::add);
        return users;
    }

    // ✅ Obtener el usuario autenticado actualmente
    public Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("No hay un usuario autenticado.");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof Usuario) {
            return (Usuario) principal; // ✅ Si el principal es un Usuario, hacer el cast
        } else if (principal instanceof String) {
            // ✅ Buscar al usuario en la base de datos usando el email
            return usuarioRepository.findByEmail((String) principal)
                    .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado."));
        } else {
            throw new SecurityException("Tipo de autenticación desconocida.");
        }
    }


    public boolean promoteUser(Long idUsuario, String token) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("Usuario a ascender no encontrado.");
        }

        Usuario usuario = optionalUsuario.get();
        if (usuario.getRol().equals(Rol.ADMIN)) {
            throw new RuntimeException("El usuario ya es administrador.");
        }

        // ✅ Buscar el usuario autenticado en la base de datos usando `findByAuthToken`
        Usuario admin = usuarioRepository.findByAuthToken(token)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado con el token proporcionado."));

        if (!admin.getRol().equals(Rol.ADMIN)) {
            throw new SecurityException("No tienes permisos para ascender usuarios.");
        }

        // ✅ Ascender al usuario y guardar los cambios
        usuario.setRol(Rol.ADMIN);
        usuarioRepository.save(usuario);

        // ✅ Registrar en la tabla de auditoría
        AuditoriaRol auditoria = new AuditoriaRol(usuario, admin, LocalDateTime.now());
        auditoriaRolRepository.save(auditoria);

        return true;
    }


}



