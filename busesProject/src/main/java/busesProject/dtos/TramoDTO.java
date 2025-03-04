package busesProject.dtos;

public class TramoDTO {
  private Integer idCiudadOrigen;
  private Integer idCiudadDestino;
  private String horaSalida;
  private Integer duracion;

  // Constructor vacío
  public TramoDTO() {}

  // Constructor con parámetros
  public TramoDTO(Integer idCiudadOrigen, Integer idCiudadDestino, String horaSalida, Integer duracion) {
    this.idCiudadOrigen = idCiudadOrigen;
    this.idCiudadDestino = idCiudadDestino;
    this.horaSalida = horaSalida;
    this.duracion = duracion;
  }

  // Getters y Setters manuales
  public Integer getIdCiudadOrigen() {
    return idCiudadOrigen;
  }

  public void setIdCiudadOrigen(Integer idCiudadOrigen) {
    this.idCiudadOrigen = idCiudadOrigen;
  }

  public Integer getIdCiudadDestino() {
    return idCiudadDestino;
  }

  public void setIdCiudadDestino(Integer idCiudadDestino) {
    this.idCiudadDestino = idCiudadDestino;
  }

  public String getHoraSalida() {
    return horaSalida;
  }

  public void setHoraSalida(String horaSalida) {
    this.horaSalida = horaSalida;
  }

  public Integer getDuracion() {
    return duracion;
  }

  public void setDuracion(Integer duracion) {
    this.duracion = duracion;
  }
}