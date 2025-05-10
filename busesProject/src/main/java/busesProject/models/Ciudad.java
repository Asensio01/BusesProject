package busesProject.models;

import busesProject.enums.Departamento;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ciudades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ciudad {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ciudad")
  private Integer idCiudad; // Cambiado a Integer para reflejar un INT en la BD

  @Column(nullable = false, length = 100)
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Departamento departamento;
}