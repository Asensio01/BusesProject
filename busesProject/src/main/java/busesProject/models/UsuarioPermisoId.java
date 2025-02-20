package busesProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPermisoId implements Serializable {

  @Column(name = "id_usuario")
  private Long idUsuario;

  @Column(name = "id_permiso")
  private Long idPermiso;

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
