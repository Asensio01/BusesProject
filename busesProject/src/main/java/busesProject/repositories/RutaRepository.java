package busesProject.repositories;

import busesProject.models.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    boolean existsByNombreRuta(String nombreRuta);
}
