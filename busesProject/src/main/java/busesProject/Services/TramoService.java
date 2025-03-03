package busesProject.Services;

import busesProject.dtos.TramoDTO;
import busesProject.models.Ciudad;
import busesProject.models.Tramo;
import busesProject.repositories.CiudadRepository;
import busesProject.repositories.TramoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TramoService {

    private final TramoRepository tramoRepository;
    private final CiudadRepository ciudadRepository;

    public TramoService(TramoRepository tramoRepository, CiudadRepository ciudadRepository) {
        this.tramoRepository = tramoRepository;
        this.ciudadRepository = ciudadRepository;
    }

    /**
     * 🔹 Obtener todos los tramos
     */
    public List<Tramo> obtenerTodosLosTramos() {
        return tramoRepository.findAll();
    }

    /**
     * 🔹 Crear un tramo
     */
    @Transactional
    public Tramo crearTramo(TramoDTO tramoDTO) {
        Ciudad ciudadOrigen = ciudadRepository.findById(tramoDTO.getIdCiudadOrigen())
                .orElseThrow(() -> new RuntimeException("Ciudad de origen no encontrada"));

        Ciudad ciudadDestino = ciudadRepository.findById(tramoDTO.getIdCiudadDestino())
                .orElseThrow(() -> new RuntimeException("Ciudad de destino no encontrada"));

        if (ciudadOrigen.equals(ciudadDestino)) {
            throw new RuntimeException("La ciudad de origen y destino no pueden ser la misma.");
        }

        Tramo nuevoTramo = new Tramo();
        nuevoTramo.setCiudadOrigen(ciudadOrigen);
        nuevoTramo.setCiudadDestino(ciudadDestino);
        nuevoTramo.setHoraSalida(tramoDTO.getHoraSalida());
        nuevoTramo.setDuracion(tramoDTO.getDuracion());

        return tramoRepository.save(nuevoTramo);
    }

    /**
     * 🔹 Editar un tramo
     */
    @Transactional
    public Tramo editarTramo(Integer idTramo, TramoDTO tramoDTO) {
        Tramo tramoExistente = tramoRepository.findById(idTramo)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado con ID: " + idTramo));

        Ciudad ciudadOrigen = ciudadRepository.findById(tramoDTO.getIdCiudadOrigen())
                .orElseThrow(() -> new RuntimeException("Ciudad de origen no encontrada"));

        Ciudad ciudadDestino = ciudadRepository.findById(tramoDTO.getIdCiudadDestino())
                .orElseThrow(() -> new RuntimeException("Ciudad de destino no encontrada"));

        if (ciudadOrigen.equals(ciudadDestino)) {
            throw new RuntimeException("La ciudad de origen y destino no pueden ser la misma.");
        }

        tramoExistente.setCiudadOrigen(ciudadOrigen);
        tramoExistente.setCiudadDestino(ciudadDestino);
        tramoExistente.setHoraSalida(tramoDTO.getHoraSalida());
        tramoExistente.setDuracion(tramoDTO.getDuracion());

        return tramoRepository.save(tramoExistente);
    }

    /**
     * 🔹 Eliminar un tramo
     */
    @Transactional
    public void eliminarTramo(Integer idTramo) {
        if (!tramoRepository.existsById(idTramo)) {
            throw new RuntimeException("Tramo no encontrado.");
        }
        tramoRepository.deleteById(idTramo);
    }
}
