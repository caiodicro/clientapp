package org.iftm.clientapp;

import java.time.Instant;

import org.iftm.clientapp.entities.Curso;
import org.iftm.clientapp.repositories.UsuarioRepository;
import org.iftm.clientapp.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientappApplication implements CommandLineRunner {
	// Injeção de dependencia da classe CursoRepository
	// Permite SpringBoot instanciar objetos da classe
	@Autowired
	private CursoService repositorio;
	@Autowired
	private UsuarioRepository repositorioUsuario;

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
		try{
			repositorio.insert(curso);
		}catch(IllegalArgumentException e){
			System.out.println("\n"+e.getMessage()+"\n");
		}
		/* 
		// Deletar tudo
		//repositorio.deleteAll();
		
		// Instanciar um objeto da classe Curso
		Curso curso2 = new Curso("Ingles", "Segundo curso cadastrado - TESTE", 50, 10, Instant.parse("2024-01-01T07:00:00.00Z"), Instant.parse("2024-06-01T07:00:00.00Z"), "Ativo");
		repositorio.save(curso2);

		// Methods que retorna uma lista na classe CursoRepository
		List <Curso> cursos = repositorio.findByStatus("Ativo");
		System.out.println("******Início da Lista******");
		System.out.println("******Quantidade de cursos ativos******");
		System.out.println(cursos.size());
		System.out.println("******Fim da Lista******\n\n");

		cursos = repositorio.findByNumVagasGreaterThanEqual (20);
		System.out.println("******Início da Lista******");
		System.out.println("******Cursos com numero de vagas maior ou igual a 20******");
		System.out.println("Quantidade: " + cursos.size());
		for(int i = 0; i < cursos.size(); i++){
			System.out.println(cursos.get(i).getNome());
		}
		System.out.println("******Fim da Lista******\n\n");

		cursos = repositorio.findByCargaHorariaGreaterThanEqual(30);
		System.out.println("******Início da Lista******");
		System.out.println("******Cursos com caraga horaria igual a 30******");
		System.out.println("Quantidade: " + cursos.size());
		for(int i = 0; i < cursos.size(); i++){
			System.out.println(cursos.get(i).getNome());
		}
		System.out.println("******Fim da Lista******\n\n");

		cursos = repositorio.findByDataInicioAfter(Instant.parse("2024-07-01T07:00:00.00Z"));
		System.out.println("******Início da Lista******");
		System.out.println("******Cursos que começam depois de data 01/07/2024******");
		System.out.println("Quantidade: " + cursos.size());
		for(int i = 0; i < cursos.size(); i++){
			System.out.println(cursos.get(i).getNome());
		}
		System.out.println("******Fim da Lista******\n\n");


		//(Long id, String nome, String cpf, String email, String senha, Instant nascimento, String endereco, String status)
		Usuario u = new Usuario(null, "Caio Passos da Silva", "11111111111", "Professor", "email", "senha", Instant.now(), "endereco", "x");
		repositorioUsuario.save(u);
		*/
	}
}
