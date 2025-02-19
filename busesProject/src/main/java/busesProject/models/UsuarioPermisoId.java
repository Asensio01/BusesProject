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

  private Integer id_usuario;
  private Integer id_permiso;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsuarioPermisoId that = (UsuarioPermisoId) o;
    return Objects.equals(id_usuario, that.id_usuario) &&
            Objects.equals(id_permiso, that.id_permiso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id_usuario, id_permiso);
  }
}
