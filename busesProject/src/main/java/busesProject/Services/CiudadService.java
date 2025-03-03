package busesProject.Services;

import busesProject.models.Ciudad;
import busesProject.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
