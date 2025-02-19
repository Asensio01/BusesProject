package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autobuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_autobus;
    @Column(nullable = false, unique = true, length = 20)
    private String placa;
    @JoinColumn(name = "id_tipo",nullable = false)
    private TipoBus tipo;
}
