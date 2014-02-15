package GUI.FormValidation;

public class Validation {
    
    public static final int NAME      = 1;
    public static final int CPF       = 2;
    public static final int DESCRICAO = 3;
    public static final int INT       = 4;
    public static final int DOUBLE    = 5;
    public static final int DATE      = 6;
    
    private Validation(){
        
    }
    
    public static boolean isValid(String data,int validation_type){
        ValidationStrategy validation;
        
        switch(validation_type){
            case NAME:
                validation = new Name();
                break;
            case CPF:
                validation = new Cpf();
                break;
            case DESCRICAO:
                validation = new Descricao();
                break;
            case INT:
                validation = new Int();
                break;
            case DOUBLE:
                validation = new Doubler();
                break;
            case DATE:
                validation = new Date();
                break;
            default:
                return false;
        }
        return validation.isValid(data);
    }
}
