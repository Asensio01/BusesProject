package busesProject.Services;

import busesProject.models.Usuario;
import busesProject.repositories.UsuarioRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
@NoArgsConstructor
@Service
public class UserService  implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().name()); // Corregido

        return new org.springframework.security.core.userdetails.User(
                user.getNombre(),
                user.getPasswordHash(),
                Collections.singletonList(authority)
        );
    }

    public boolean existsByUserName(String username){
        return usuarioRepository.existsByNombre(username);
    }

    public void save(Usuario user){
        usuarioRepository.save(user);
    }
}
