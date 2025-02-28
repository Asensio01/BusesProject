package busesProject.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class TramoDTO {
    private int idCiudadOrigen;
    private int idCiudadDestino;
    private LocalTime horaSalida;
    private Integer duracion;
}
