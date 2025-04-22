package org.iftm.clientapp;

import java.time.Instant;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientappApplication implements CommandLineRunner {
	// Injeção de dependencia da classe CursoRepository
	// Permite SpringBoot instanciar objetos da classe
	@Autowired
	private CursoRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ClientappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Instanciar um objeto da classe Curso
		Curso curso = new Curso();
		curso.setNome("Informatica");
		curso.setDescricao("Primeiro curso cadastrado - TESTE");
		curso.setCargaHoraria(40);
		curso.setNumVagas(20);
		curso.setDataInicio(Instant.parse("2024-01-01T07:00:00.00Z"));
		curso.setDataTermino(Instant.parse("2024-06-01T07:00:00.00Z"));
		curso.setStatus("Ativo");
		repositorio.save(curso);

		// Deletar tudo
		//repositorio.deleteAll();
		
		// Instanciar um objeto da classe Curso
		Curso curso2 = new Curso("Ingles", "Segundo curso cadastrado - TESTE", 50, 10, Instant.parse("2024-01-01T07:00:00.00Z"), Instant.parse("2024-06-01T07:00:00.00Z"), "Ativo");
		repositorio.save(curso2);
	}
}
