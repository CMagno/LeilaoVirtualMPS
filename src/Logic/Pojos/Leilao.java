package Logic.Pojos;

import java.util.Calendar;

public class Leilao {
    
    private Produto  produto;
    private Calendar data_int;
    private Calendar data_fim;

    public Leilao(Produto produto, Calendar data_int, Calendar data_fim) {
        this.produto = produto;
        this.data_int = data_int;
        this.data_fim = data_fim;
    }
    
    public Leilao(){
        this(new Produto(),Calendar.getInstance(),Calendar.getInstance());
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Calendar getData_int() {
        return data_int;
    }

    public void setData_int(Calendar data_int) {
        this.data_int = data_int;
    }
    
    public Calendar getData_fim() {
        return data_fim;
    }

    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    @Override
    public String toString() {
        return "Leilao{" + "produto=" + produto.toString() + ", data_int=" + data_int + ", data_fim=" + data_fim + '}';
    }
    
    
    
}
