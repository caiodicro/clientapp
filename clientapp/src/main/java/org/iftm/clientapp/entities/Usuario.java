package org.iftm.clientapp.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_name", nullable = false, length = 50)
    private String nome;
    @Column(name = "usuario_cpf", nullable = false, length = 11)
    private String cpf;
    @Column(name = "usuario_tipo", nullable = false, length = 11)
    private String tipo;
    @Column(name = "usuario_email", nullable = false, length = 50)
    private String email;
    @Column(name = "usuario_senha", nullable = false, length = 20)
    @JsonIgnore
    private String senha;
    @Column(name = "usuario_nascimento", nullable = false)
    private Instant nascimento;
    @Column(name = "usuario_endereco", nullable = false, length = 255)
    private String endereco;
    @Column(nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "matricula_fk", referencedColumnName = "id")
    @JsonIgnoreProperties("usuario")
    private Matricula matricula;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String cpf, String tipo, String email, String senha, Instant nascimento,
            String endereco, String status, Matricula matricula) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.status = status;
        this.matricula = matricula;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getNascimento() {
        return nascimento;
    }

    public void setNascimento(Instant nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
