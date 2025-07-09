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

    // Regra: Buscar uma Matrícula
    public Matricula findById(Long id) {
        Optional<Matricula> obj = repository.findById(id);
        return obj.orElseThrow(() -> new EntityNotFoundException("Matrícula não encontrada com ID: " + id));
    }

    // Regra: Buscar todos as Matrícula cadastradas
    public List<Matricula> findAll() {
        return repository.findAll();
    }

    // Regra: Cadastrar nova Matrícula
    public Matricula insert(Matricula matricula) {
        return repository.save(matricula);
    }

    // Regra: Atualizar Matricula
    public Matricula update(Long id, Matricula novaMatricula) {
        Matricula existente = findById(id);
        existente.setDataInicio(novaMatricula.getDataInicio());
        existente.setDataFim(novaMatricula.getDataFim());
        return repository.save(existente);
    }

    // Regra: Deletar uma Matrícula
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Matrícula não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Regra: Deletar todas as Matrículas
    public void deleteAll() {
        repository.deleteAll();
    }
}
