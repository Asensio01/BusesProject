package busesProject.models;

import busesProject.enums.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ciudades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ciudad")
  private Integer idCiudad;
  @Column(nullable = false, length = 100)
  private String nombre;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Departamento departamento;
}
