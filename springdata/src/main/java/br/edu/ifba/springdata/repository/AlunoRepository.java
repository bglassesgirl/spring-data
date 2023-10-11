package br.edu.ifba.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{
    //buscar um aluno do banco com o nome
     List<Aluno> findByNomeStartingWith(String nome);
     List<Aluno> findByNomeStartingWithAndIdadeLesThanEqua(String nome, Integer idade);

    //  JPQL
    @Query("SELECT a FROM Aluno a WHERE a.nome like :nome% AND a.idade >= :idade")
    List<Aluno> findByNomeIdadeIgualOuMaior(String nome, Integer idade);

    //  JPQL
    @Query("SELECT a FROM Aluno a INNER JOIN a.disciplina d WHERE a.name  like :nomeAluno% AND a.idade >= :idadeAluno AND d.nome like  :nomeDisciplina")
    List<Aluno> findNomeIdadeIgualOuMaiorMatricula(String nomeAluno, Integer idadeIdade, String nomeDisciplina);
}
