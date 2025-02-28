package busesProject.repositories;

import busesProject.models.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
    List<Ciudad> findByDepartamento(String departamento);
}
