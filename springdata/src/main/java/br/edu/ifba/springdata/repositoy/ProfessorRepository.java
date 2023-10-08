package br.edu.ifba.springdata.repositoy;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}