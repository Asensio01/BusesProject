package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "tramos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tramo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_tramo;
  @JoinColumn(name = "id_ciudad_origen", nullable = false)
  private Ciudad ciudad_origen;
  @JoinColumn(name = "id_ciudad_destino", nullable = false)
  private Ciudad ciudad_destino;
  @Column(nullable = false)
  private LocalTime hora_salida;
  @Column(nullable = false)
  private Integer duracion;
}
