package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

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
  @MapsId("id_usuario")
  private Usuario usuario;
  @ManyToOne
  @MapsId("id_permiso")
  private Permiso permiso;
}
