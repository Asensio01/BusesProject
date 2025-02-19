package busesProject.models;

import busesProject.enums.TipoViaje;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @MapsId("tipoViaje")
  @Column(name = "tipo_viaje")
  private TipoViaje tipoViaje;
}
