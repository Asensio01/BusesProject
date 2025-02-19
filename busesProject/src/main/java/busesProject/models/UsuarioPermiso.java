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
  @MapsId("id_usuario")
  private Usuario usuario;
  @ManyToOne
  @MapsId("id_permiso")
  private Permiso permiso;
}
