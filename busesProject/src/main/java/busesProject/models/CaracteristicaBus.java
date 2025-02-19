package busesProject.models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "caracteristicas_bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CaracteristicaBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_caracteristica;
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
}
