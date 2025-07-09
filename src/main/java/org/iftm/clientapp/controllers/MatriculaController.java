package org.iftm.clientapp.controllers;

import java.util.List;

import org.iftm.clientapp.entities.Matricula;
import org.iftm.clientapp.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// teste commit
@RestController
@RequestMapping("/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        List<Matricula> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Long id) {
        Matricula matricula = service.findById(id);
        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<Matricula> insert(@RequestBody Matricula matricula) {
        Matricula novaMatricula = service.insert(matricula);
        return ResponseEntity.ok(novaMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update(@PathVariable Long id, @RequestBody Matricula matricula) {
        Matricula atualizada = service.update(id, matricula);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
