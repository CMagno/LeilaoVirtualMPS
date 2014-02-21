package Logic.Pojos;

public class Negocio {
    
    private Cliente comprador;
    private Cliente vendedor;
    private Produto produto;

    public Negocio(Cliente comprador, Cliente vendedor, Produto produto) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.produto = produto;
    }

    
    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Cliente getVendedor() {
        return vendedor;
    }

    public void setVendedor(Cliente vendedor) {
        this.vendedor = vendedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Negocio{" + "comprador=" + comprador + ", vendedor=" + vendedor + ", produto=" + produto + '}';
    }
    
    
    
}
