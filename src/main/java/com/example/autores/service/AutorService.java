package com.example.autores.service;

import com.example.autores.entity.Autor;
import com.example.autores.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.nio.file.*;

@Service
public class AutorService {

  @Autowired
  private AutorRepository repo;

  private final String IMAGE_DIR = "src/main/resources/static/images/";

  public List<Autor> listar() {
    return repo.findAll();
  }

  public Autor crear(String nombre, String biografia, org.springframework.web.multipart.MultipartFile imagen) {
    try {
      String fileName = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
      Path path = Paths.get(IMAGE_DIR + fileName);
      Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

      Autor a = new Autor();
      a.setNombre(nombre);
      a.setBiografia(biografia);
      a.setImagenUrl("/images/" + fileName);
      return repo.save(a);
    } catch (Exception e) {
      throw new RuntimeException("Error al subir imagen", e);
    }
  }

  public void eliminar(Long id) {
    repo.deleteById(id);
  }

  public Autor actualizar(Long id, Autor datos) {
    datos.setId(id);
    return repo.save(datos);
  }
}