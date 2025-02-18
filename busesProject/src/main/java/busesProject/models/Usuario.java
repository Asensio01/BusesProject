package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name= "usuarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column(length = 15)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Rol rol;

    @Column(name = "fecha_ascenso_admin")
    @Temporal(TemporalType.DATE)
    private Date fechaAscensoAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_permisos",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private Set<Permiso> permisos;
}
