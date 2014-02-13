package Logic.Pojos;

public class Produto {
    
    private String nome;
    private String descricao;
    private double preco_init;
    private double preco_compra;
    private int ano;

    public Produto(String nome, String descricao, double preco_init, double preco_compra, int ano) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco_init = preco_init;
        this.preco_compra = preco_compra;
        this.ano = ano;
    }
    
    public Produto(){
        this("","",0D,0D,0);
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco_init() {
        return preco_init;
    }

    public void setPreco_init(double preco_init) {
        this.preco_init = preco_init;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }


    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", descricao=" + descricao + ", preco_init=" + preco_init + ", preco_compra=" + preco_compra + ", ano=" + ano + '}';
    }
    
    
    
}
