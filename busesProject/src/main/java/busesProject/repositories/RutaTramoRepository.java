package busesProject.repositories;

import busesProject.enums.TipoViaje;
import busesProject.models.Ruta;
import busesProject.models.RutaTramo;
import busesProject.models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaTramoRepository extends JpaRepository<RutaTramo, Integer> {
  boolean existsByRutaAndTramoAndTipoViaje(Ruta ruta, Tramo tramo, TipoViaje tipoViaje);
}