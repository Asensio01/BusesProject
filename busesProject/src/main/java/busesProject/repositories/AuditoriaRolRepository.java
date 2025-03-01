package busesProject.repositories;

import busesProject.models.AuditoriaRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRolRepository extends JpaRepository<AuditoriaRol, Long> {
}
