package org.iftm.clientapp.repositories;

import java.time.Instant;
import java.util.List;

import org.iftm.clientapp.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Busca matrículas onde o nome do usuário contém string
    List<Matricula> findByUsuarioNomeContaining(String nome);

    // Busca matrículas que iniciam após certa data
    List<Matricula> findByDataInicioAfter(Instant dataInicio);
}
