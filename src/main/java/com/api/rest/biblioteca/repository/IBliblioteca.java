package com.api.rest.biblioteca.repository;

import com.api.rest.biblioteca.model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBliblioteca extends JpaRepository<Biblioteca,Integer> {
}
