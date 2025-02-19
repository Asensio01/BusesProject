package busesProject.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @MapsId("idAutobus")
  private Autobus autobus;
  @ManyToOne
  @MapsId("idCaracteristica")
  private CaracteristicaBus caracteristicaBus;
}
