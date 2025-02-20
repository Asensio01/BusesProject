package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "caracteristicas_bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaracteristicaBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caracteristica")
    private Long idCaracteristica;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
}
