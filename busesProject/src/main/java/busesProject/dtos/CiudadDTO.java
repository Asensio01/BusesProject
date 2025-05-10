package busesProject.dtos;

import busesProject.enums.Departamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CiudadDTO {

  @NotBlank(message = "El nombre de la ciudad es obligatorio.")
  private String nombre;

  @NotNull(message = "El departamento es obligatorio.")
  private Departamento departamento;
}
