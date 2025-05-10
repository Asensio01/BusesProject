package busesProject.controllers;

import busesProject.dtos.RutaTramoDTO;
import busesProject.models.RutaTramo;
import busesProject.Services.RutaTramoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas-tramos")
public class RutaTramoController {

  private final RutaTramoService rutaTramoService;

  public RutaTramoController(RutaTramoService rutaTramoService) {
    this.rutaTramoService = rutaTramoService;
  }

  /**
   * 🔹 Obtener todas las asignaciones de rutas con tramos
   */
  @GetMapping
  public ResponseEntity<List<RutaTramo>> obtenerTodasLasAsignaciones() {
    return ResponseEntity.ok(rutaTramoService.obtenerTodasLasAsignaciones());
  }

  /**
   * 🔹 Crear una relación ruta-tramo con tipo de viaje
   */
  @PostMapping("/con-tipo")
  public ResponseEntity<?> crearRutaTramo(@RequestBody RutaTramoDTO rutaTramoDTO) {
    try {
      RutaTramo rutaTramo = rutaTramoService.crearRutaTramo(rutaTramoDTO);
      return ResponseEntity.ok(rutaTramo);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * 🔹 Crear una relación ruta-tramo SIN especificar tipo de viaje (asigna IDA por defecto)
   */


  /**
   * 🔹 Editar una asignación de ruta-tramo
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> editarRutaTramo(@PathVariable Integer id, @RequestBody RutaTramoDTO rutaTramoDTO) {
    try {
      RutaTramo rutaTramo = rutaTramoService.editarRutaTramo(id, rutaTramoDTO);
      return ResponseEntity.ok(rutaTramo);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * 🔹 Eliminar una asignación de ruta-tramo
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarRutaTramo(@PathVariable Integer id) {
    try {
      rutaTramoService.eliminarRutaTramo(id);
      return ResponseEntity.ok("Asignación eliminada correctamente.");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}