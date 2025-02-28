package busesProject.repositories;

import busesProject.models.RutaTramo;
import busesProject.models.RutaTramoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RutaTramoRepository extends JpaRepository<RutaTramo, RutaTramoId> {

    @Transactional
    @Modifying
    @Query("DELETE FROM RutaTramo rt WHERE rt.id.idRuta = :idRuta")
    void deleteByRutaId(int idRuta);
}
