package org.iftm.clientapp.services;

import java.time.Instant;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CursoService {
    // Injeção de dependencia
    @Autowired
    private CursoRepository repositorio;

    //Método para validação do cadastro do NOME DO CURSO
    public void validarNomeCurso(String nome){
        if(nome.length() <=0 || nome.length() >80){
            throw new IllegalArgumentException("Nome Inválido! Carga Horária do curso deve ser entre 1 e 255 caracteres!");
        }
        if(nome.charAt(0) == ' '){
            throw new IllegalArgumentException("Nome Inválido! Não é possível cadastrar o nome do curso com espaço no primeiro caracter!");
        }
    }

    //Método para validação do cadastro da DESCRIÇÃO DO CURSO
    public void validarDescricaoCurso(String descricao){
        if(descricao.length() < 15 || descricao.length() >255){
            throw new IllegalArgumentException("Descrição Inválida! Descrição do curso deve ser entre 15 e 255 caracteres!");
        }
    }

    //Método para validação do cadastro da CARGA HORÁRIA DO CURSO
    public void validarCargaHorariaCurso(Integer carga){
        if (carga < 10 || carga > 100){
            throw new IllegalArgumentException("Carga Horária Inválida! Carga Horária do curso deve ser entre 10 e 100 horas!");
        }
    }

    //Método para validação do cadastro da NÚMERO DE VAGAS DO CURSO
    public void validarNumVagasCurso(Integer numVagas){
        if (numVagas < 5 || numVagas > 100){
            throw new IllegalArgumentException("Número de vagas Inválida! Número de vagas do curso deve ser entre 5 e 100 vagas!");
        }
    }

    //Método para validação do cadasto da DATA DE INICIO DO CURSO
    public void validarDataInicioCurso(Instant dataInicio){
        Instant dataLimite = Instant.parse("2024-01-01T07:00:00.00Z");
        if(dataInicio.isBefore(dataLimite)){
            throw new IllegalArgumentException("Data de início Inválida! Data de início do curso deve ser maior que 01/01/2024!");
        }
    }

    //Método para validação do cadasto da DATA DE TÉRMINO DO CURSO
    public void validarDataTerminoCurso(Instant dataInicio, Instant dataTermino){
        if(dataTermino.isBefore(dataInicio)){
            throw new IllegalArgumentException("Data de Término Inválida! Data de término deve ser maior que data de início do curso!");
        }
    }

    @Transactional
    public Curso insert(Curso cursos){
        validarNomeCurso(cursos.getNome());
        validarCargaHorariaCurso(cursos.getCargaHoraria());
        validarDescricaoCurso(cursos.getDescricao());
        validarNumVagasCurso(cursos.getNumVagas());
        validarDataInicioCurso(cursos.getDataInicio());
        validarDataTerminoCurso(cursos.getDataInicio(), cursos.getDataTermino());
        return repositorio.save(cursos);
    }

}
