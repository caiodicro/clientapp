package org.iftm.clientapp.controllers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins="*")
public class CursoController {

    @Autowired 
    private CursoService service;

    @GetMapping
    public ResponseEntity<List<Curso>> findyAll() {
        List<Curso> cursos = service.findAll();
        return ResponseEntity.ok(cursos);
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Optional<Curso> curso = service.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get()); 
        } else {
            return ResponseEntity.notFound().build(); 
        }  
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Curso>> findByStatus(@PathVariable String status) {
        List<Curso> cursos = service.findByStatus(status);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Curso>> findByNome(@PathVariable String nome) {
        List<Curso> cursos = service.findByNome(nome);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/datainicio/{dataInicio}")
    public ResponseEntity<List<Curso>> findByData(@PathVariable String dataInicio) {
        String partes[] = dataInicio.split("-");
        List<Curso> cursos = service.findByDataInicio(Instant.parse(partes[2]+"-"+partes[1]+"-"+partes[0]+"T00:00:00Z"));
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/numvagas/{numVagas}")
    public ResponseEntity<List<Curso>> findByMinVagas(@PathVariable Integer numVagas) {
        List<Curso> cursos = service.findByNumVagas(numVagas);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/cargahoraria/{cargaHoraria}")
    public ResponseEntity<List<Curso>> findByCargaHoraria(@PathVariable Integer cargaHoraria) {
        List<Curso> cursos = service.findByNumVagas(cargaHoraria);
        return ResponseEntity.ok(cursos);
    }
}