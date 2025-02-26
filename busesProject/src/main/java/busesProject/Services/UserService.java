package busesProject.Services;

import busesProject.models.Usuario;
import busesProject.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UsuarioRepository userRepository;
    public UserService(UsuarioRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
    }

    public List<Usuario> allUsers() {
        List<Usuario> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}



