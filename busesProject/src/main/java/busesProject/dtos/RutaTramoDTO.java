package busesProject.dtos;

public class RutaTramoDTO {
    private Integer idRuta;
    private Integer idTramo;
    private String tipoViaje;

    public RutaTramoDTO() {}

    public RutaTramoDTO(Integer idRuta, Integer idTramo, String tipoViaje) {
        this.idRuta = idRuta;
        this.idTramo = idTramo;
        this.tipoViaje = tipoViaje;
    }

    public Integer getIdRuta() { return idRuta; }
    public void setIdRuta(Integer idRuta) { this.idRuta = idRuta; }

    public Integer getIdTramo() { return idTramo; }
    public void setIdTramo(Integer idTramo) { this.idTramo = idTramo; }

    public String getTipoViaje() { return tipoViaje; }
    public void setTipoViaje(String tipoViaje) { this.tipoViaje = tipoViaje; }
}
