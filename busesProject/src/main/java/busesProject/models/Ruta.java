package busesProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rutas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_ruta")
  private Integer idRuta;

  @Column(name = "nombre_ruta", nullable = false, unique = true, length = 50)
  private String nombreRuta;

  public void setNombreRuta(String nombreRuta) {
    this.nombreRuta = nombreRuta;
  }

  public String getNombreRuta() {
    return nombreRuta;
  }
}