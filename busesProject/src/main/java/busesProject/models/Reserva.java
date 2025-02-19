package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reserva;
    @JoinColumn(name="id_usuario", nullable = false)
    private Usuario usuario;
    @Column(length = 50)
    private String localizador;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_emision;
    @Column(length = 100)
    private String ciudad_origen;
    @Column(length = 100)
    private String ciudad_destino;
}
