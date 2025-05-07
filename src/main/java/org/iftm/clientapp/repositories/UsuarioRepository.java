package org.iftm.clientapp.repositories;

import java.time.Instant;
import java.util.List;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // Método para pesquisar usuario pelo nome
    public List<Curso> findByNomeContaining(String nome); 

    // Método para pesquisar usuario por status( Ex.: Cursos Ativos e Inativos)
    public List<Curso> findByStatus(String status);

    // Método para pesquisar usuario pelo email
    public List<Curso> findByEmailLike(String email); 
    
    // Método para pesquisar usuario pelo email
    public List<Curso> findByNascimento(Instant nascimento); 
}
