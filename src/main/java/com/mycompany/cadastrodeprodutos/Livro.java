package com.mycompany.cadastrodeprodutos;

public class Livro extends Produto {
    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Livro(String id, String nome, double preço, String autor) {
        super(id, nome, preço);
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" + "autor=" + autor + '}';
    }
    
    
}