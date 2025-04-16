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
@Table(name="tb_curso")
public class Curso implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "curso_name", nullable = false, length = 50)
    private String nome;
    @Column(name = "curso_descricao", nullable = false, length = 255)
    private String descricao;
    @Column(name = "curso_carga_horaria", nullable = false)
    private Integer cargaHoraria;
    @Column(name = "curso_num_vagas", nullable = false)
    private Integer numVagas;
    @Column(name = "curso_data_inicio", nullable = false)
    private Instant dataInicio;
    @Column(name = "curso_data_termino", nullable = false)
    private Instant dataTermino;
    @Column(nullable = false)
    private String status;

    public Curso() {
    }

    public Curso(String nome, String descricao, Integer cargaHoraria, Integer numVagas, Instant dataInicio,
            Instant dataTermino, String status) {
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.numVagas = numVagas;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
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


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getCargaHoraria() {
        return cargaHoraria;
    }


    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }


    public Integer getNumVagas() {
        return numVagas;
    }


    public void setNumVagas(Integer numVagas) {
        this.numVagas = numVagas;
    }


    public Instant getDataInicio() {
        return dataInicio;
    }


    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }


    public Instant getDataTermino() {
        return dataTermino;
    }


    public void setDataTermino(Instant dataTermino) {
        this.dataTermino = dataTermino;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
