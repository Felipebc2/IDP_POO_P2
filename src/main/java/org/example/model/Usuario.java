package org.example.model;

public class Usuario {
    private String nome; // Usado para autenticação
    private String senha; // Usado para autenticação
    private String projetos; // Texto descritivo
    private int tempoAtividadeMeses;

    // Construtor
    public Usuario(String nome, String senha, String projetos, int tempoAtividadeMeses) {
        this.nome = nome;
        this.senha = senha;
        this.projetos = projetos;
        this.tempoAtividadeMeses = tempoAtividadeMeses;
    }

    // Getters
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
    public String getProjetos() { return projetos; }
    public int getTempoAtividadeMeses() { return tempoAtividadeMeses; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setProjetos(String projetos) { this.projetos = projetos; }
    public void setTempoAtividadeMeses(int tempoAtividadeMeses) { this.tempoAtividadeMeses = tempoAtividadeMeses; }
}
