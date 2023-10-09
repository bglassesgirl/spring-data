package br.edu.ifba.springdata.service;

import java.util.Scanner;

import java.util.List;
import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Aluno;
import br.edu.ifba.springdata.repository.AlunoRepository;

@Service
public class RelatorioService {

    private AlunoRepository alunoRepository;

    public RelatorioService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void menu(Scanner in){
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println( "\nQual relatório você deseja");
			System.out.println("0 - voltar ao menu anterior");
			System.out.println("1 - alunos por nome ");
            System.out.println("2 - alunos por nome E IDADE MENOR OU IGUAL");


            int opcao = in.nextInt();

			switch(opcao){
				case 1:
                    this.alunosPorNome(in);
				    break;
                case 2:
                    this.alunosPorNomeIdadeMenorOuIgual(in);
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
}
