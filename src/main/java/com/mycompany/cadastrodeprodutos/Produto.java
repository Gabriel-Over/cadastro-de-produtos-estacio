package com.mycompany.cadastrodeprodutos;

public class Produto {
    private String id;
    private String nome;
    private double preço;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public Produto(String id, String nome, double preço) {
        this.id = id;
        this.nome = nome;
        this.preço = preço;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", pre\u00e7o=" + preço + '}';
    }
    
    
}
