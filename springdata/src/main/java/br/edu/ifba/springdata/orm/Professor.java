package br.edu.ifba.springdata.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = false)
    private String prontuario;

    //fetch
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;

    public Professor(){}

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
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

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
    }

    //=>define o objeto professor_id null ao apagar um professor associado a disciplina
    // ON REMOVE, SET NULL
    //sem esse metodo, como o professor_id é chave estrangeira de disciplina (OneTMany), ele define um professor
    //obrigatorio a disciplina, ent, caso apague o professor que tem uma disciplina, apaga a disciplina tbm
    @PreRemove
    public void atualizarDisciplinaOnDelete(){
        System.out.println("atualizarDisciplinaOnDelete");
        for (Disciplina disciplina : this.getDisciplinas()) {
            disciplina.setProfessor(null);
        }
    }


}
