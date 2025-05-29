package com.mycompany.cadastrodeprodutos;

public class Venda {
    Cliente cliente = new Cliente("", "");
    Produto[] produto = new Produto[10];
    int qtdProdutos = 1;
    
    
    public double calcularTotal() {
        double total = 0;
        for(Produto p : produto) {
            if(p != null) {
                total+=p.getPreço();
            }
        }
        return total;
    }
    
    public String listarProdutos() {
        StringBuilder sb = new StringBuilder();
        
        for(Produto p : produto) {
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

    @Override
    public String toString() {
        return "Venda{" + "cliente=" + cliente + ", produto=" + produto + ", qtdProdutos=" + qtdProdutos + '}';
    }
    
    
}