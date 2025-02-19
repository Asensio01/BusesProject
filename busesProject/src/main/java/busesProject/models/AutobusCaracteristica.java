package busesProject.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autobuses_caracteristicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AutobusCaracteristica {
  @EmbeddedId
  private AutobusCaracteristicaId id;
  @ManyToOne
  @MapsId("id_autobus")
  private Autobus autobus;
  @ManyToOne
  @MapsId("id_caracteristica")
  private CaracteristicaBus caracteristicaBus;
}
