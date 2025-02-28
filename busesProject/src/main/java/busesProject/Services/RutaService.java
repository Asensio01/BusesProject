package busesProject.Services;

import busesProject.dtos.RutaDTO;
import busesProject.dtos.RutaSimpleDTO;
import busesProject.enums.TipoViaje;
import busesProject.models.Ruta;
import busesProject.models.RutaTramo;
import busesProject.models.RutaTramoId;
import busesProject.models.Tramo;
import busesProject.repositories.RutaRepository;
import busesProject.repositories.RutaTramoRepository;
import busesProject.repositories.TramoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private TramoRepository tramoRepository;

    @Autowired
    private RutaTramoRepository rutaTramoRepository;

    /**
     * 🔹 Crear una nueva ruta (solo insertará en la tabla `rutas`)
     */
    @Transactional
    public Ruta crearRutaNueva(RutaSimpleDTO rutaDTO) {
        if (rutaRepository.existsByNombreRuta(rutaDTO.getNombreRuta())) {
            throw new RuntimeException("Ya existe una ruta con este nombre.");
        }

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setNombreRuta(rutaDTO.getNombreRuta());

        return rutaRepository.save(nuevaRuta);
    }


    /**
     * 🔹 Crear una nueva ruta con sus tramos
     */
    @Transactional
    public Ruta crearRuta(RutaDTO rutaDTO) {
        if (rutaRepository.existsByNombreRuta(rutaDTO.getNombreRuta())) {
            throw new RuntimeException("Ya existe una ruta con este nombre.");
        }

        Ruta nuevaRuta = new Ruta();
        nuevaRuta.setNombreRuta(rutaDTO.getNombreRuta());
        nuevaRuta = rutaRepository.save(nuevaRuta);

        if (rutaDTO.getIdTramos() != null && !rutaDTO.getIdTramos().isEmpty()) {
            TipoViaje tipoViaje = (rutaDTO.getTipoViaje() != null) ? rutaDTO.getTipoViaje() : TipoViaje.IDA; // ✅ Enum correcto

            for (int idTramo : rutaDTO.getIdTramos()) {
                Tramo tramo = tramoRepository.findById(idTramo)
                        .orElseThrow(() -> new RuntimeException("Tramo con ID " + idTramo + " no encontrado"));

                RutaTramo rutaTramo = new RutaTramo();
                rutaTramo.setId(new RutaTramoId(nuevaRuta.getIdRuta(), tramo.getIdTramo(), tipoViaje));
                rutaTramo.setRuta(nuevaRuta);
                rutaTramo.setTramo(tramo);
                rutaTramo.setTipoViaje(tipoViaje);

                rutaTramoRepository.save(rutaTramo);
            }
        }

        return nuevaRuta;
    }


    /**
     * 🔹 Obtener todas las rutas disponibles
     */
    public List<Ruta> obtenerRutas() {
        return rutaRepository.findAll();
    }

    /**
     * 🔹 Editar una ruta existente
     */
    @Transactional
    public Ruta editarRuta(int idRuta, RutaDTO rutaDTO) {
        Ruta ruta = rutaRepository.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con ID: " + idRuta));

        if (rutaDTO.getNombreRuta() != null && !rutaDTO.getNombreRuta().isEmpty()) {
            ruta.setNombreRuta(rutaDTO.getNombreRuta());
        }

        ruta = rutaRepository.save(ruta);

        if (rutaDTO.getIdTramos() != null && !rutaDTO.getIdTramos().isEmpty()) {
            rutaTramoRepository.deleteByRutaId(idRuta);

            // ✅ Si el usuario no envía `tipoViaje`, usar `IDA` como valor por defecto
            TipoViaje tipoViaje = (rutaDTO.getTipoViaje() != null) ? rutaDTO.getTipoViaje() : TipoViaje.IDA;

            for (int idTramo : rutaDTO.getIdTramos()) {
                Tramo tramo = tramoRepository.findById(idTramo)
                        .orElseThrow(() -> new RuntimeException("Tramo con ID " + idTramo + " no encontrado"));

                RutaTramo rutaTramo = new RutaTramo();
                rutaTramo.setId(new RutaTramoId(ruta.getIdRuta(), tramo.getIdTramo(), tipoViaje));
                rutaTramo.setRuta(ruta);
                rutaTramo.setTramo(tramo);
                rutaTramo.setTipoViaje(tipoViaje);

                rutaTramoRepository.save(rutaTramo);
            }
        }

        return ruta;
    }


    /**
     * 🔹 Eliminar una ruta por su ID
     */
    @Transactional
    public void eliminarRuta(int idRuta) {
        if (!rutaRepository.existsById(idRuta)) {
            throw new RuntimeException("Ruta no encontrada.");
        }
        rutaTramoRepository.deleteByRutaId(idRuta);
        rutaRepository.deleteById(idRuta);
    }
}
