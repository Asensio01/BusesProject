package busesProject.models;

import busesProject.enums.TipoViaje;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rutas_tramos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaTramo {
  @EmbeddedId
  private RutaTramoId id;
  @ManyToOne
  @MapsId("idRuta")
  private Ruta ruta;
  @ManyToOne
  @MapsId("idTramo")
  private Tramo tramo;
  @Enumerated(EnumType.STRING)
  private TipoViaje tipoViaje;
}
