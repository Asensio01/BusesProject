package busesProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class AutobusCaracteristicaId implements Serializable {

  @Column(name = "id_autobus")
  private Integer idAutobus;
  @Column(name = "id_caracteristica")
  private Integer idCaracteristica;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AutobusCaracteristicaId that = (AutobusCaracteristicaId) o;
    return Objects.equals(idAutobus, that.idAutobus) &&
            Objects.equals(idCaracteristica, that.idCaracteristica);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idAutobus, idCaracteristica);
  }
}
