package br.edu.ifba.springdata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Aluno;
import br.edu.ifba.springdata.orm.Disciplina;
import br.edu.ifba.springdata.orm.Professor;
import br.edu.ifba.springdata.repository.AlunoRepository;
import br.edu.ifba.springdata.repository.DisciplinaRepository;
import br.edu.ifba.springdata.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService{

    private DisciplinaRepository disciplinaRepository;//dependencia da classe CrudProfessorService
    // o spring automaticamente cria um objeto com a interface ProfessorRepository,
    //e o injenta para nós no construtor da classe atual ==> injeção de dependencia
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository,
                                 ProfessorRepository professorRepository,
                                 AlunoRepository alunoRepository){
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
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
            System.out.println("5 - matricular alunos");


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
                    this.matricularAluno(in);
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

        System.out.println("Digite o semestre da disciplinas: ");
        int semestre = in.nextInt();//ler a proxima string ate achar um enter ou espaço

        System.out.println("Digite o ID do professor: ");
        long professorId = in.nextLong();//ler a proxima string ate achar um enter ou espaço

        Optional<Professor> optional = professorRepository.findById(professorId);
        if (optional.isPresent()) {
            Professor professor = optional.get();

            List<Aluno> alunos = this.matricular(in);

            Disciplina disciplina = new Disciplina(null, nome, semestre, professor);
            disciplina.setAlunos(alunos);
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

                List<Aluno> alunos = this.matricular(in);

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);
                disciplina.setAlunos(alunos);

                disciplinaRepository.save(disciplina);
                System.out.println("Professor atualizado!! \n");
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

    private List<Aluno> matricular(Scanner in){
        Boolean isTrue = true;
        List<Aluno> alunos = new ArrayList<Aluno>();

        while (isTrue) {
            System.out.println("O ID do aluno a ser matriculado (digite 0 para)");
            Long alunoId = in.nextLong();

            if (alunoId > 0) {
                System.out.println("alunoId: " + alunoId);
                Optional<Aluno> optional = this.alunoRepository.findById(alunoId);
                 if (optional.isPresent()) {
                    alunos.add(optional.get());
                 } else {
                    System.err.println("Nenhum aluno possui ID " + alunoId + "!");

                 }
            } else {
                isTrue = false;
            }
        }
        return alunos;
    }



    private void matricularAluno(Scanner in){
        System.out.println("Digite o ID de disciplina para matricular alunos:");
        Long id = in.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            List<Aluno> novosAlunos = this.matricular(in);
            disciplina.getAlunos().addAll(0, novosAlunos);
            this.disciplinaRepository.save(disciplina);
        }
        else {
            System.out.println("O id da disciplina informado: " + "é inválido\n");
        }
    }

}