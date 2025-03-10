package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios_permisos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_permiso"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermiso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario_permiso")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_permiso")
  private Permiso permiso;
}
