package com.mycompany.cadastrodeprodutos;
import javax.swing.JOptionPane;

public class Loja {
    Cliente[] clientes = new Cliente[100];
    Produto[] produtos = new Produto[100];
    Venda[] vendas = new Venda[100];
    
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
        
        int qtdProdutos = 1;
        for(Produto p : produtos) {
            if(p != null) {
                sb.append("Produto ").append(qtdProdutos).append(" - ").append(p.getNome())
                .append(": R$").append(p.getPreço())
                .append(", ID: ").append(p.getId())
                .append("\n");
                qtdProdutos++;
            }
        }
        return sb.toString();
    }
    
    public String listarVendas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numeroVendas; i++) {
            sb.append(vendas[i].toString()).append("\n");
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
