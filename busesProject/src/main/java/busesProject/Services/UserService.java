package busesProject.Services;

import busesProject.enums.Rol; // Importar el enum de roles
import busesProject.models.Usuario;
import busesProject.repositories.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UsuarioRepository userRepository;

    public UserService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // ✅ Obtener el usuario autenticado actualmente
    public Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    // ✅ Método para ascender un usuario a ADMIN usando el enum
    public boolean promoteUser(Long idUsuario) {
        Optional<Usuario> optionalUsuario = userRepository.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (!usuario.getRol().equals(Rol.ADMIN)) { // Verificar si ya es admin
                usuario.setRol(Rol.ADMIN); // ✅ Usar el ENUM en lugar de un String
                userRepository.save(usuario);
                return true;
            }
        }
        return false; // No se pudo ascender porque no existía o ya era admin
    }
}
