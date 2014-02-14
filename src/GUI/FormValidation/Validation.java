package GUI.FormValidation;

public class Validation {
    
    public static final int NAME = 1;
    public static final int CPF  = 2;
    public static final int DESCRICAO = 3;
    public static boolean validation(String data,int validation_type){
        ValidationStrategy validation;
        
        switch(validation_type){
            case 1:
                validation = new Name();
                break;
            case 2:
                validation = new Cpf();
                break;
            case 3:
                validation = new Descricao();
                break;
            default:
                return false;
        }
        return validation.isValid(data);
    }
}
