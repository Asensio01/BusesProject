package busesProject.repositories;

import busesProject.models.TipoBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoBusRepository extends JpaRepository<TipoBus,Integer> {
}
