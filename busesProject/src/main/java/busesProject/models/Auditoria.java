package busesProject.models;

import busesProject.enums.Accion;
import busesProject.enums.TablaModificada;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "auditorias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TablaModificada tabla_modificada;

    @Column(nullable = false)
    private Integer id_registro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date fecha = new Date();
}
