/*
    * Qualquer tratamento com cliente fica aqui !
    * 
    * Qualquer mudança dos atributos de Cliente deve alterar os metodos 
    * getCliData() e addCli()
    * 
 */
package Logic.Managers;

import Logic.Exceptions.CpfDuplicateException;
import Logic.Pojos.Cliente;
import java.util.HashSet;
import java.util.Iterator;


public class ClienteManager {
    
    private HashSet<Cliente> cli_list;
    
    public ClienteManager() {
        cli_list = new HashSet<>();
    }
    
    public Cliente getCliente(){
        return cli_list.iterator().next(); 
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
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("");
        for(Cliente c : cli_list){
            string.append(c.toString());
            string.append("\n");
        }
        return string.toString();
        
    }

    
}
