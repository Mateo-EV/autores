package com.example.autores.entity;

import jakarta.persistence.*;

@Entity
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String biografia;
  private String imagenUrl;

  // Getters y setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getBiografia() { return biografia; }
  public void setBiografia(String biografia) { this.biografia = biografia; }
  public String getImagenUrl() { return imagenUrl; }
  public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}