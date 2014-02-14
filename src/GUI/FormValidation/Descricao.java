package GUI.FormValidation;

public class Descricao implements ValidationStrategy{

    @Override
    public boolean isValid(String data) { 
        return (data.length() <= 500);
    }
    
}
