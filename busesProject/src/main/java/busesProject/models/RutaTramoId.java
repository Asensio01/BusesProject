package busesProject.models;

import busesProject.enums.TipoViaje;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RutaTramoId implements Serializable{

  @Column(name = "id_ruta")
  private Long idRuta;
  @Column(name = "id_tramo")
  private Long idTramo;
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