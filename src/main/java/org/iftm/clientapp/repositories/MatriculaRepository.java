package org.iftm.clientapp.repositories;

import java.time.Instant;
import java.util.List;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long>{

    // Método para pesquisar matricula pelo nome
    public List<Curso> findByAlunoContaining(String nome);

    // Método para pesquisar matricular no curso
    public List<Curso> findByCursoLike(String curso); 

    // Método para pesquisar matricula a partir da data de inicio (Ex.: Matriculas que iniciam a partir de 01/01/2025)
    public List<Curso> findByDataInicioAfter(Instant datainicio);
}
