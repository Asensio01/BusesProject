package busesProject.models;

import jakarta.persistence.*;
import busesProject.enums.TipoViaje;

@Entity
@Table(name = "rutas_tramos")
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

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_viaje", nullable = false)
  private TipoViaje tipoViaje;

  public RutaTramo() {}

  public RutaTramo(Ruta ruta, Tramo tramo, TipoViaje tipoViaje) {
    this.ruta = ruta;
    this.tramo = tramo;
    this.tipoViaje = tipoViaje;
  }

  // Getters y Setters
  public Integer getIdRutaTramo() { return idRutaTramo; }
  public void setIdRutaTramo(Integer idRutaTramo) { this.idRutaTramo = idRutaTramo; }

  public Ruta getRuta() { return ruta; }
  public void setRuta(Ruta ruta) { this.ruta = ruta; }

  public Tramo getTramo() { return tramo; }
  public void setTramo(Tramo tramo) { this.tramo = tramo; }

  public TipoViaje getTipoViaje() { return tipoViaje; }
  public void setTipoViaje(TipoViaje tipoViaje) { this.tipoViaje = tipoViaje; }
}
