package br.edu.ifba.springdata;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifba.springdata.service.CrudProfessorService;


@SpringBootApplication
public class SpringdataApplication implements CommandLineRunner{
	private CrudProfessorService professorService;

	//os objetos  passadp por parametros são injetados automaticamente pelo spring
	//pq suas classes possuem a anotação @Service
	public SpringdataApplication(CrudProfessorService professorService){
		this.professorService = professorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner in = new Scanner(System.in);

		while(isTrue){
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - sair");
			System.out.println("1 - professor");
			int opcao = in.nextInt();

			switch(opcao){
				case 1:
				    this.professorService.menu(in);
				    break;
				default:
				 isTrue = false;
				 break;
			}
			in.close();

		}
	}
	
}
