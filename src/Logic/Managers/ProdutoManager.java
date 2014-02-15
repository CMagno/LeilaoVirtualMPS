package Logic.Managers;

import Logic.Pojos.Produto;

public class ProdutoManager {
    private Produto p;
    
    public ProdutoManager (String nome, String descricao, double preco_init, double preco_compra, int ano){
        p = new Produto(nome,descricao,preco_init,preco_compra,ano);   
    }
    
    public ProdutoManager(){
        this("","",0,0,0);
    }
    
    
    public void setProduto(String nome, String descricao, double preco_init, double preco_compra, int ano){
        p = new Produto(nome,descricao,preco_init,preco_compra,ano);
    }
    public Produto getProduto(){
        return p;
    }
}
