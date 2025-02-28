package busesProject.repositories;

import busesProject.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByResetToken(String token);
  Optional<Usuario> findByAuthToken(String authToken);


}
