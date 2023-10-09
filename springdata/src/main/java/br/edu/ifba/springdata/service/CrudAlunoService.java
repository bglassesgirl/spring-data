package br.edu.ifba.springdata.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.edu.ifba.springdata.orm.Aluno;
import br.edu.ifba.springdata.orm.Disciplina;
import br.edu.ifba.springdata.repository.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class CrudAlunoService {

    private AlunoRepository alunoRepository;

    public CrudAlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public void menu(Scanner in){
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println( "\nQual entidade você deseja executar?");
			System.out.println("0 - voltar ao menu anterior");
			System.out.println("1 - cadastrar novo Aluno");
            System.out.println("2 - atualizar um Aluno");
            System.out.println("3 - listar os Alunos cadastrados");
            System.out.println("4 - delete uma Aluno");
            System.out.println("5 - visualize um Aluno");


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
                    this.visualizeAluno(in);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }

    }

    private void cadastrar(Scanner in) {
        System.out.println("Digite o nome do aluno: ");
        String nome = in.next();

        System.out.println("Digite a idade do aluno: ");
        Integer idade = in.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno salvo no Banco:) !! \n");

    }

    private void atualizar(Scanner in) {
        System.out.print("Digite o ID do Aluno a ser atualizado: ");
        Long id = in.nextLong();

        Optional<Aluno>  optional = this.alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();

            System.out.println("Digite o nome do Aluno: ");
            String nome = in.next();

            System.out.println("Digite a idade do Aluno: ");
            Integer idade = in.nextInt();

            aluno.setNome(nome);
            aluno.setIdade(idade);
            alunoRepository.save(aluno); //atualiza o objeto no BD
            System.out.println("Aluno atualizado no banco!!\n");
        }
        else{
             System.out.println("O id do aluno informado: " + id + " é nvalido!");
        }

    }

    private void visulizar(Scanner in) {
        Iterable<Aluno>  alunos = this.alunoRepository.findAll();

        for (Aluno aluno : alunos) {
           System.out.println(aluno);
        }

        System.out.println();
    }

    private void deletar(Scanner in) {
        System.out.print("Digite o ID do aluno a ser deletado: ");
        Long id = in.nextLong();
        this.alunoRepository.deleteById(id);; //lançara um exception se nao achar um id passado na tabela
        System.out.println("Aluno deletado!!\n");
    }

    @Transactional
    private void visualizeAluno(Scanner in) {
        System.out.print("Digite o ID do Aluno que deseja procurar: ");
        Long id = in.nextLong();

       Optional<Aluno>  optional = this.alunoRepository.findById(id);
        if (optional.isPresent()) {
            Aluno aluno = optional.get();

            System.out.println("ID: " + aluno.getId());
            System.out.println("Idade: " + aluno.getNome());
            System.out.println("Disciplinas: [");

            if (aluno.getDisciplinas() != null) {
                for (Disciplina disciplina : aluno.getDisciplinas()) {
                System.out.println("\tNome:" + disciplina.getNome());
                System.out.println("\tSemestre:" + disciplina.getSemestre());
                System.out.println();

            }
             System.out.println("]\n");
            } else {
                System.out.println("O id do aluno informado: " + id + " é nvalido!");
            }

        }
    }

}
