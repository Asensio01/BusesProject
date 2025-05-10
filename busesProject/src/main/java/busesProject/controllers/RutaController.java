package busesProject.controllers;

import busesProject.dtos.RutaDTO;
import busesProject.models.Ruta;
import busesProject.Services.RutaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

  private final RutaService rutaService;

  public RutaController(RutaService rutaService) {
    this.rutaService = rutaService;
  }

  /**
   * 🔹 Obtener todas las rutas
   */
  @GetMapping
  public ResponseEntity<List<Ruta>> obtenerRutas() {
    return ResponseEntity.ok(rutaService.obtenerTodasLasRutas());
  }

  /**
   * 🔹 Crear una nueva ruta usa DTO
   */
  @PostMapping
  public ResponseEntity<?> crearRuta(@RequestBody RutaDTO rutaDTO) {
    try {
      String nombreRuta = rutaDTO.getNombreRuta();
      Ruta nuevaRuta = rutaService.crearRuta(nombreRuta);
      return ResponseEntity.ok(nuevaRuta);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al crear la ruta: " + e.getMessage());
    }
  }

  /**
   * 🔹 Editar una ruta usa DTO
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> editarRuta(@PathVariable Integer id, @RequestBody RutaDTO rutaDTO) {
    try {
      String nuevoNombreRuta = rutaDTO.getNombreRuta();
      Ruta rutaEditada = rutaService.editarRuta(id, nuevoNombreRuta);
      return ResponseEntity.ok(rutaEditada);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error al editar la ruta: " + e.getMessage());
    }
  }

  /**
   * 🔹 Eliminar una ruta
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarRuta(@PathVariable Integer id) {
    try {
      rutaService.eliminarRuta(id);
      return ResponseEntity.ok("Ruta eliminada correctamente");
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}