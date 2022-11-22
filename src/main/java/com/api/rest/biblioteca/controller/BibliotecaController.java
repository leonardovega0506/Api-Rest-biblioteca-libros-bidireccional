package com.api.rest.biblioteca.controller;

import com.api.rest.biblioteca.model.Biblioteca;
import com.api.rest.biblioteca.repository.IBliblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/bilioteca")
public class BibliotecaController {

    @Autowired
    private IBliblioteca iBliblioteca;

    @PostMapping
    public ResponseEntity<Biblioteca> guardarBiblioteca(@RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaGuardada = iBliblioteca.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bibliotecaGuardada.getId()).toUri();
        return  ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable(value = "id") int id_biblioteca,@RequestBody Biblioteca biblioteca){
        Optional<Biblioteca> biblotecaOptiona = iBliblioteca.findById(id_biblioteca);

        if(!biblotecaOptiona.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        biblioteca.setId(biblotecaOptiona.get().getId());
        iBliblioteca.save(biblioteca);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable(value = "id") int id_biblioteca){
        Optional<Biblioteca> biblotecaOptiona = iBliblioteca.findById(id_biblioteca);

        if(!biblotecaOptiona.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        iBliblioteca.delete(biblotecaOptiona.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> obtenerBibliotecaById(@PathVariable(value = "id") int id_biblioteca){
        Optional<Biblioteca> biblotecaOptiona = iBliblioteca.findById(id_biblioteca);

        if(!biblotecaOptiona.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return  ResponseEntity.ok(biblotecaOptiona.get());
    }

    @GetMapping
    public ResponseEntity<Page<Biblioteca>> listarBibliotecas(Pageable pageable){
        return ResponseEntity.ok(iBliblioteca.findAll(pageable));
    }
}
