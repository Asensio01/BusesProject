package busesProject.Services;

import busesProject.dtos.TramoDTO;
import busesProject.models.Ciudad;
import busesProject.models.Tramo;
import busesProject.repositories.CiudadRepository;
import busesProject.repositories.TramoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TramoService {

    @Autowired
    private TramoRepository tramoRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    /**
     * 🔹 Obtener todos los tramos
     */
    public List<Tramo> obtenerTodosLosTramos() {
        return tramoRepository.findAll();
    }

    /**
     * 🔹 Crear un nuevo tramo
     */
    @Transactional
    public Tramo crearTramo(TramoDTO tramoDTO) {
        Ciudad ciudadOrigen = ciudadRepository.findById((long) tramoDTO.getIdCiudadOrigen())
                .orElseThrow(() -> new RuntimeException("Ciudad de origen no encontrada"));

        Ciudad ciudadDestino = ciudadRepository.findById((long) tramoDTO.getIdCiudadDestino())
                .orElseThrow(() -> new RuntimeException("Ciudad de destino no encontrada"));

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
    public Tramo editarTramo(int idTramo, TramoDTO tramoDTO) {
        Tramo tramo = tramoRepository.findById(idTramo)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado con ID: " + idTramo));

        Ciudad ciudadOrigen = ciudadRepository.findById((long) tramoDTO.getIdCiudadOrigen())
                .orElseThrow(() -> new RuntimeException("Ciudad de origen no encontrada"));

        Ciudad ciudadDestino = ciudadRepository.findById((long) tramoDTO.getIdCiudadDestino())
                .orElseThrow(() -> new RuntimeException("Ciudad de destino no encontrada"));

        tramo.setCiudadOrigen(ciudadOrigen);
        tramo.setCiudadDestino(ciudadDestino);
        tramo.setHoraSalida(tramoDTO.getHoraSalida());
        tramo.setDuracion(tramoDTO.getDuracion());

        return tramoRepository.save(tramo);
    }

    /**
     * 🔹 Eliminar un tramo
     */
    @Transactional
    public void eliminarTramo(int idTramo) {
        if (!tramoRepository.existsById(idTramo)) {
            throw new RuntimeException("Tramo no encontrado.");
        }
        tramoRepository.deleteById(idTramo);
    }
}
