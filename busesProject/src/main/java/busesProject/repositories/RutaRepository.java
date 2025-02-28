package busesProject.repositories;

import busesProject.models.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    Optional<Ruta> findByNombreRuta(String nombreRuta);
    boolean existsByNombreRuta(String nombreRuta);
}
