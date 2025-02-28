package busesProject.controllers;

import busesProject.dtos.RutaDTO;
import busesProject.dtos.RutaSimpleDTO;
import busesProject.models.Ruta;
import busesProject.Services.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    /**
     * 🔹 Crear una nueva ruta (solo se inserta en la tabla `rutas`)
     */
    @PostMapping
    public ResponseEntity<Ruta> crearRuta(@RequestBody RutaSimpleDTO rutaDTO) {
        return ResponseEntity.ok(rutaService.crearRutaNueva(rutaDTO));
    }


    /**
     * 🔹 Crear una nueva ruta con tramos asociados
     */
    @PostMapping("/con-tramos")
    public ResponseEntity<Ruta> crearRutaConTramos(@RequestBody RutaDTO rutaDTO) {
        return ResponseEntity.ok(rutaService.crearRuta(rutaDTO));
    }

    /**
     * 🔹 Obtener todas las rutas
     */
    @GetMapping
    public ResponseEntity<List<Ruta>> obtenerRutas() {
        return ResponseEntity.ok(rutaService.obtenerRutas());
    }

    /**
     * 🔹 Editar una ruta
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ruta> editarRuta(@PathVariable int id, @RequestBody RutaDTO rutaDTO) {
        return ResponseEntity.ok(rutaService.editarRuta(id, rutaDTO));
    }

    /**
     * 🔹 Eliminar una ruta
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRuta(@PathVariable int id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.ok("Ruta eliminada correctamente");
    }
}
