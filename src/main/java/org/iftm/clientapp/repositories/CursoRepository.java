package org.iftm.clientapp.repositories;

import java.time.Instant;
import java.util.List;

import org.iftm.clientapp.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
    
    // Método para pesquisar curso pelo nome
    public List<Curso> findByNomeContaining(String nome); 

    // Método para pesquisar cursos por status( Ex.: Cursos Ativos e Inativos)
    public List<Curso> findByStatus(String status);

    // Método para pesquisar cursos a partir da data de inicio (Ex.: Cursos que iniciam a partis de 01/01/2025)
    public List<Curso> findByDataInicioAfter(Instant datainicio);

    // Método para pesquisar cursos a partir do número de vagas (Ex.: Cursos com 20 vagas ou mais)
    public List<Curso> findByNumVagasGreaterThanEqual(int numDeVagas);

     // Método para pesquisar cursos a partir da carga horária (Ex.: Cursos com carga horaria maior que 15)
    public List<Curso> findByCargaHorariaGreaterThanEqual(int cargahoraria);
}
