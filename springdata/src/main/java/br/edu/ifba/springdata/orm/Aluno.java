package br.edu.ifba.springdata.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(nullable = false)
       private String nome;
       private Integer idade;

       //a entidade está sendo mapiada pelo atributo aluno da classe disciplina em um relacionamento ManyToMany
       @ManyToMany(mappedBy = "alunos")
       List<Disciplina> disciplina;



}
