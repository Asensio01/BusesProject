package busesProject.repositories;

import busesProject.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {


    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);


}
