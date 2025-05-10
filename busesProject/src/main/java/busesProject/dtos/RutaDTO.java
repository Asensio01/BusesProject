package busesProject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RutaDTO {
  private String nombreRuta;

  public String getNombreRuta() {
    return nombreRuta;
  }

  public void setNombreRuta(String nombreRuta) {
    this.nombreRuta = nombreRuta;
  }
}