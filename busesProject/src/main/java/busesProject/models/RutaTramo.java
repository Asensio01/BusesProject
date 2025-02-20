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
  @JoinColumn(name = "id_ruta")
  private Ruta ruta;

  @ManyToOne
  @MapsId("idTramo")
  @JoinColumn(name = "id_tramo")
  private Tramo tramo;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_viaje",insertable=false, updatable=false)
  private TipoViaje tipoViaje;
}
