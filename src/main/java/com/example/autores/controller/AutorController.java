package com.example.autores.controller;

import com.example.autores.entity.Autor;
import com.example.autores.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
public class AutorController {

  @Autowired
  private AutorService service;

  @GetMapping
  public List<Autor> listar() {
    return service.listar();
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Autor crear(@RequestParam("nombre") String nombre,
                     @RequestParam("biografia") String biografia,
                     @RequestParam("imagen") MultipartFile imagen) {
    return service.crear(nombre, biografia, imagen);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }

  @PutMapping("/{id}")
  public Autor actualizar(@PathVariable Long id, @RequestBody Autor autor) {
    return service.actualizar(id, autor);
  }
}