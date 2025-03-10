package busesProject.Services;

import busesProject.dtos.CiudadDTO;
import busesProject.models.Ciudad;
import busesProject.repositories.CiudadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CiudadService {

  private final CiudadRepository ciudadRepository;

  public CiudadService(CiudadRepository ciudadRepository) {
    this.ciudadRepository = ciudadRepository;
  }

  public List<Ciudad> obtenerTodasLasCiudades() {
    return ciudadRepository.findAll();
  }
  public Ciudad addCity(CiudadDTO ciudadData) {
    Ciudad newCiudad = Ciudad.builder()
            .nombre(ciudadData.getNombre())
            .departamento(ciudadData.getDepartamento())
            .build();
    return ciudadRepository.save(newCiudad);
  }

}