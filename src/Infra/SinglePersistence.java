package Infra;

import Logic.Managers.ClienteManager;
import Logic.Exceptions.CpfDuplicateException;
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

    public static SinglePersistence getInstance() {
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
            return clientes;
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
                ProdutoManager p = new ProdutoManager(nome,descricao,preco_init,preco_compra,ano);
                //dados de leilao
                Calendar data_int = Calendar.getInstance();
                data_int.setTimeInMillis(in.readLong());
                Calendar data_fim = Calendar.getInstance();
                data_fim.setTimeInMillis(in.readLong());           
                lei.addLeilao(p.getProduto(),data_int,data_fim);
            }
        } catch (IOException ex) {
            return lei;
        }

        return lei;
    }

    @Override
    public void saveCli(ClienteManager c) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.cli_fileName, false))) {
            
            out.writeInt(c.getCli_listSize()); // salva o tamanho da lista de clientes
            
            while (!c.isEmpty()) {
                out.writeUTF(c.getCliente().getNome());
                out.writeUTF( c.getCliente().getCpf());
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
                out.writeUTF(l.getLeilao().getProduto().getNome());
                out.writeUTF(l.getLeilao().getProduto().getDescricao());
                out.writeDouble(l.getLeilao().getProduto().getPreco_init());
                out.writeDouble(l.getLeilao().getProduto().getPreco_compra());
                out.writeInt(l.getLeilao().getProduto().getAno());
                //dados de leilao
                out.writeLong(l.getLeilao().getData_int().getTimeInMillis());
                out.writeLong(l.getLeilao().getData_fim().getTimeInMillis());
            } catch (IOException ex) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
