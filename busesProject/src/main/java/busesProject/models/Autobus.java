package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "autobuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autobus")
    private Long idAutobus;

    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_tipo",nullable = false)
    private TipoBus tipo;
}
