package org.iftm.clientapp.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_matricula")
public class Matricula implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="matricula_aluno", nullable=false, length=50)
    private String aluno;
    @Column(name="matricula_curso", nullable=false, length=50)
    private String curso;
    @Column(name = "matricula_data_inicio", nullable = false)
    private Instant dataInicio;
    @Column(name = "matricula_data_fim", nullable = false)
    private Instant dataFim;

    public Matricula() {
    }

    public Matricula(Long id, String aluno, String curso, Instant dataInicio, Instant dataFim) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public void setDataFim(Instant dataFim) {
        this.dataFim = dataFim;
    }

    
    
}
