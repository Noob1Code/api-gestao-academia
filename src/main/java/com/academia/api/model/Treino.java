/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.academia.api.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Kayqu
 */
@Entity
@Table(name = "treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelTreino nivel;

    @ManyToMany(mappedBy = "treinos", fetch = FetchType.LAZY)
    private Set<Aluno> alunos = new HashSet<>();

    public Treino() {
    }

    public Treino(Long id, String nome, String descricao, NivelTreino nivel, Set<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
        this.alunos = alunos;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NivelTreino getNivel() {
        return nivel;
    }

    public void setNivel(NivelTreino nivel) {
        this.nivel = nivel;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Treino treino = (Treino) o;
        return Objects.equals(id, treino.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Treino{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", nivel=" + nivel
                + '}';
    }
}
