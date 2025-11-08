package br.com.lreiiss;

import br.com.lreiiss.dao.ClienteMapDAO;
import br.com.lreiiss.dao.IClienteDAO;
import br.com.lreiiss.domain.Cliente;

import javax.swing.*;

public class App {
    private static IClienteDAO iClienteDAO;
    public static void main(String[] args) {
        iClienteDAO  = new ClienteMapDAO();
        String opcao = "";


        while (true) {

            opcao = JOptionPane.showInputDialog(null,
                    "Digite:\n" +
                            "1 - Cadastrar cliente\n" +
                            "2 - Consultar cliente\n" +
                            "3 - Excluir cliente\n" +
                            "4 - Alterar cliente\n" +
                            "5 - Sair",
                    "Menu", JOptionPane.INFORMATION_MESSAGE);

            if (opcao == null) {
                sair();
            }

            if (!isOpcaoValida(opcao)) {
                JOptionPane.showMessageDialog(null,
                        "Opção inválida! Tente novamente.");
                continue; // volta para o início do while
            }

            if (isOpacaoSair(opcao)) {
                sair();
            }

            if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados separados por vírgula:\n" +
                                "Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
                continue;
            }

            if (isConsultar(opcao)) {
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF:",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(cpf);
                continue;
            }

            if (isExcluir(opcao)) {
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF para excluir:",
                        "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(cpf);
                continue;
            }

            if (isAlterar(opcao)) {
                String cpf = JOptionPane.showInputDialog(null,
                        "Digite o CPF para alterar:",
                        "Alterar", JOptionPane.INFORMATION_MESSAGE);
                alterar(cpf);
            }
        }



    }
    private static boolean isExcluir(String opcao) {
        return "3".equals(opcao);
    }

    private static boolean isAlterar(String opcao) {
        return "4".equals(opcao);
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
        if (dadosSeparados.length != 7) {
            JOptionPane.showMessageDialog(null, "Erro: você deve informar 7 dados separados por vírgula.");
            return;
        }
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
        JOptionPane.showMessageDialog(null,
                "Até logo!");
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
    private static void excluir(String dados) {

        if (dados == null || dados.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você precisa informar um CPF.");
            return;
        }

        String cleanCpf = dados.replaceAll("[^0-9]", "");

        if (cleanCpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return;
        }

        if (cleanCpf.length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF deve conter 11 dígitos.");
            return;
        }

        Long cpf = Long.parseLong(cleanCpf);

        Cliente cliente = iClienteDAO.consultar(cpf);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        iClienteDAO.excluir(cpf);

        JOptionPane.showMessageDialog(null,
                "Cliente removido com sucesso!");
    }
    private static void alterar(String dados) {

        if (dados == null || dados.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Você precisa informar um CPF.");
            return;
        }

        String cleanCpf = dados.replaceAll("[^0-9]", "");

        if (cleanCpf.isEmpty()) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            return;
        }

        if (cleanCpf.length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF deve conter 11 dígitos.");
            return;
        }

        Long cpf = Long.parseLong(cleanCpf);

        Cliente cliente = iClienteDAO.consultar(cpf);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            return;
        }

        String novosDados = JOptionPane.showInputDialog(null,
                "Digite os novos dados separados por vírgula:\n" +
                        "Nome, Telefone, Endereço, Número, Cidade, Estado",
                "Alterar", JOptionPane.INFORMATION_MESSAGE);

        if (novosDados == null || novosDados.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma alteração realizada.");
            return;
        }

        String[] campos = novosDados.split(",");

        if (campos.length != 6) {
            JOptionPane.showMessageDialog(null,
                    "Quantidade de dados inválida! Deve ter:\n" +
                            "Nome, Telefone, Endereço, Número, Cidade, Estado");
            return;
        }

        // Atualizando os campos do cliente
        cliente.setNome(campos[0].trim());

        String cleanTel = campos[1].replaceAll("[^0-9]", "");
        cliente.setTel(Long.valueOf(cleanTel));

        cliente.setEnd(campos[2].trim());

        String cleanNum = campos[3].replaceAll("[^0-9]", "");
        cliente.setNumero(Integer.valueOf(cleanNum));

        cliente.setCidade(campos[4].trim());
        cliente.setEstado(campos[5].trim());

        iClienteDAO.alterar(cliente);

        JOptionPane.showMessageDialog(null,
                "Cliente alterado com sucesso!");
    }


}
