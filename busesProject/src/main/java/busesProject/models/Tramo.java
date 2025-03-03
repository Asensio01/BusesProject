package busesProject.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tramos")
public class Tramo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_tramo")
  private Integer idTramo;

  @ManyToOne
  @JoinColumn(name = "id_ciudad_origen", nullable = false)
  private Ciudad ciudadOrigen;

  @ManyToOne
  @JoinColumn(name = "id_ciudad_destino", nullable = false)
  private Ciudad ciudadDestino;

  @Column(name = "hora_salida", nullable = false)
  private String horaSalida; // Formato HH:mm:ss

  @Column(nullable = false)
  private Integer duracion; // Duración en minutos

  // 🔹 Constructor vacío (Necesario para JPA)
  public Tramo() {}

  // 🔹 Constructor con parámetros
  public Tramo(Integer idTramo, Ciudad ciudadOrigen, Ciudad ciudadDestino, String horaSalida, Integer duracion) {
    this.idTramo = idTramo;
    this.ciudadOrigen = ciudadOrigen;
    this.ciudadDestino = ciudadDestino;
    this.horaSalida = horaSalida;
    this.duracion = duracion;
  }

  // 🔹 Getters y Setters

  public Integer getIdTramo() {
    return idTramo;
  }

  public void setIdTramo(Integer idTramo) {
    this.idTramo = idTramo;
  }

  public Ciudad getCiudadOrigen() {
    return ciudadOrigen;
  }

  public void setCiudadOrigen(Ciudad ciudadOrigen) {
    this.ciudadOrigen = ciudadOrigen;
  }

  public Ciudad getCiudadDestino() {
    return ciudadDestino;
  }

  public void setCiudadDestino(Ciudad ciudadDestino) {
    this.ciudadDestino = ciudadDestino;
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
