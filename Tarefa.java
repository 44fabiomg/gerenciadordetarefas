package com.example.gerenciadordetarefas;

public class Tarefa {
    private long id;
    private String descricao;
    private String dataVencimento;
    private int prioridade;
    private String categoria;
    private boolean concluida;

    public Tarefa(long id, String descricao, String dataVencimento, int prioridade, String categoria, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.concluida = concluida;
    }

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(String dataVencimento) { this.dataVencimento = dataVencimento; }
    public int getPrioridade() { return prioridade; }
    public void setPrioridade(int prioridade) { this.prioridade = prioridade; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }
}
