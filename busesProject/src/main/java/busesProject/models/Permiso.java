package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permisos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermiso;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
}
