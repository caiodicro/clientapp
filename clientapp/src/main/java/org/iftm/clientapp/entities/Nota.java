package org.iftm.clientapp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_nota")
public class Nota implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nota_aluno", nullable=false, length=50)
    private String aluno;
    @Column(name = "nota_01", nullable = false, unique=false)
    private Double nota01;
    @Column(name = "nota_02", nullable = false, unique=false)
    private Double nota02;
    @Column(name = "nota_03", nullable = false, unique=false)
    private Double nota03;

    public Nota(String aluno, Long id, Double nota01, Double nota02, Double nota03) {
        this.aluno = aluno;
        this.id = id;
        this.nota01 = nota01;
        this.nota02 = nota02;
        this.nota03 = nota03;
    }
    public Nota() {
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
    public Double getNota01() {
        return nota01;
    }
    public void setNota01(Double nota01) {
        this.nota01 = nota01;
    }
    public Double getNota02() {
        return nota02;
    }
    public void setNota02(Double nota02) {
        this.nota02 = nota02;
    }
    public Double getNota03() {
        return nota03;
    }
    public void setNota03(Double nota03) {
        this.nota03 = nota03;
    }

}
