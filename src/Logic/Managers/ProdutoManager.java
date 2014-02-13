package Logic.Managers;

import Logic.Pojos.Produto;

public class ProdutoManager {
    
    
    private Produto p;
    
    public static Produto geraProduto(String nome, String descricao, double preco_init, double preco_compra, int ano){
        return new Produto(nome,descricao,preco_init,preco_compra,ano);
    }
    
    public void setProduto(Object o){
        p = (Produto) o;
    }
    
    public Object getProdData(String data){        
        switch(data){
            
            case "nome":
                    return p.getNome();
            case "ano":
                    return p.getAno();
            case "preco_compra":
                    return p.getPreco_compra();
            case "preco_init":
                    return p.getPreco_init();
            case "descricao":
                    return p.getDescricao();
                   
        }
        return null;
    }
    
}
