package busesProject.controllers;

import busesProject.models.Ciudad;
import busesProject.Services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
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
}