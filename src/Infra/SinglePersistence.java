package Infra;

import Logic.Managers.ClienteManager;
import Logic.Managers.Exceptions.CpfDuplicateException;
import Logic.Managers.LeilaoManager;
import Logic.Managers.ProdutoManager;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SinglePersistence implements Infra {

    private static final SinglePersistence persistenceObj = new SinglePersistence();
    private final String cli_fileName = "clientes.dat";
    private final String lei_fileName = "leilao.dat";

    private SinglePersistence() {
    }

    public static synchronized SinglePersistence getInstance() {
        return SinglePersistence.persistenceObj;
    }

    @Override
    public ClienteManager readCliList() {
        ClienteManager clientes = new ClienteManager();
        try (DataInputStream in = new DataInputStream(new FileInputStream(this.cli_fileName))) {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                try {
                    clientes.addCli(in.readUTF(), in.readUTF());
                } catch (CpfDuplicateException ex) {
                    System.out.println("Exceçao cpf duplicado");
                }
            }
        } catch (IOException ex) {
        }
        return clientes;
    }

    @Override
    public LeilaoManager readLeiList() {
        LeilaoManager lei = new LeilaoManager();
        
        try (DataInputStream in = new DataInputStream(new FileInputStream(this.lei_fileName))) {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                //dados de produto
                String nome = in.readUTF();
                String descricao = in.readUTF();
                double preco_init = in.readDouble();
                double preco_compra = in.readDouble();
                int ano = in.readInt();
                //dados de leilao
                Calendar data_int = Calendar.getInstance();
                data_int.setTimeInMillis(in.readLong());
                Calendar data_fim = Calendar.getInstance();
                data_fim.setTimeInMillis(in.readLong());           
                lei.addLeilao(ProdutoManager.geraProduto(nome, descricao, preco_init, preco_compra, ano),data_int,data_fim);
            }
        } catch (IOException ex) {
        }

        return lei;
    }

    @Override
    public void saveCli(ClienteManager c) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.cli_fileName, false))) {
            
            out.writeInt(c.getCli_listSize()); // salva o tamanho da lista de clientes
            
            while (!c.isEmpty()) {
                //consertar isso !!!
                out.writeUTF((String) c.getCliData("nome"));
                out.writeUTF((String) c.getCliData("cpf"));
                c.nextCli();
            }
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveLei(LeilaoManager l) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.lei_fileName, false))) {
            
            out.writeInt(l.getLei_listSize()); // salva o tamanho da lista de leiloes
        
        while(!l.isEmpty()) {
            try {
                //dados de produto
                ProdutoManager pManager = new ProdutoManager();
                pManager.setProduto(l.getLeiData("produto"));
                out.writeUTF((String)pManager.getProdData("nome"));
                out.writeUTF((String)pManager.getProdData("descricao"));
                out.writeDouble((double)pManager.getProdData("preco_init"));
                out.writeDouble((double)pManager.getProdData("preco_compra"));
                out.writeInt((int)pManager.getProdData("ano"));
                //dados de leilao
                out.writeLong((long)l.getLeiData("data_ini"));
                out.writeLong((long)l.getLeiData("data_compra"));
            } catch (IOException ex) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
