package busesProject.repositories;

import busesProject.models.Autobus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutobusRepository extends JpaRepository<Autobus,Integer> {
}
