package com.mycompany.cadastrodeprodutos;

public class Cliente {
    private String nome;
    private String cpf;
    
    private boolean validarCpf(String cpf) {
        for (char ch : cpf.toCharArray()) {
            if (!Character.isDigit(ch) || cpf.length() != 11) {
                return false;
            }
        }
        return true;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validarCpf(cpf)) {
            this.cpf = cpf;
        }
    }

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        if (validarCpf(cpf)){
            this.cpf = cpf;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf;
    }
}
