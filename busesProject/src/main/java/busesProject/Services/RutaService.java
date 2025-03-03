package busesProject.Services;

import busesProject.models.Ruta;
import busesProject.repositories.RutaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }

    /**
     * 🔹 Obtener todas las rutas
     */
    public List<Ruta> obtenerTodasLasRutas() {
        return rutaRepository.findAll();
    }

    /**
     * 🔹 Crear ruta
     */
    @Transactional
    public Ruta crearRuta(String nombreRuta) {
        if (nombreRuta == null || nombreRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ruta no puede estar vacío.");
        }

        if (rutaRepository.existsByNombreRuta(nombreRuta)) {
            throw new RuntimeException("Ya existe una ruta con ese nombre.");
        }

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setNombreRuta(nombreRuta.trim());

        return rutaRepository.save(nuevaRuta);
    }

    /**
     * 🔹 Editar una ruta existente
     */
    @Transactional
    public Ruta editarRuta(Integer idRuta, String nuevoNombreRuta) {
        if (nuevoNombreRuta == null || nuevoNombreRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre de la ruta no puede estar vacío.");
        }

        Ruta rutaExistente = rutaRepository.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + idRuta));

        if (rutaRepository.existsByNombreRuta(nuevoNombreRuta.trim())) {
            throw new RuntimeException("Ya existe una ruta con ese nombre.");
        }

        rutaExistente.setNombreRuta(nuevoNombreRuta.trim());
        return rutaRepository.save(rutaExistente);
    }

    /**
     * 🔹 Eliminar una ruta
     */
    @Transactional
    public void eliminarRuta(Integer idRuta) {
        if (!rutaRepository.existsById(idRuta)) {
            throw new RuntimeException("Ruta no encontrada.");
        }
        rutaRepository.deleteById(idRuta);
    }
}
