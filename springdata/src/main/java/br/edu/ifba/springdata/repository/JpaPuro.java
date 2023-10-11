package br.edu.ifba.springdata.repository;

import br.edu.ifba.springdata.orm.Professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaPuro {

    private EntityManagerFactory emf;
    private EntityManager em;

    public void JpaPuroEx(){
        emf = Persistence.createEntityManagerFactory("JPA");
        em = emf.createEntityManager();
    }
    public Professor findAllById(Integer id){
        em.getTransaction().begin();
        Professor professor = em.find(Professor.class, id);
        em.getTransaction().commit();
        em.close();
        return professor;
    }
    public void save(Professor professor){
        em.getTransaction().begin();
        em.persist(professor);
        em.getTransaction();
        em.getTransaction().commit();
        em.close();
    }
}
