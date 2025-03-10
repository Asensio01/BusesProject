package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autobuses_caracteristicas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_autobus", "id_caracteristica"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutobusCaracteristica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_autobus", nullable = false)
  private Autobus autobus;

  @ManyToOne
  @JoinColumn(name = "id_caracteristica", nullable = false)
  private CaracteristicaBus caracteristicaBus;
}
