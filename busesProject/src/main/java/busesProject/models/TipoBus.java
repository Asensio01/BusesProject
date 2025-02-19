package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo;
    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
    @Column(nullable = false)
    private Integer capacidad_maxima;
}
