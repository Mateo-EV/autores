package com.example.autores.service;

import com.example.autores.entity.Autor;
import com.example.autores.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.nio.file.*;

@Service
public class AutorService {

  @Autowired
  private AutorRepository repo;

  @Value("${app.upload.dir:/app/uploads/images/}")
  private String imageDir;

  @Value("${app.upload.url-prefix:/uploads/images/}")
  private String urlPrefix;

  public List<Autor> listar() {
    return repo.findAll();
  }

  public Autor crear(String nombre, String biografia, org.springframework.web.multipart.MultipartFile imagen) {
    try {
      // Crear el directorio si no existe
      Path uploadPath = Paths.get(imageDir);
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      String fileName = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
      Path path = uploadPath.resolve(fileName);
      Files.copy(imagen.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

      Autor a = new Autor();
      a.setNombre(nombre);
      a.setBiografia(biografia);
      a.setImagenUrl(urlPrefix + fileName);
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