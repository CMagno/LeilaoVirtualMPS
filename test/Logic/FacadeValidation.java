package Logic;

import Logic.Exceptions.CpfDuplicateException;
import Logic.Exceptions.NullObject;
import Logic.Managers.Facade;
import Logic.Pojos.Cliente;
import Logic.Pojos.Leilao;
import Logic.Pojos.Produto;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;

public class FacadeValidation {

    Cliente c1;
    Cliente c2;
    Cliente c3;
    
    Leilao l1;
    Leilao l2;
    
    Produto p1;
    Produto p2;
    
    Facade f;
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        
        c1 = new Cliente("joao", "000.111.222-33");
        c2 = new Cliente("inho", "000.111.222-44");
        c3 = new Cliente("igual", "000.111.222-33");
        
        p1 = new Produto("nome", "descricao", 0, 1, 1991);
        p2 = new Produto("nome2", "descricao2", 02, 12, 1992);
        
        l1 = new Leilao(p1,Calendar.getInstance(),Calendar.getInstance());
        l2 = new Leilao(p2,Calendar.getInstance(),Calendar.getInstance());
        
        f = new Facade();
        
    }

    @Test
    public void testInserirCliente() {
        try {
            assertTrue(f.addCliente(c1.getNome(), c1.getCpf()));   
            assertTrue(f.addCliente(c2.getNome(), c2.getCpf()));
            
        } catch (CpfDuplicateException ex) {
            System.out.println("NAO DEVE PASSAR AQUI !!!! METODO testInserirCliente");
        }
    }
    
    @Test
    public void testInserirClienteException() throws CpfDuplicateException {
        testInserirCliente();
        exception.expect(Logic.Exceptions.CpfDuplicateException.class);
        exception.expectMessage("Cpf Duplicado");
        f.addCliente(c3.getNome(), c3.getCpf());
    }
    
    @Test
    public void testToStringClientes(){
        testInserirCliente();
        String test = c1.toString() + "\n" + c2.toString() + "\n";
        assertEquals(test,f.clienteToString());
    }
    
    @Test
    public void testInserirLeilao() {
        try {
            f.setProduto(p1.getNome(), p1.getDescricao(), p1.getPreco_init(), p1.getPreco_compra(), p1.getAno());
            f.addLeilao(l1.getData_int(), l1.getData_fim());
            f.setProduto(p2.getNome(), p2.getDescricao(), p2.getPreco_init(), p2.getPreco_compra(), p2.getAno());
            f.addLeilao(l2.getData_int(), l2.getData_fim());
        } catch (NullObject ex) {
            System.out.println("NAO DEVE ENTRAR AQUI ! testInserirLeilao");
        }
    }
    
    @Test
    public void testInserirLeilaoException() throws NullObject{
        exception.expect(Logic.Exceptions.NullObject.class);
        exception.expectMessage("Produto é um objeto nulo");
        f.addLeilao(Calendar.getInstance(),Calendar.getInstance());
    }
    
    @Test
    public void testToStringLeilao(){
        testInserirLeilao();
        String test = l1.toString() + "\n" + l2.toString() + "\n";
        assertEquals(test,f.leilaoToString());
    }
}
