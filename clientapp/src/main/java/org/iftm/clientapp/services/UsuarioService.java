package org.iftm.clientapp.services;

import org.iftm.clientapp.entities.Usuario;
import org.iftm.clientapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    // Regra: Validação do cadastro do NOME DO USUÁRIO
    public void validarNomeAluno(String nome) {
        if (nome == null || nome.isBlank() || nome.length() > 50) {
            throw new IllegalArgumentException(
                    "Nome Inválido! Nome do usuário deve ser entre 1 e 50 caracteres!");
        }
        if (nome.charAt(0) == ' ') {
            throw new IllegalArgumentException(
                    "Nome Inválido! Não é possível cadastrar o nome do usuário com espaço no primeiro caracter!");
        }
    }

    // Regra: Validação do cadastro do CPF DO USUÁRIO
    public void validarCpfAluno(String cpf) {
        if (cpf == null || cpf.isBlank() || cpf.length() > 11 || cpf.length() < 11) {
            throw new IllegalArgumentException(
                    "CPF Inválido! CPF do usuário deve conter 11 números!");
        }
        if (cpf.charAt(0) == ' ') {
            throw new IllegalArgumentException(
                    "CPF Inválido! Não é possível cadastrar o cpf do usuário com espaço no primeiro caracter!");
        }
    }

    // Regra: Validação do cadastro do EMAIL DO USUÁRIO
    public void validarEmailAluno(String email) {
        if (email == null || email.isBlank() || email.length() > 50) {
            throw new IllegalArgumentException(
                    "Email Inválido! Email do usuário deve ser entre 1 e 50 caracteres!");
        }
        if (email.charAt(0) == ' ') {
            throw new IllegalArgumentException(
                    "Email Inválido! Não é possível cadastrar o email do usuário com espaço no primeiro caracter!");
        }
    }

    // Regra: Validação do cadastro da SENHA DO USUÁRIO
    public void validarSenhaAluno(String senha) {
        if (senha   == null || senha.isBlank() || senha.length() > 20) {
            throw new IllegalArgumentException(
                    "Senha Inválida! Senha do usuário não pode exceder 20 caracteres!");
        }
    }

    @Transactional
    public Usuario insert(Usuario insertUsuario){
        return repositorio.save(insertUsuario);
    }

}
