package br.edu.ifba.springdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifba.springdata.orm.Professor;
import repositoy.ProfessorRepository;

@SpringBootApplication
public class SpringdataApplication implements CommandLineRunner{
	//injenção de dependencias
    private ProfessorRepository repository;
	
	public SpringdataApplication(ProfessorRepository repository){
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'run'");
        Professor professor = new Professor("Luana", "abc");
		this.repository.save(professor);
	}
	

}
