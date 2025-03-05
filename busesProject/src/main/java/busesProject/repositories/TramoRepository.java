package busesProject.repositories;

import busesProject.models.Ciudad;
import busesProject.models.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface TramoRepository extends JpaRepository<Tramo, Integer> {


    @Query("SELECT COUNT(t) > 0 FROM Tramo t WHERE t.ciudadOrigen.idCiudad = :idCiudadOrigen " +
            "AND t.ciudadDestino.idCiudad = :idCiudadDestino " +
            "AND t.horaSalida = :horaSalida " +
            "AND t.duracion = :duracion")
    boolean existsByCiudadesAndHorario(@Param("idCiudadOrigen") Integer idCiudadOrigen,
                                       @Param("idCiudadDestino") Integer idCiudadDestino,
                                       @Param("horaSalida") String horaSalida,
                                       @Param("duracion") Integer duracion);

}