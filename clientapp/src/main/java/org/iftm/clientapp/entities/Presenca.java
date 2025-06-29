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
@Table(name="tb_presenca")
public class Presenca implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "presenca_aluno", nullable = false, length = 50)
    private String aluno;
    @Column(name = "presenca_curso", nullable = false, length = 50)
    private String curso;
    @Column(name = "presenca_data", nullable = false)
    private Instant dataPresenca;
    @Column(name = "presenca_status", nullable = false)
    private String status;
    
    public Presenca() {
    }

    public Presenca(Long id, String aluno, String curso, Instant dataPresenca, String status) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.dataPresenca = dataPresenca;
        this.status = status;
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

    public Instant getDataPresenca() {
        return dataPresenca;
    }

    public void setDataPresenca(Instant dataPresenca) {
        this.dataPresenca = dataPresenca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

}
