package GUI;

import GUI.Readers.Reader;
import Logic.Managers.Exceptions.CpfDuplicateException;
import Infra.SinglePersistence;
import Logic.Managers.ClienteManager;
import Logic.Pojos.Cliente;
import Logic.Pojos.Leilao;
import Logic.Pojos.Produto;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Main_MENU {

    private SinglePersistence per;
    private Reader leitor;
    private ClienteManager clientes;
    
    public Main_MENU() {
        this.per = SinglePersistence.getInstance();
        leitor = new Reader();
        clientes = new ClienteManager();
    }

    private void cadastraCliente() {
        String cli_nome = JOptionPane.showInputDialog("Nome:");
        while (true) {
            String cli_cpf = JOptionPane.showInputDialog("CPF:");
            try {
                per.add(new Cliente(cli_nome, cli_cpf));
                break;
            } catch (CpfDuplicateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de CPF", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cadastraLeilao() {
        
        Leilao lei = new Leilao();
        
        Produto prod = new Produto();
        prod.setNome(JOptionPane.showInputDialog("Nome do Produto"));
        prod.setDescricao(Reader.showInputTextDialog("Descricao do produto", "Tela de descricao"));
        
        //set ano do produto
        while(true){
            try{
                prod.setAno(Integer.parseInt(JOptionPane.showInputDialog("Ano")));
                break;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ano invalido !", "Erro de Ano", JOptionPane.ERROR_MESSAGE);
            }
        }
        //set preco inicial do produto
        while(true){
            try{
                prod.setPreco_init(Double.parseDouble(JOptionPane.showInputDialog("Preco")));
                break;
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Preco invalido !", "Erro de Preco", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
        lei.setProduto(prod);
        
        //set data int
        while(true){
            Calendar dataInt = Reader.showInputDateDialog("Data de Inicio", "Tela de data");
            if(dataInt == null){
                JOptionPane.showMessageDialog(null, "Erro no formato da data", "Erro de data", JOptionPane.ERROR_MESSAGE);
            }
            else{
                lei.setData_int(dataInt);
                break;
            }
        }
        //set data fim
        while(true){
            Calendar dataFim = Reader.showInputDateDialog("Data do Final", "Tela de data");
            if(dataFim == null){
                JOptionPane.showMessageDialog(null, "Erro no formato da data", "Erro de data", JOptionPane.ERROR_MESSAGE);
            }
            else{
                lei.setData_fim(dataFim);
                break;
            }
        }
        
        per.addLeilao(lei);
        
    }

    private void listaClientes() {
        StringBuilder string = new StringBuilder("");
        HashSet<Cliente> list = per.getCli_list();
        for (Cliente c : list) {
            string.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, string.toString(), "Tela de Clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void listaLeiloes() {
        StringBuilder string = new StringBuilder("");
        LinkedList<Leilao> list = per.getLei_list();
        for (Leilao l : list) {
            string.append(l.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, string.toString(), "Tela de Leiloes", JOptionPane.INFORMATION_MESSAGE);
    }
    

    public void begin() {
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
                    cadastraCliente();
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
                    per.saveCli();
                    per.saveLei();
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
