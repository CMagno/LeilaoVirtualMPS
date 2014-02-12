package Infra;

import Infra.Exceptions.CpfDuplicateException;
import Logic.Cliente;
import Logic.Leilao;
import Logic.Produto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Felipe e Carlos
 */
public class SinglePersistence {
    
    private static final SinglePersistence persistenceObj = new SinglePersistence();
    private final static String cli_fileName = "clientes.dat";
    private final static String lei_fileName = "leilao.dat";
    private HashSet<Cliente> cli_list;
    private LinkedList<Leilao> lei_list;
    private DataInputStream in;
    private DataOutputStream out;

    
    private SinglePersistence() {
        cli_list = cli_listInit();
        lei_list = lei_listInit();
    }
   
    public static synchronized SinglePersistence getInstance(){
        return SinglePersistence.persistenceObj;
    }

    //fills cli_list with data that was stored previously
    private HashSet<Cliente> cli_listInit() {
        HashSet<Cliente> list = new HashSet<>();
        Cliente cli;
        try {
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                cli = new Cliente();
                cli.setNome(in.readUTF());
                cli.setCpf(in.readUTF());
                list.add(cli);
            }
        } catch (FileNotFoundException ex) {
            return new HashSet<>();
        } catch (IOException ex) {
            try {
                in.close();
            } catch (IOException ex1) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return new HashSet<>();
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private LinkedList<Leilao> lei_listInit() {
        LinkedList<Leilao> list = new LinkedList<>();
        Leilao lei;
        try {
            in = new DataInputStream(new FileInputStream(lei_fileName));
            int size = in.readInt();
            for (int i = 0; i < size; i++) {

                lei = new Leilao();

                Produto prod = new Produto();
                prod.setNome(in.readUTF());
                prod.setDescricao(in.readUTF());
                prod.setPreco_init(in.readDouble());
                prod.setPreco_compra(in.readDouble());
                prod.setAno(in.readInt());
                lei.setProduto(prod);

                Calendar c1 = Calendar.getInstance();
                c1.setTimeInMillis(in.readLong());
                lei.setData_int(c1);

                Calendar c2 = Calendar.getInstance();
                c2.setTimeInMillis(in.readLong());
                lei.setData_int(c2);

                list.add(lei);
            }
        } catch (IOException ex) {
            try {
                in.close();
            } catch (IOException ex1) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return new LinkedList<>();
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void saveCli() {
        try {
            out = new DataOutputStream(new FileOutputStream(cli_fileName, false));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.writeInt(getCli_list().size()); // salva o tamanho da lista de clientes
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Cliente c : getCli_list()) {
            try {
                out.writeUTF(c.getNome());
                out.writeUTF(c.getCpf());
            } catch (IOException ex) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveLei() {
        for (Leilao l : getLei_list()) {
            System.out.println(l.toString());
        }
        try {
            out = new DataOutputStream(new FileOutputStream(lei_fileName, false));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.writeInt(getLei_list().size()); // salva o tamanho da lista de leiloes
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Leilao l : getLei_list()) {
            try {
                //se um dia um leilao tiver mais de um produto ... salvar aqui tambem o tamanho da lista     =)
                //salva produto do leilao
                Produto prod = l.getProduto();
                out.writeUTF(prod.getNome());
                out.writeUTF(prod.getDescricao());
                out.writeDouble(prod.getPreco_init());
                out.writeDouble(prod.getPreco_compra());
                out.writeInt(prod.getAno());

                //salva leilao em si
                out.writeLong(l.getData_int().getTimeInMillis());
                out.writeLong(l.getData_fim().getTimeInMillis());
            } catch (IOException ex) {
                Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(SinglePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCli(Cliente cli_novo) throws CpfDuplicateException {
        for (Cliente c : getCli_list()) {
            if (c.getCpf().equalsIgnoreCase(cli_novo.getCpf())) {
                throw new CpfDuplicateException();
            }
        }
        getCli_list().add(cli_novo);
    }

    public void addLeilao(Leilao lei_novo) {
        getLei_list().add(lei_novo);
    }

    public HashSet<Cliente> getCli_list() {
        return cli_list;
    }

    public LinkedList<Leilao> getLei_list() {
        return lei_list;
    }
}
