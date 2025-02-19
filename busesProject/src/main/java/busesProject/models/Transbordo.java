package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios_permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transbordo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_transbordo")
  private Integer idTransbordo;
  @ManyToOne
  @JoinColumn(name = "id_reserva",nullable = false)
  private Reserva reserva;
  @ManyToOne
  @JoinColumn(name = "idTramo",nullable = false)
  private Tramo tramo;
  @ManyToOne
  @JoinColumn(name = "idAutobus",nullable = false)
  private Autobus autobus;
  @Column(name = "numero_fila", nullable = false)
  private Integer numeroFila;
  @Column(name = "letra_asiento", nullable = false)
  private Character letraAsiento;
}
