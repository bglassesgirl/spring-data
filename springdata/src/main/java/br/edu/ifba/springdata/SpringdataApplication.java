package br.edu.ifba.springdata;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifba.springdata.service.CrudAlunoService;
import br.edu.ifba.springdata.service.CrudDisciplinaService;
import br.edu.ifba.springdata.service.CrudProfessorService;

@SpringBootApplication
public class SpringdataApplication implements CommandLineRunner{
	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;
	private CrudAlunoService alunoService;

	//os objetos  passadp por parametros são injetados automaticamente pelo spring
	//pq suas classes possuem a anotação @Service
	public SpringdataApplication(CrudProfessorService professorService,
								CrudDisciplinaService disciplinaService,
								CrudAlunoService alunoService){
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunoService = alunoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner in = new Scanner(System.in);

		while(isTrue){
			System.out.println("Qual entidade você deseja interagi1r?");
			System.out.println("0 - sair");
			System.out.println("1 - professor");
			System.out.println("2 - disciplina");
			System.out.println("3 - aluno");

			int opcao = in.nextInt();

			switch(opcao){
				case 1:
				    this.professorService.menu(in);
				    break;
				case 2:
				    this.disciplinaService.menu(in);
				    break;
				case 3:
				    this.alunoService.menu(in);
				    break;
				default:
				 isTrue = false;
				 break;
			}

		}
	}
}
