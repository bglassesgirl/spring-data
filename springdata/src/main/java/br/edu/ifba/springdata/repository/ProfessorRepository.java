package br.edu.ifba.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {
       //sql nativa
       @Query(nativeQuery  = true,
       value = "SELECT * FROM professores  p INNER JOIN disciplina d ON p.id = d.professor_id WHERE p.nome like : nomeProfessor% AND d.nome like :nomeDisciplina %")
       List<Professor> findProfessorAtribuido(String nomeProfessor, String nomeoDisciplina);

}