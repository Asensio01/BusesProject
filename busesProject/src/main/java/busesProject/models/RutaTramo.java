package busesProject.models;

import busesProject.enums.TipoViaje;
import jakarta.persistence.*;

@Entity
@Table(name = "rutas_tramos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_ruta", "id_tramo"})
        })

public class RutaTramo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ruta_tramo")
  private Integer idRutaTramo;

  @ManyToOne
  @JoinColumn(name = "id_ruta", nullable = false)
  private Ruta ruta;

  @ManyToOne
  @JoinColumn(name = "id_tramo", nullable = false)
  private Tramo tramo;


  @Column(name = "orden", nullable = false)
  private Integer orden;

  public RutaTramo() {
  }

  public RutaTramo(Ruta ruta, Tramo tramo, Integer orden) {
    this.ruta = ruta;
    this.tramo = tramo;
    this.orden = orden;
  }

  // Getters y Setters
  public Integer getIdRutaTramo() {
    return idRutaTramo;
  }

  public void setIdRutaTramo(Integer idRutaTramo) {
    this.idRutaTramo = idRutaTramo;
  }

  public Ruta getRuta() {
    return ruta;
  }

  public void setRuta(Ruta ruta) {
    this.ruta = ruta;
  }

  public Tramo getTramo() {
    return tramo;
  }

  public void setTramo(Tramo tramo) {
    this.tramo = tramo;
  }

  public Integer getOrden() {
    return orden;
  }

  public void setOrden(Integer orden) {
    this.orden = orden;
  }
}