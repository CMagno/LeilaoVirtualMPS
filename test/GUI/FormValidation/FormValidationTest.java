package GUI.FormValidation;


import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class FormValidationTest {
    
    @Test
    public void Name()
    {   
        assertTrue (Validation.isValid ("valeti'm terra ",Validation.NAME));
        assertTrue (Validation.isValid ("felipe",Validation.NAME));
        assertFalse(Validation.isValid("felip3",Validation.NAME));
        assertFalse(Validation.isValid("c4rlos",Validation.NAME));
        assertFalse(Validation.isValid("5antos",Validation.NAME));
    }
    
    @Test
    public void Double(){
        assertTrue(Validation.isValid ("3.5",Validation.DOUBLE));
        assertTrue(Validation.isValid ("0",Validation.DOUBLE));
        assertTrue(Validation.isValid (".5",Validation.DOUBLE));
        assertFalse(Validation.isValid ("3,5",Validation.DOUBLE));
        assertFalse(Validation.isValid ("3..5",Validation.DOUBLE));
        assertFalse(Validation.isValid ("a",Validation.DOUBLE));
    }
    
    @Test
    public void Int(){
        assertFalse(Validation.isValid ("3.5",Validation.INT));
        assertFalse(Validation.isValid ("a",Validation.INT));
        assertFalse(Validation.isValid (".5",Validation.INT));
        assertFalse(Validation.isValid ("3,5",Validation.INT));
        assertFalse(Validation.isValid ("3..5",Validation.INT));
        assertTrue(Validation.isValid ("0",Validation.INT));
    }
    
    @Test
    public void Date(){
        assertTrue(Validation.isValid ("15/02/1993",Validation.DATE));
        assertTrue(Validation.isValid ("15/02/93",Validation.DATE));
        assertTrue(Validation.isValid ("15/02/0093",Validation.DATE));
        assertFalse(Validation.isValid ("1993/02/15",Validation.DATE));
        assertFalse(Validation.isValid ("1993/15/02",Validation.DATE));
        assertFalse(Validation.isValid ("31/02/1993",Validation.DATE));
        assertFalse(Validation.isValid ("31/06/1993",Validation.DATE));
    }
    
    @Test
    public void Cpf(){
        assertTrue(Validation.isValid ("000.333.222-44",Validation.CPF));
        assertFalse(Validation.isValid ("00.333.222-44",Validation.CPF));
        assertFalse(Validation.isValid ("0a0.333.222-44",Validation.CPF));
        assertFalse(Validation.isValid ("000.333.222.44",Validation.CPF));
        assertFalse(Validation.isValid ("000.333-222-44",Validation.CPF));
        assertFalse(Validation.isValid ("00033322244",Validation.CPF));
        assertFalse(Validation.isValid ("000.333.222-4a",Validation.CPF));
    }
    
    @Test
    public void Descricao(){
        assertTrue(Validation.isValid (
                  "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                ,Validation.DESCRICAO));
        
        assertFalse(Validation.isValid (
                  "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "123456789012345678901234567890123456789012345678901"
                ,Validation.DESCRICAO));
        
       
        assertTrue(Validation.isValid (
                  "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "123456789O1234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "12345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890123456789"
                ,Validation.DESCRICAO));
        
        
        assertTrue(Validation.isValid ("1",Validation.DESCRICAO));
    }
    
    
}