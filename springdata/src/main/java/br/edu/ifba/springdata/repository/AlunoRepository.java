package br.edu.ifba.springdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{
    //buscar um aluno do banco com o nome
    List<Aluno> findByNomeStartingWith(String nome);
     List<Aluno> findByNomeStartingWithAndIdadeLesThanEqua(String nome, Integer idade);


}
