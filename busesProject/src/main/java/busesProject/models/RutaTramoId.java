package busesProject.models;

import busesProject.enums.TipoViaje;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RutaTramoId implements Serializable{
  @Column(name = "id_ruta")
  private Integer idRuta;

  @Column(name = "id_tramo")
  private Integer idTramo;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_viaje")
  private TipoViaje tipoViaje;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RutaTramoId that = (RutaTramoId) o;
    return Objects.equals(idRuta, that.idRuta) &&
            Objects.equals(idTramo, that.idTramo) &&
            tipoViaje == that.tipoViaje;
  }

  @Override
  public int hashCode() {
    return Objects.hash(idRuta, idTramo, tipoViaje);
  }

}
