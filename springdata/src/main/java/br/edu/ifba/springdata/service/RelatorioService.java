package br.edu.ifba.springdata.service;

import java.util.Scanner;

import java.util.List;
import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Aluno;
import br.edu.ifba.springdata.orm.Professor;
import br.edu.ifba.springdata.repository.AlunoRepository;
import br.edu.ifba.springdata.repository.ProfessorRepository;

@Service
public class RelatorioService {

    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;

    public RelatorioService(AlunoRepository alunoRepository,
                            ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner in){
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println( "\nQual relatório você deseja");
			System.out.println("0 - voltar ao menu anterior");
			System.out.println("1 - alunos por nome ");
            System.out.println("2 - alunos por nome E IDADE MENOR OU IGUAL");
            System.out.println("3 - alunos por nome e idade maior ou igual ");
            System.out.println("4 - alunos matriculados com um dado nome e idade maior ou igual ");
            System.out.println("5 - alunos matriculados com um dado nome e idade maior ou igual ");


            int opcao = in.nextInt();

			switch(opcao){
				case 1:
                    this.alunosPorNome(in);
				    break;
                case 2:
                    this.alunosPorNomeIdadeMenorOuIgual(in);
				    break;
                case 3:
                    this.alunosPorNomeIdadeMaiorOuIgual(in);
				    break;
                case 4:
                    this.alunosMatriculadosComNomeIdadeMaiorOuIgual(in);
				    break;
                case 5:
                    this.professoresAtribuidos(in);
				    break;
                default:
                    isTrue = false;
                    break;
            }
        }

        System.out.println();


    }

    private void alunosPorNome(Scanner in) {
        System.out.println("Nome: ");
        String nome = in.nextLine();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

    }
     private void alunosPorNomeIdadeMenorOuIgual(Scanner in) {
        System.out.println("Nome: ");
        String nome = in.nextLine();

        System.out.println("Idade: ");
        Integer idade = in.nextInt();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLesThanEqua(nome, idade);
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

    }

     private void alunosPorNomeIdadeMaiorOuIgual(Scanner in) {
        System.out.println("Nome: ");
        String nome = in.nextLine();

        System.out.println("Idade: ");
        Integer idade = in.nextInt();

        List<Aluno> alunos = this.alunoRepository.findByNomeIdadeIgualOuMaior(nome, idade);
        alunos.forEach(System.out::println);

    }

        private void alunosMatriculadosComNomeIdadeMaiorOuIgual(Scanner in) {
            System.out.println("Nome: ");
            String nomeAluno = in.nextLine();

            System.out.println("Idade: ");
            Integer idadeAluno = in.nextInt();

            System.out.println("Nome da disciplina: ");
            String nomeDisciplina = in.nextLine();

            List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaiorMatricula(nomeAluno, idadeAluno, nomeDisciplina);
            alunos.forEach(System.out::println);

        }

        private void professoresAtribuidos(Scanner in) {
            System.out.println("Nome do professor: ");
            String nomeProfessor = in.nextLine();

            System.out.println("Nome da disciplina: ");
            String nomeDisciplina= in.nextLine();


            List<Professor> professores = this.professorRepository.findProfessorAtribuido(nomeProfessor, nomeDisciplina);
            professores.forEach(System.out::println);

        }
}