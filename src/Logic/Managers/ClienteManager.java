/*
    * Qualquer tratamento com cliente fica aqui !
    * 
    * Qualquer mudança dos atributos de Cliente deve alterar os metodos 
    * getCliData() e addCli()
    * 
 */
package Logic.Managers;

import Logic.Managers.Exceptions.CpfDuplicateException;
import Logic.Pojos.Cliente;
import java.util.HashSet;
import java.util.Iterator;


public class ClienteManager {
    
    private HashSet<Cliente> cli_list;
    
    public ClienteManager() {
        cli_list = new HashSet<>();
    }
    
    public Object getCliData(String data){
        Cliente next = cli_list.iterator().next();
        switch(data){
            case "nome":
                return next.getNome();
            case "cpf":
                return next.getCpf();
            default :
                return null;
        }
        
    }
    
    
    public void addCli(String nome, String cpf) throws CpfDuplicateException {
        Cliente cli_novo = new Cliente(nome,cpf);
        for (Cliente c : cli_list) {
            if (c.getCpf().equalsIgnoreCase(cli_novo.getCpf())) {
                throw new CpfDuplicateException();
            }
        }
        cli_list.add(cli_novo);
    }
    
    public boolean isEmpty(){
        return cli_list.isEmpty();
    }
    
    public void nextCli(){
        if(cli_list.size() > 0){
            Iterator i = cli_list.iterator();
            i.next();
            i.remove();
        }
    }
    
    public void setCli_list(HashSet<Cliente> cli_list) {
        this.cli_list = cli_list;
    }

    public int getCli_listSize() {
        return cli_list.size();
    }

    
}
