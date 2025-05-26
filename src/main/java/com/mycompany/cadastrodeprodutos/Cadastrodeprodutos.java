package com.mycompany.cadastrodeprodutos;

import javax.swing.JOptionPane;

public class Cadastrodeprodutos {
    private static Loja loja = new Loja();
    
    public static void main(String[] args) {
        
        String[] respostas = {
            "Cadastrar Clientes",
            "Cadastrar Produto",
            "Realizar Venda",
            "Listar Produtos", 
            "Listar Clientes",
            "Listar Vendas",
            "Sair"
        };
        /*Aparece a caixa de dialogo
         *Opcao recebe o valor do indice do array de String respostas
         */
        while (true) {
            int opcao = JOptionPane.showOptionDialog(
                    null, 
                    "Escolha uma opção",
                    "Cadastro e Controle de Vendas", 
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, 
                    respostas,
                    0
            );

            switch(opcao) {
                case 0:
                    cadastrarCliente();
                    break;
                case 1:
                    cadastrarProdutos();
                    break;
                case 2:
                    realizarVendas();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, loja.listarProdutos());
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, loja.listarClientes());
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, loja.listarVendas());
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    
    private static void cadastrarCliente() {
        boolean cadastroCompleto = false;
        int tentativas = 1;
        final int MAX_TENTATIVAS = 3;
        String nome;
        String cpf;
        while (cadastroCompleto == false || tentativas <= MAX_TENTATIVAS) {
            nome = JOptionPane.showInputDialog("Digite o nome do cliente");
            if(nome == null) return;
            
            if (nome.trim().length() < 3) {
                JOptionPane.showMessageDialog(null, "Nome inválido! Deve ter pelo menos 3 caracteres. \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                tentativas++;
                continue;
            }
            
            cpf = JOptionPane.showInputDialog("Digite o cpf do cliente");
            if (cpf == null) return;
            
            //Apenas pra atribuir as variaveis
            Cliente tempCliente = new Cliente(nome, cpf);
            
            
            if (tempCliente.getCpf() == null) {
                JOptionPane.showMessageDialog(null, 
                    "CPF inválido! Deve conter 11 dígitos. \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                tentativas++;
            } else {
                // Verifica se CPF já existe
                boolean cpfExistente = false;
                for (int i = 0; i < loja.getNumeroClientes(); i++) {
                    if (loja.clientes[i].getCpf().equals(cpf)) {
                        cpfExistente = true;
                        break;
                    }
               }
                
                if (cpfExistente) {
                    JOptionPane.showMessageDialog(null, 
                        "CPF já cadastrado! \nTentativas restantes: " + (MAX_TENTATIVAS - tentativas - 1));
                    tentativas++;
                } else {
                    loja.adicionarClientes(tempCliente);
                    JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                    cadastroCompleto = true;
                }
            }
        }
    }
    
   private static void cadastrarProdutos() {
        double preco;
        
        boolean cadastroCompleto = false;
        
        while (cadastroCompleto == false) {
            //Se o ID for nulo ou nao for inteiramente em inteiro ele para
            String id = JOptionPane.showInputDialog("Digite o ID do produto");
            if (id == null ) return;
            
            if (!id.matches("^\\d+$")) {
                JOptionPane.showMessageDialog(null, "ID inválido!");
                break;
            }
            
            String nome = JOptionPane.showInputDialog("Digite o nome do produto");
            
            String escolherPreco = JOptionPane.showInputDialog("Digite o preco do produto");
            if (escolherPreco == null || !escolherPreco.matches("^\\d+(\\.\\d+)?$")) {
                JOptionPane.showMessageDialog(null, "Preço inválido!");
                break;
            }
            
            preco = Double.parseDouble(escolherPreco);
            
            Produto tempProduto = new Produto(id, nome, preco);
            
            loja.adicionarProdutos(tempProduto);
            cadastroCompleto = true;
        }
    }
    
    private static void realizarVendas() {
        if (loja.listarClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado!");
            return;
        }
        
        boolean vendaCompleta = false;
        
        while (vendaCompleta == false){
            String clientesDisponiveis = loja.listarClientes();
            String escolhaCpf = JOptionPane.showInputDialog("Escolha um cliente:\n" + clientesDisponiveis + "\nDigite o Cpf do cliente:");

            Cliente clienteSelecionado = null;
            for (int i = 0; i < loja.getNumeroClientes(); i++) {
                if(loja.clientes[i].getCpf().equals(escolhaCpf)) {
                    clienteSelecionado = loja.clientes[i];
                }
            }

            if (clienteSelecionado == null) {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                return;
            }
            
            String produtosDisponiveis = loja.listarProdutos();
            
            //Aqui começa a parte de procurar produto
            Produto[] produtoSelecionado = new Produto[10];
            int contagemProdutos = 0;
            boolean continuar = true;
            
            while (continuar && contagemProdutos < 10) {
                String escolhaID = JOptionPane.showInputDialog("Escolha até 10 Produtos:\n" + produtosDisponiveis + "\nDigite o ID do Produto (Ou F para sair)");
                
                //Se apertar em cancelar ou f
                if (escolhaID == null || escolhaID.equalsIgnoreCase("F")) {
                    continuar = false;
                    continue;
                }
                Produto produto = null;
                for(int i = 0; i < loja.getNumeroProdutos(); i++) {
                    if(loja.produtos[i].getId().equals(escolhaID)) {
                        produto = loja.produtos[i];
                        break;
                    }
                }
                
                if(produto != null) {
                    produtoSelecionado[contagemProdutos++] = produto;
                    JOptionPane.showMessageDialog(null, 
                    produto.getNome() + " adicionado ao carrinho!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro, ID não encontrado!");
                }
            }
            
            if (contagemProdutos > 0) {
                Produto[] produtoVenda = new Produto[contagemProdutos];
                System.arraycopy(produtoSelecionado, 0, produtoVenda, 0, contagemProdutos);
                
                loja.realizarVenda(clienteSelecionado, produtoVenda);
                JOptionPane.showMessageDialog(null, "Venda Concluida!\nValor total: R$" +
                        String.format("%.2f", loja.vendas[loja.getNumeroVendas()-1].calcularTotal()));
                
                vendaCompleta = true;
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum produto selecionado, Venda Cancelada");
            }
        }
    }
}
