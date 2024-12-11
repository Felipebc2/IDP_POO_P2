package org.example.model;

import java.util.List;

public class Grupo {
    private String nome;
    private String descricao;
    private List<Usuario> membros;

    // Construtor
    public Grupo(String nome, String descricao, List<Usuario> membros) {
        this.nome = nome;
        this.descricao = descricao;
        this.membros = membros;
    }

    // Getters
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public List<Usuario> getMembros() { return membros; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setMembros(List<Usuario> membros) { this.membros = membros; }
}
