package Logic;

import java.util.Calendar;

/**
 *
 * @authors Carlos e Felipe
 */
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

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the data_int
     */
    public Calendar getData_int() {
        return data_int;
    }

    /**
     * @param data_int the data_int to set
     */
    public void setData_int(Calendar data_int) {
        this.data_int = data_int;
    }

    /**
     * @return the data_fim
     */
    public Calendar getData_fim() {
        return data_fim;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    @Override
    public String toString() {
        return "Leilao{" + "produto=" + produto.toString() + ", data_int=" + data_int + ", data_fim=" + data_fim + '}';
    }
    
    
    
}
