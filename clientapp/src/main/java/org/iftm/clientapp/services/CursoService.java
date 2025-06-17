package org.iftm.clientapp.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repositorio;

    // Regra: Validação do cadastro do NOME DO CURSO
    public void validarNomeCurso(String nome) {
        if (nome == null || nome.isBlank() || nome.length() > 80) {
            throw new IllegalArgumentException(
                    "Nome Inválido! Nome do curso deve ser entre 1 e 80 caracteres!");
        }
        if (nome.charAt(0) == ' ') {
            throw new IllegalArgumentException(
                    "Nome Inválido! Não é possível cadastrar o nome do curso com espaço no primeiro caracter!");
        }
    }

    // Regra: Validação do cadastro da DESCRIÇÃO DO CURSO
    public void validarDescricaoCurso(String descricao) {
        if (descricao.length() < 15 || descricao.length() > 255) {
            throw new IllegalArgumentException(
                    "Descrição Inválida! Descrição do curso deve ser entre 15 e 255 caracteres!");
        }
    }

    // Regra: Validação do cadastro da CARGA HORÁRIA DO CURSO
    public void validarCargaHorariaCurso(Integer carga) {
        if (carga < 10 || carga > 100) {
            throw new IllegalArgumentException(
                    "Carga Horária Inválida! Carga Horária do curso deve ser entre 10 e 100 horas!");
        }
    }

    // Regra: Validação do cadastro da NÚMERO DE VAGAS DO CURSO
    public void validarNumVagasCurso(Integer numVagas) {
        if (numVagas < 5 || numVagas > 100) {
            throw new IllegalArgumentException(
                    "Número de vagas Inválida! Número de vagas do curso deve ser entre 5 e 100 vagas!");
        }
    }

    // Regra: Validação do cadasto da DATA DE INÍCIO DO CURSO
    public void validarDataInicioCurso(Instant dataInicio) {
        Instant dataLimite = Instant.parse("2024-01-01T07:00:00.00Z");
        if (dataInicio.isBefore(dataLimite)) {
            throw new IllegalArgumentException(
                    "Data de início Inválida! Data de início do curso deve ser maior que 01/01/2024!");
        }
    }

    // Regra: Validação do cadasto da DATA DE TÉRMINO DO CURSO
    public void validarDataTerminoCurso(Instant dataInicio, Instant dataTermino) {
        if (dataTermino.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                    "Data de Término Inválida! Data de término deve ser maior que data de início do curso!");
        }
    }

    // Regra: Cadastrar um novo CURSO
    @Transactional
    public Curso insert(Curso insertCurso) {
        validarNomeCurso(insertCurso.getNome());
        validarCargaHorariaCurso(insertCurso.getCargaHoraria());
        validarDescricaoCurso(insertCurso.getDescricao());
        validarNumVagasCurso(insertCurso.getNumVagas());
        validarDataInicioCurso(insertCurso.getDataInicio());
        validarDataTerminoCurso(insertCurso.getDataInicio(), insertCurso.getDataTermino());
        return repositorio.save(insertCurso);
    }

    // Regra: Excluir um CURSO
    @Transactional
    public void delete(Long id) {
        if (!repositorio.existsById(id)) {
            throw new IllegalArgumentException("Curso com ID " + id + " não encontrado!");
        }
        repositorio.deleteById(id);
    }

    // Regra: Excluir todos os CURSOS
    @Transactional
    public void deleteAll() {
        repositorio.deleteAll();
    }

    // Regra: Atualizar o cadastro do CURSO
    @Transactional
    public Curso update(Long id, Curso updatedCurso) {
        Curso cursoExistente = repositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Curso com ID " + id + " não encontrado!"));
        validarNomeCurso(updatedCurso.getNome());
        validarCargaHorariaCurso(updatedCurso.getCargaHoraria());
        validarDescricaoCurso(updatedCurso.getDescricao());
        validarNumVagasCurso(updatedCurso.getNumVagas());
        validarDataInicioCurso(updatedCurso.getDataInicio());
        validarDataTerminoCurso(updatedCurso.getDataInicio(), updatedCurso.getDataTermino());
        cursoExistente.setNome(updatedCurso.getNome());
        cursoExistente.setDescricao(updatedCurso.getDescricao());
        cursoExistente.setCargaHoraria(updatedCurso.getCargaHoraria());
        cursoExistente.setNumVagas(updatedCurso.getNumVagas());
        cursoExistente.setDataInicio(updatedCurso.getDataInicio());
        cursoExistente.setDataTermino(updatedCurso.getDataTermino());
        cursoExistente.setStatus(updatedCurso.getStatus());
        return repositorio.save(cursoExistente);
    }

    // Regra: Buscar todos os cursos cadastrados
    @Transactional(readOnly = true)
    public List<Curso> findAll(){
        return repositorio.findAll();
    }

    // Regra: Buscar cursos pelo ID
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repositorio.findById(id);
    }

    // Regra: Buscar cursos por STATUS
    @Transactional(readOnly=true)
    public List<Curso> findByStatus(String status){
        return repositorio.findByStatus(status);
    }

    // Regra: Buscar cursos pelo NOME
    @Transactional(readOnly=true)
    public List<Curso> findByNome(String nome){
        return repositorio.findByNomeLike(nome);
    }

    // Regra: Buscar cursos por DATA DE INÍCIO
    @Transactional(readOnly=true)
    public List<Curso> findByDataInicio(Instant dataInicio){
        return repositorio.findByDataInicioAfter(dataInicio);
    }

    // Regra: Buscar cursos por NÚMERO DE VAGAS
    @Transactional(readOnly=true)
    public List<Curso> findByNumVagas(Integer numVagas){
        return repositorio.findByNumVagasGreaterThanEqual(numVagas);
    }

    // Regra: Buscar cursos por CARGA HORÁRIA
    @Transactional(readOnly=true)
    public List<Curso> findByCargaHoraria(Integer cargaHoraria){
        return repositorio.findByCargaHorariaGreaterThanEqual(cargaHoraria);
    }
}
