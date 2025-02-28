package busesProject.controllers;

import busesProject.models.Ciudad;
import busesProject.Services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    /**
     * 🔹 GET all ciudades
     */
    @GetMapping
    public ResponseEntity<List<Ciudad>> obtenerCiudades() {
        return ResponseEntity.ok(ciudadService.obtenerTodasLasCiudades());
    }
}
