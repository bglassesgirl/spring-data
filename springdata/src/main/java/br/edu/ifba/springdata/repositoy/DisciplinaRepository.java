package br.edu.ifba.springdata.repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.springdata.orm.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long>  {
    
}
