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

        public Aluno() {
        }

        public Aluno(Long id, String nome, Integer idade, List<Disciplina> disciplina) {
            this.id = id;
            this.nome = nome;
            this.idade = idade;
            this.disciplina = disciplina;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Integer getIdade() {
            return idade;
        }

        public void setIdade(Integer idade) {
            this.idade = idade;
        }

        public List<Disciplina> getDisciplina() {
            return disciplina;
        }

        public void setDisciplina(List<Disciplina> disciplina) {
            this.disciplina = disciplina;
        }

        @Override
        public String toString() {
            return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + ", disciplina=" + disciplina + "]";
        }


}
