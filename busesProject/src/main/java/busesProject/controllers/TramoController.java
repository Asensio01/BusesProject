package busesProject.controllers;

import busesProject.dtos.TramoDTO;
import busesProject.models.Tramo;
import busesProject.Services.TramoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tramos")
public class TramoController {

    @Autowired
    private TramoService tramoService;

    /**
     * 🔹 Obtener todos los tramos
     */
    @GetMapping
    public ResponseEntity<List<Tramo>> obtenerTramos() {
        return ResponseEntity.ok(tramoService.obtenerTodosLosTramos());
    }

    /**
     * 🔹 Crear un tramo
     */
    @PostMapping
    public ResponseEntity<Tramo> crearTramo(@RequestBody TramoDTO tramoDTO) {
        return ResponseEntity.ok(tramoService.crearTramo(tramoDTO));
    }

    /**
     * 🔹 Editar un tramo
     */
    @PutMapping("/{id}")
    public ResponseEntity<Tramo> editarTramo(@PathVariable int id, @RequestBody TramoDTO tramoDTO) {
        return ResponseEntity.ok(tramoService.editarTramo(id, tramoDTO));
    }

    /**
     * 🔹 Eliminar un tramo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTramo(@PathVariable int id) {
        tramoService.eliminarTramo(id);
        return ResponseEntity.ok("Tramo eliminado correctamente");
    }
}
