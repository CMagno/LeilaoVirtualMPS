package GUI;

import GUI.FormValidation.Validation;
import GUI.Readers.Reader;
import Logic.Exceptions.CpfDuplicateException;
import Logic.Exceptions.NullObject;
import Logic.Managers.Facade;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main_MENU {

    private Facade clientesEleilao;

    public Main_MENU() {
        clientesEleilao = new Facade();
    }

    private void cadastraCliente() throws CpfDuplicateException {
        String cli_nome;
        do {
            cli_nome = JOptionPane.showInputDialog("Nome:");
        } while (!Validation.isValid(cli_nome, Validation.NAME));

        String cli_cpf;
        do {
            cli_cpf = JOptionPane.showInputDialog("CPF:");
        } while (!Validation.isValid(cli_cpf, Validation.CPF));

        clientesEleilao.addCliente(cli_nome, cli_cpf);
    }

    private void cadastraLeilao() {


        String prod_nome = JOptionPane.showInputDialog("Nome do Produto");

        String prod_desc;
        do {
            prod_desc = Reader.showInput("Descricao do produto", "Tela de descricao", Reader.jText);
        } while (!Validation.isValid(prod_desc, Validation.DESCRICAO));

        String prod_ano;
        do {
            prod_ano = JOptionPane.showInputDialog("Digite o ano");
        } while (!Validation.isValid(prod_ano, Validation.INT));

        String prod_preco;
        do {
            prod_preco = JOptionPane.showInputDialog("Digite o valor do produto");
        } while (!Validation.isValid(prod_ano, Validation.DOUBLE));

        clientesEleilao.setProduto(prod_nome, prod_desc, Double.parseDouble(prod_preco), 0, Integer.parseInt(prod_ano));

        String lei_dataIni;
        do {
            lei_dataIni = Reader.showInput("Data de Inicio", "Tela de data", Reader.jCalendar);
        } while (!Validation.isValid(lei_dataIni, Validation.DATE));
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(lei_dataIni));
        try {
            clientesEleilao.addLeilao(c, null);
        } catch (NullObject ex) {
            Logger.getLogger(Main_MENU.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void listaClientes() {
        JOptionPane.showMessageDialog(null, clientesEleilao.clienteToString(), "Tela de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listaLeiloes() {
        JOptionPane.showMessageDialog(null, clientesEleilao.leilaoToString(), "Tela de Leiloes", JOptionPane.INFORMATION_MESSAGE);
    }

    public void begin() {
        clientesEleilao.initClientes();
        clientesEleilao.initLeilao();
        while (true) {
            String option = JOptionPane.showInputDialog("Escolha uma opção : \n"
                    + "1 - Cadastrar Cliente\n"
                    + "2 - Cadastrar Leilão\n"
                    + "3 - Listar Clientes\n"
                    + "4 - Listar Leiloes\n"
                    + "5 - Sair");
            if (option == null) {
                System.err.println("Erro de escolha de opcao");
                return;
            }

            switch (option) {
                case "1":
                    try {
                        cadastraCliente();
                    } catch (CpfDuplicateException ex) {
                        Logger.getLogger(Main_MENU.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "2":
                    cadastraLeilao();
                    break;
                case "3":
                    listaClientes();
                    break;
                case "4":
                    listaLeiloes();
                    break;
                case "5":
                    clientesEleilao.saveClientes();
                    clientesEleilao.saveLei();
                    return;
                default:
                    System.err.println("ERRO opcao nao existe !");
                    break;
            }
        }
    }

    public static void main(String args[]) {

        new Main_MENU().begin();

    }
}
