package Logic.Managers;

import Infra.SinglePersistence;
import Logic.Exceptions.CpfDuplicateException;
import Logic.Exceptions.NullObject;
import java.util.Calendar;

public class Facade {
    
    private ClienteManager clientes;
    private SinglePersistence persistence;
    private LeilaoManager leiloes;
    private ProdutoManager produto;
    
    public Facade(){
        clientes    = null;
        persistence = SinglePersistence.getInstance();
        leiloes     = null;
        produto     = null;
    }
   
    //metodos de persistencia e manipulaçao de clientes
    
    public void initClientes(){
        clientes = persistence.readCliList();
    }
    
    public void addCliente(String nome, String cpf) throws CpfDuplicateException{        
            clientes.addCli(nome, cpf);
    }
    
    public String clienteToString(){
        return clientes.toString();
    }
    
    public void saveClientes(){
        persistence.saveCli(clientes);
    }
    
    //metodos de persistencia e manipulaçao de leilao
    public void initLeilao(){
        leiloes = persistence.readLeiList();
    }
    
    public void saveLei(){
        persistence.saveLei(leiloes);
    }
    
    
    public void addLeilao(Calendar data_int, Calendar data_fim) throws NullObject{
        if(this.produto == null)
            throw new NullObject("Produto é um objeto nulo");
        leiloes.addLeilao(this.produto.getProduto(), data_int, data_fim);
    }
    
    public String leilaoToString(){
        return leiloes.toString();
    }
    
    
    public void setProduto(String nome, String descricao, double preco_init, double preco_compra, int ano){
            produto = new ProdutoManager(nome,descricao,preco_init,preco_compra,ano);
    }
    
}
