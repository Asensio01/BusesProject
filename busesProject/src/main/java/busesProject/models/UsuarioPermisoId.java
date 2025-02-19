package busesProject.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermisoId implements Serializable {

  @Column(name = "id_usuario")
  private Integer idUsuario;

  @Column(name = "id_permiso")
  private Integer idPermiso;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsuarioPermisoId that = (UsuarioPermisoId) o;
    return Objects.equals(idUsuario, that.idUsuario) &&
            Objects.equals(idPermiso, that.idPermiso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idUsuario, idPermiso);
  }
}
