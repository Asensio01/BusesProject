package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  @Column(name = "id_tramo")
  private Long idTramo;

  @ManyToOne
  @JoinColumn(name = "id_ciudad_origen", nullable = false)
  private Ciudad ciudadOrigen;

  @ManyToOne
  @JoinColumn(name = "id_ciudad_destino", nullable = false)
  private Ciudad ciudadDestino;

  @Column(name = "hora_salida",nullable = false)
  private LocalTime horaSalida;

  @Column(nullable = false)
  private Integer duracion;
}
