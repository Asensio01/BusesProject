package busesProject.dtos;

public class RutaTramoSinTipoDTO {
    private Integer idRuta;
    private Integer idTramo;

    public RutaTramoSinTipoDTO() {}

    public RutaTramoSinTipoDTO(Integer idRuta, Integer idTramo) {
        this.idRuta = idRuta;
        this.idTramo = idTramo;
    }

    public Integer getIdRuta() { return idRuta; }
    public void setIdRuta(Integer idRuta) { this.idRuta = idRuta; }

    public Integer getIdTramo() { return idTramo; }
    public void setIdTramo(Integer idTramo) { this.idTramo = idTramo; }
}
