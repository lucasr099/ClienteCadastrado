package br.com.lreiiss;

import br.com.lreiiss.dao.ClienteMapDAO;
import br.com.lreiiss.dao.IClienteDAO;
import br.com.lreiiss.domain.Cliente;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;
    public static void main(String[] args) {
        iClienteDAO  = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou  5 para sair",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while(!isOpcaoValida(opcao)){
            if ("".equals(opcao)){
                sair();
            }
            opcao = JOptionPane.showInputDialog(null, "Opção inválida digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou  5 para sair",
                    "ERRO", JOptionPane.INFORMATION_MESSAGE);

        }
        while (isOpcaoValida(opcao)){
            if (isOpacaoSair(opcao)){
                sair();
            }else if (isCadastro(opcao)){
                String dados = JOptionPane.showInputDialog(null,"Digite os dados do cliente separados por vígula, conforme o exemoplo: Nome, CPF, Telefone, Endereço, Numero, Cidade, Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            }else if (isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null,"Digite o cpf ",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);

                consultar(dados);
            }
            opcao = JOptionPane.showInputDialog(null, "Opção inválida digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou  5 para sair",
                    "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    private static void consultar(String dados) {
        if (dados == null || dados.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você precisa informar um CPF.");
            return;
        }

        // Remove tudo que não é número
        String cleanCpf = dados.replaceAll("[^0-9]", "");

        if (cleanCpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF inválido! Digite apenas números.");
            return;
        }

        if (cleanCpf.length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF deve conter 11 dígitos.");
            return;
        }

        Long cpf = Long.parseLong(cleanCpf);

        Cliente cliente = iClienteDAO.consultar(cpf);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.","Erro",JOptionPane.INFORMATION_MESSAGE );
        } else {
            JOptionPane.showMessageDialog(null, "Cliente encontrado:\n\n" + cliente.toString());

        }
    }

    private static boolean isConsultar(String opcao) {
        if ("2".equals(opcao)){
            return true;

        } return false;
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        Cliente cliente = new Cliente(dadosSeparados[0], dadosSeparados[1], dadosSeparados[2], dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);

        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra Cadastrado", "ERRO", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    private static boolean isCadastro(String opcao) {
        if ("1".equals(opcao)){
        return true;

    } return false;
}

    private static boolean isOpacaoSair(String opcao) {
        if ("5".equals(opcao)){
            return true;

        } return false;

    }

    private static void sair() {
        String opcao = JOptionPane.showInputDialog(null,
                "Até logo!",
                "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }
        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if ("1".equals(opcao)){
            return true;

        } return false;
    }
}
