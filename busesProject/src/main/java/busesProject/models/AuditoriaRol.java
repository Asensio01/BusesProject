package busesProject.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auditoria_roles")
public class AuditoriaRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioAscendido;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Usuario administrador;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public AuditoriaRol() {}

    public AuditoriaRol(Usuario usuarioAscendido, Usuario administrador, LocalDateTime fecha) {
        this.usuarioAscendido = usuarioAscendido;
        this.administrador = administrador;
        this.fecha = fecha;
    }
}
