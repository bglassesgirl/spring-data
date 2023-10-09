package br.edu.ifba.springdata.service;

import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Disciplina;
import br.edu.ifba.springdata.orm.Professor;
import br.edu.ifba.springdata.repository.ProfessorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CrudProfessorService{

    private ProfessorRepository professorRepository;//dependencia da classe CrudProfessorService
    // o spring automaticamente cria um objeto com a interface ProfessorRepository,
    //e o injenta para nós no construtor da classe atual ==> injeção de dependencia

    public CrudProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    //@Transactional
    public void menu(Scanner in){
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println( "\nQual entidade você deseja executar?");
			System.out.println("0 - voltar ao menu anterior");
			System.out.println("1 - cadastrar novo Professor");
            System.out.println("2 - atualizar um Professor");
            System.out.println("3 - listar os professores cadastrados");
            System.out.println("4 - delete um professor");
            System.out.println("5 - visualziar UM professor");

            int opcao = in.nextInt();

			switch(opcao){
				case 1:
                    this.cadastrar(in);
				    break;
                case 2:
                    this.atualizar(in);
                    break;
                case 3:
                    this.visulizar(in);
                    break;
                case 4:
                    this.deletar(in);
                    break;
                case 5:
                    this.visualizarProfessor(in);
                    break;
				default:
				 isTrue = false;
				 break;
			}

        }
        System.out.println();
    }


    private void cadastrar(Scanner in){
        System.out.println("Digite o nome do professor: ");
        String nome = in.next(); //ler a proxima string ate achar um enter ou espaço

        System.out.println("Digite o prontuario do professor: ");
        String prontuario = in.next(); //ler a proxima string ate achar um enter ou espaço

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no banco!!\n");
    }

    private void atualizar(Scanner in) {
        System.out.print("Digite o ID do professor a ser atualizado: ");
        Long id = in.nextLong();

        Optional<Professor>  optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            System.out.println("Digite o nome do professor: ");
            String nome = in.next(); //ler a proxima string ate achar um enter ou espaço

            System.out.println("Digite o prontuario do professor: ");
            String prontuario = in.next(); //ler a proxima string ate achar um enter ou espaço

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            professorRepository.save(professor); //atualiza o objeto no BD
            System.out.println("Professor atualizado no banco!!\n");
        }else{
            System.out.println("O id do professor informado: "+ id + "é inválido\n");
        }

    }

    private void visulizar(Scanner in) {
       Iterable<Professor> professores = this.professorRepository.findAll();
                // for (Professor professor : professores) {
                //System.out.println(professores);
                //}
        professores.forEach(professor -> {
             System.out.println(professor);
        });
       System.out.println();
    }

    private void deletar(Scanner in) {
        System.out.print("Digite o ID do professor a ser deletado: ");
        Long id = in.nextLong();
        this.professorRepository.deleteById(id);; //lançara um exception se nao achar um id passado na tabela
        System.out.println("Professor deletado!!\n");

    }

     //@Transactional
     private void visualizarProfessor(Scanner in) {
        System.out.print("Digite o ID do professor que deseja procurar: ");
        Long id = in.nextLong();

        Optional<Professor>  optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            Professor professor = optional.get();

            System.out.println("Professor: {");
            System.out.println("ID: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("Prontuario: " + professor.getProntuario());
            System.out.println("Disciplinas: [");

            for (Disciplina disciplina : professor.getDisciplinas()) {
                System.out.println("\tID:" + disciplina.getId());
                System.out.println("\tNome:" + disciplina.getNome());
                System.out.println("\tSemestre:" + disciplina.getSemestre());

            }
             System.out.println("]\n");

        }

    }

}