package busesProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios_permisos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermiso {
  @EmbeddedId
  private UsuarioPermisoId id;

  @ManyToOne
  @JoinColumn(name = "id_usuario")
  @MapsId("idUsuario")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "id_permiso")
  @MapsId("idPermiso")
  private Permiso permiso;
}
