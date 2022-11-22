package com.api.rest.biblioteca.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "biblioteca")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre_biblioteca",nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "biblioteca",cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<>();

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for(Libro libro: libros){
            libro.setBiblioteca(this);
        }
    }
}
