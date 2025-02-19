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
public class Ciudad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id_ciudad;
  @Column(nullable = false, length = 100)
  private String nombre;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Departamento departamento;
}
