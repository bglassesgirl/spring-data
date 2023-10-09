package br.edu.ifba.springdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long>{

}
