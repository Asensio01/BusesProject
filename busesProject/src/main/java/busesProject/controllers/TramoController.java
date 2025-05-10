package busesProject.controllers;

import busesProject.dtos.TramoDTO;
import busesProject.models.Tramo;
import busesProject.Services.TramoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramos")
public class TramoController {

  private final TramoService tramoService;

  public TramoController(TramoService tramoService) {
    this.tramoService = tramoService;
  }

  /**
   * 🔹 Obtener todos los tramos
   */
  @GetMapping
  public ResponseEntity<List<Tramo>> obtenerTramos() {
    List<Tramo> tramos = tramoService.obtenerTodosLosTramos();
    return ResponseEntity.ok(tramos);
  }

  /**
   * 🔹 Crear un tramo
   */
  @PostMapping
  public ResponseEntity<?> crearTramo(@RequestBody TramoDTO tramoDTO) {
    try {
      Tramo nuevoTramo = tramoService.crearTramo(tramoDTO);
      return ResponseEntity.ok(nuevoTramo);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * 🔹 Editar un tramo
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> editarTramo(@PathVariable Integer id, @RequestBody TramoDTO tramoDTO) {
    try {
      Tramo tramoEditado = tramoService.editarTramo(id, tramoDTO);
      return ResponseEntity.ok(tramoEditado);
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * 🔹 Eliminar un tramo
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> eliminarTramo(@PathVariable Integer id) {
    try {
      tramoService.eliminarTramo(id);
      return ResponseEntity.ok("Tramo eliminado correctamente");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}