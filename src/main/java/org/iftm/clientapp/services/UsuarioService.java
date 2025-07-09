package org.iftm.clientapp.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

import org.iftm.clientapp.entities.Usuario;
import org.iftm.clientapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new IllegalArgumentException("Email Inválido! Email do usuário deve ser entre 1 e 50 caracteres!");
        }
        if (email.charAt(0) == ' ') {
            throw new IllegalArgumentException(
                    "Email Inválido! Não é possível cadastrar o email do usuário com espaço no primeiro caracter!");
        }
    }

    // Regra: Validação do cadastro da SENHA DO USUÁRIO
    public void validarSenhaAluno(String senha) {
        if (senha == null || senha.isBlank() || senha.length() > 20) {
            throw new IllegalArgumentException("Senha inválida! A senha deve ter entre 1 e 20 caracteres.");
        }
    }

    // Regra: Validação do cadastro da DATA DE NASCIMENTO DO USUÁRIO
    public void validarNascimentoUsuario(Instant nascimento) {
        if (nascimento == null) {
            throw new IllegalArgumentException("Data de nascimento inválida! O campo não pode estar vazio.");
        }

        LocalDate dataNascimento = LocalDate.ofInstant(nascimento, ZoneId.systemDefault());
        LocalDate hoje = LocalDate.now();

        if (dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException("Data de nascimento inválida! Não pode ser uma data futura.");
        }

        int idade = Period.between(dataNascimento, hoje).getYears();
        if (idade < 10) {
            throw new IllegalArgumentException(
                    "Data de nascimento inválida! O usuário deve ter no mínimo 10 anos de idade.");
        }
    }

    // Regra: Validação do cadastro do ENDEREÇO DO USUÁRIO
    public void validarEnderecoUsuario(String endereco) {
        if (endereco == null || endereco.isBlank() || endereco.length() > 255) {
            throw new IllegalArgumentException("Endereço inválido! Deve ter entre 1 e 255 caracteres.");
        }
        if (endereco.charAt(0) == ' ') {
            throw new IllegalArgumentException("Endereço inválido! Não pode começar com espaço.");
        }
    }

    // Regra: Validação do cadastro do STATUS DO USUÁRIO
    public void validarStatusUsuario(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status inválido! O campo é obrigatório.");
        }
        if (!status.equalsIgnoreCase("Ativo") && !status.equalsIgnoreCase("Inativo")) {
            throw new IllegalArgumentException("Status inválido! Use 'Ativo' ou 'Inativo'.");
        }
    }

    // Regra: Cadastrar um novo USUÁRIO
    @Transactional
    public Usuario insert(Usuario insertUsuario) {
        validarNomeAluno(insertUsuario.getNome());
        validarCpfAluno(insertUsuario.getCpf());
        validarEmailAluno(insertUsuario.getEmail());
        validarSenhaAluno(insertUsuario.getSenha());
        validarNascimentoUsuario(insertUsuario.getNascimento());
        validarEnderecoUsuario(insertUsuario.getEndereco());
        validarStatusUsuario(insertUsuario.getStatus());
        return repositorio.save(insertUsuario);
    }

    // Regra: Atualizar um novo USUÁRIO
    @Transactional
    public Usuario update(Long id, Usuario updatedUsuario) {
        Usuario usuarioExistente = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + id + " não encontrado!"));
        validarNomeAluno(updatedUsuario.getNome());
        validarCpfAluno(updatedUsuario.getCpf());
        validarEmailAluno(updatedUsuario.getEmail());
        validarSenhaAluno(updatedUsuario.getSenha());
        validarNascimentoUsuario(updatedUsuario.getNascimento());
        validarEnderecoUsuario(updatedUsuario.getEndereco());
        validarStatusUsuario(updatedUsuario.getStatus());
        usuarioExistente.setNome(updatedUsuario.getNome());
        usuarioExistente.setCpf(updatedUsuario.getCpf());
        usuarioExistente.setEmail(updatedUsuario.getEmail());
        usuarioExistente.setSenha(updatedUsuario.getSenha());
        usuarioExistente.setNascimento(updatedUsuario.getNascimento());
        usuarioExistente.setEndereco(updatedUsuario.getEndereco());
        usuarioExistente.setStatus(updatedUsuario.getStatus());
        return repositorio.save(usuarioExistente);
    }

    // Regra: Excluir um USUÁRIO
    @Transactional
    public void delete(Long id) {
        if (!repositorio.existsById(id)) {
            throw new IllegalArgumentException("Usuário com ID " + id + " não encontrado!");
        }
        repositorio.deleteById(id);
    }

    // Regra: Excluir todos os USUÁRIOS
    @Transactional
    public void deleteAll() {
        repositorio.deleteAll();
    }

    // Regra: Buscar usuário por ID
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário com ID " + id + " não encontrado."));
    }

    // Regra: Buscar usuários por STATUS
    @Transactional(readOnly = true)
    public List<Usuario> findByStatus(Usuario findUsuario) {
        return repositorio.findByStatus(findUsuario.getStatus());
    }

    // Regra: Buscar usuários por EMAIL
    @Transactional(readOnly = true)
    public Usuario findByEmail(String email) {
        List<Usuario> usuarios = repositorio.findByEmailLike(email);
        if (usuarios.isEmpty()) {
            throw new NoSuchElementException("Usuário com email " + email + " não encontrado.");
        }
        return usuarios.get(0);
    }

}
