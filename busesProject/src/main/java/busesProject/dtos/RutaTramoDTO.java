package busesProject.dtos;

public class RutaTramoDTO {
  private Integer idRuta;
  private Integer idTramo;
  private Integer orden;

  public RutaTramoDTO() {}

  public RutaTramoDTO(Integer idRuta, Integer idTramo, Integer orden) {
    this.idRuta = idRuta;
    this.idTramo = idTramo;
    this.orden = orden;
  }

  public Integer getIdRuta() { return idRuta; }
  public void setIdRuta(Integer idRuta) { this.idRuta = idRuta; }

  public Integer getIdTramo() { return idTramo; }
  public void setIdTramo(Integer idTramo) { this.idTramo = idTramo; }

  public Integer getOrden() { return orden; }
  public void setTipoViaje(String tipoViaje) { this.orden = orden; }
}