package org.iftm.clientapp.services;

import java.util.List;
import java.util.Optional;

import org.iftm.clientapp.entities.Matricula;
import org.iftm.clientapp.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    public Matricula findById(Long id) {
        Optional<Matricula> obj = repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com ID: " + id));
    }

    public List<Matricula> findAll() {
        return repository.findAll();
    }

    public Matricula insert(Matricula matricula) {
        return repository.save(matricula);
    }

    public Matricula update(Long id, Matricula novaMatricula) {
        Matricula existente = findById(id);
        existente.setDataInicio(novaMatricula.getDataInicio());
        existente.setDataFim(novaMatricula.getDataFim());
        return repository.save(existente);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
