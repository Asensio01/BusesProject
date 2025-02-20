package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_bus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Long idTipo;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "capacidad_maxima",nullable = false)
    private Integer capacidadMaxima;

    @Column(nullable = false)
    private Integer filas;

    @Column(name = "asientos_por_fila",nullable = false)
    private Integer asientosPorFila;
}
