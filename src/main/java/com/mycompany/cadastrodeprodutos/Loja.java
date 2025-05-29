package com.mycompany.cadastrodeprodutos;
import javax.swing.JOptionPane;

public class Loja {
    Cliente[] clientes = new Cliente[100];
    Produto[] produtos = new Produto[100];
    Venda[] vendas = new Venda[100];
    Livro[] livros = new Livro[100];
    
    private int numeroLivros = 0;
    private int numeroClientes = 0;
    private int numeroProdutos = 0;
    private int numeroVendas = 0;
    
    public void adicionarProdutos(Produto p){
        if (numeroProdutos < produtos.length) {
            produtos[numeroProdutos++] = p;
        } else {
            System.out.println("Limite de produtos atingido!");
        }
    }
    
    public void adicionarLivro(Livro l) {
        if (numeroLivros < livros.length) {
            livros[numeroLivros++] = l;
        } else {
            System.out.println("Limite de livros atingido!");
        }
    }

    public Produto buscarProdutoPorId(String id) {
        // Busca em produtos normais
        for (int i = 0; i < numeroProdutos; i++) {
            if (produtos[i] != null && produtos[i].getId().equals(id)) {
                return produtos[i];
            }
        }
        // Busca em livros
        for (int i = 0; i < numeroLivros; i++) {
            if (livros[i] != null && livros[i].getId().equals(id)) {
                return livros[i];
            }
        }
        return null;
    }

    
    public boolean adicionarClientes(Cliente c) {
        if (numeroClientes < clientes.length) {
            clientes[numeroClientes] = new Cliente("", "");
            clientes[numeroClientes++] = c;
            return true;
        } else {
            System.out.println("Limite de clientes atingido!");
        }
        return false;
    }
    
    public void realizarVenda(Cliente c, Produto[] produtos) {
        if (numeroVendas < vendas.length) {
            Venda venda = new Venda();
            venda.cliente = c;
            venda.produto = produtos;
            venda.qtdProdutos = produtos.length;
            vendas[numeroVendas++] = venda;
        } else {
            JOptionPane.showMessageDialog(null, "Limite de vendas atingido!");
        }
    }
    
    public String listarClientes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroClientes; i++) {
            sb.append(clientes[i].toString()).append("\n");
        }
        return "Clientes:\n" + sb.toString();
    }
    
    public String listarProdutos() {
        StringBuilder sb = new StringBuilder();
        int contador = 1;

        // Listar produtos normais
        for (int i = 0; i < numeroProdutos; i++) {
            if (produtos[i] != null) {
                sb.append(contador++).append(". [Produto] ")
                  .append("ID: ").append(produtos[i].getId())
                  .append(", Nome: ").append(produtos[i].getNome())
                  .append(", Preço: R$").append(String.format("%.2f", produtos[i].getPreço()))
                  .append("\n");
            }
        }

        // Listar livros
        for (int i = 0; i < numeroLivros; i++) {
            if (livros[i] != null) {
                sb.append(contador++).append(". [Livro] ")
                  .append("ID: ").append(livros[i].getId())
                  .append(", Nome: ").append(livros[i].getNome())
                  .append(", Autor: ").append(livros[i].getAutor())
                  .append(", Preço: R$").append(String.format("%.2f", livros[i].getPreço()))
                  .append("\n");
            }
        }

        return sb.toString();
    }
    
    public String listarVendas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroVendas; i++) {
            Venda v = vendas[i];
            sb.append("Venda ").append(i+1).append(":\n");
            sb.append("Cliente: ").append(v.cliente.getNome()).append("\n");
            sb.append("Itens:\n");

            for (int j = 0; j < v.qtdProdutos; j++) {
                if (v.produto[j] != null) {
                    String tipo = (v.produto[j] instanceof Livro) ? "Livro" : "Produto";
                    sb.append("- [").append(tipo).append("] ")
                      .append(v.produto[j].getNome())
                      .append(" - R$").append(v.produto[j].getPreço())
                      .append("\n");
                }
            }

            sb.append("Total: R$").append(String.format("%.2f", v.calcularTotal())).append("\n\n");
        }
        return sb.toString();
    }

    public int getNumeroClientes() {
        return numeroClientes;
    }

    public int getNumeroProdutos() {
        return numeroProdutos;
    }

    public int getNumeroVendas() {
        return numeroVendas;
    }
}