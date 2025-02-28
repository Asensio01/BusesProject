package busesProject.dtos;

import busesProject.enums.TipoViaje;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RutaDTO {
    private String nombreRuta;
    private List<Integer> idTramos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TipoViaje tipoViaje;
}
