package busesProject.Services;

import busesProject.dtos.RutaTramoDTO;
import busesProject.models.Ruta;
import busesProject.models.RutaTramo;
import busesProject.models.Tramo;
import busesProject.repositories.RutaRepository;
import busesProject.repositories.RutaTramoRepository;
import busesProject.repositories.TramoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RutaTramoService {

  private final RutaTramoRepository rutaTramoRepository;
  private final RutaRepository rutaRepository;
  private final TramoRepository tramoRepository;

  public RutaTramoService(RutaTramoRepository rutaTramoRepository,
                          RutaRepository rutaRepository,
                          TramoRepository tramoRepository) {
    this.rutaTramoRepository = rutaTramoRepository;
    this.rutaRepository = rutaRepository;
    this.tramoRepository = tramoRepository;
  }

  /**
   * 🔹 Obtener todas las asignaciones de rutas con tramos
   */
  public List<RutaTramo> obtenerTodasLasAsignaciones() {
    return rutaTramoRepository.findAll();
  }

  /**
   * 🔹 Crear una relación ruta-tramo con validación de duplicados
   */
  @Transactional
  public RutaTramo crearRutaTramo(RutaTramoDTO rutaTramoDTO) {
    Ruta ruta = rutaRepository.findById(rutaTramoDTO.getIdRuta())
            .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

    Tramo tramo = tramoRepository.findById(rutaTramoDTO.getIdTramo())
            .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

    // Validar duplicados por ruta y tramo
    if (rutaTramoRepository.existsByRutaAndTramo(ruta, tramo)) {
      throw new RuntimeException("Esta asignación ya existe.");
    }

    RutaTramo rutaTramo = new RutaTramo(ruta, tramo, rutaTramoDTO.getOrden());
    return rutaTramoRepository.save(rutaTramo);
  }

  /**
   * 🔹 Editar una relación ruta-tramo
   */
  @Transactional
  public RutaTramo editarRutaTramo(Integer id, RutaTramoDTO rutaTramoDTO) {
    RutaTramo rutaTramo = rutaTramoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

    Ruta ruta = rutaRepository.findById(rutaTramoDTO.getIdRuta())
            .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

    Tramo tramo = tramoRepository.findById(rutaTramoDTO.getIdTramo())
            .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

    // Validar duplicados excepto para el mismo registro
    if (!rutaTramo.getIdRutaTramo().equals(id) &&
            rutaTramoRepository.existsByRutaAndTramo(ruta, tramo)) {
      throw new RuntimeException("Ya existe una asignación con estos valores.");
    }

    rutaTramo.setRuta(ruta);
    rutaTramo.setTramo(tramo);
    rutaTramo.setOrden(rutaTramoDTO.getOrden());

    return rutaTramoRepository.save(rutaTramo);
  }

  /**
   * 🔹 Eliminar una relación ruta-tramo
   */
  @Transactional
  public void eliminarRutaTramo(Integer id) {
    if (!rutaTramoRepository.existsById(id)) {
      throw new RuntimeException("Asignación no encontrada.");
    }
    rutaTramoRepository.deleteById(id);
  }
}
