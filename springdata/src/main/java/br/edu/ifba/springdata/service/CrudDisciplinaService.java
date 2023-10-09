package br.edu.ifba.springdata.service;

import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Disciplina;
import br.edu.ifba.springdata.orm.Professor;
import br.edu.ifba.springdata.repository.DisciplinaRepository;
import br.edu.ifba.springdata.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService{

    private DisciplinaRepository disciplinaRepository;//dependencia da classe CrudProfessorService
    // o spring automaticamente cria um objeto com a interface ProfessorRepository,
    //e o injenta para nós no construtor da classe atual ==> injeção de dependencia
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository,
                                 ProfessorRepository professorRepository){
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner in){
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println( "\nQual entidade você deseja executar?");
			System.out.println("0 - voltar ao menu anterior");
			System.out.println("1 - cadastrar novo Disciplina");
            System.out.println("2 - atualizar um Disciplina");
            System.out.println("3 - listar as Disciplina cadastrados");
            System.out.println("4 - delete uma Disciplina");

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
                default:
                    isTrue = false;
                    break;
            }
        }

    }

    private void cadastrar(Scanner in){
        System.out.println("Digite o nome da disciplina: ");
        String nome = in.next(); //ler a proxima string ate achar um enter ou espaço


        System.out.println();
        System.out.println("Digite o semestre da disciplinas: ");
        int semestre = in.nextInt();//ler a proxima string ate achar um enter ou espaço

        System.out.println();
        System.out.println("Digite o ID do professor: ");
        long professorId = in.nextLong();//ler a proxima string ate achar um enter ou espaço

        Optional<Professor> optional = professorRepository.findById(professorId);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(null, nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo!! \n");
        }else{
            System.out.println("Professor ID: " + professorId + "invalido!");
        }

    }


    private void atualizar(Scanner in) {
        System.out.print("Digite o ID da disciplina a ser atualizado: ");
        Long id = in.nextLong();

        Optional<Disciplina>  optionalDisciplina = this.disciplinaRepository.findById(id);
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            System.out.println("Digite o nome da disciplina: ");
            String nome = in.next(); //ler a proxima string ate achar um enter ou espaço

            System.out.println("Digite o semesttre do disciplina: ");
            Integer semestre = in.nextInt(); //ler a proxima string ate achar um enter ou espaço

            System.out.println("Digite o ID do professor: ");
            long professorId = in.nextLong();//ler a proxima string ate achar um enter ou espaço

            Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Salvo!! \n");
            }else{
                System.out.println("Professor ID: " + professorId + "invalido!");
            }
        }
        else{
             System.out.println("O id da disciplina informado: " + id + " é nvalido!");
        }

    }

    private void visulizar(Scanner in) {
       Iterable<Disciplina>  disciplinas = this.disciplinaRepository.findAll();

       for (Disciplina disciplina : disciplinas) {
          System.out.println(disciplina);
       }

       System.out.println();
    }

    private void deletar(Scanner in) {
        System.out.print("Digite o ID da disciplina a ser deletado: ");
        Long id = in.nextLong();
        this.disciplinaRepository.deleteById(id);; //lançara um exception se nao achar um id passado na tabela
        System.out.println("Disciplina deletado!!\n");

    }

}