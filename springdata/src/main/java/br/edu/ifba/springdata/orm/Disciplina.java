package br.edu.ifba.springdata.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplina")
public class Disciplina{

    private static final boolean t = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String nome;
    private Integer semestre;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disciplina_aluno",
                joinColumns = @JoinColumn(name = "disciplina_fk"),
                inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
    List<Aluno> alunos;

    public Disciplina() {
    }

    public Disciplina(Long id, String nome, Integer semestre, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professor;
    }

    public static boolean isT() {
        return t;
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

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", nome=" + nome + ", semestre=" + semestre + ", professor=" + professor
                + ", alunos=" + alunos + "]";
    }

}