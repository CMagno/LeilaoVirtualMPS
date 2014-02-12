package Infra.Exceptions;

/**
 *
 * @authors Felipe e Carlos
 */
public class CpfDuplicateException extends Exception{
    public CpfDuplicateException(){
        super("Cpf Duplicado");
    }
}
