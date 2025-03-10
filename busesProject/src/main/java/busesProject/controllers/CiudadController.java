package busesProject.controllers;

import busesProject.dtos.CiudadDTO;
import busesProject.models.Ciudad;
import busesProject.Services.CiudadService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permitir peticiones desde React/ Ya la tenia
@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

  private final CiudadService ciudadService;

  public CiudadController(CiudadService ciudadService) {
    this.ciudadService = ciudadService;
  }

  @GetMapping
  public List<Ciudad> obtenerCiudades() {
    return ciudadService.obtenerTodasLasCiudades();
  }

  @PostMapping
  public ResponseEntity<?> createCiudad(@Valid @RequestBody CiudadDTO ciudad){
    Ciudad newCity = ciudadService.addCity(ciudad);
    return ResponseEntity.ok(newCity);
  }
}