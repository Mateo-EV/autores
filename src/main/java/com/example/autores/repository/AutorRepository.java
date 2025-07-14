package com.example.autores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.autores.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {}