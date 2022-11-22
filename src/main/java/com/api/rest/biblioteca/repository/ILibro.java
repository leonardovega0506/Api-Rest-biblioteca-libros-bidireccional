package com.api.rest.biblioteca.repository;

import com.api.rest.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibro extends JpaRepository<Libro,Integer> {
}
