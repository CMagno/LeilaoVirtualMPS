package GUI.FormValidation;

public class Name implements ValidationStrategy{

    @Override
    public boolean isValid(String data) {
        // nome max 300
        boolean condition1 = (data.length() > 300)    ? false : true;
        // nome min 5
        boolean condition2 = (data.length() <   5)    ? false : true; 
        // nome nao contem numero
        boolean condition3 = (data.matches("[0-9]+")) ? false : true; 
        
        
        
        return (condition1 && condition2 && condition3);
    }
    
}
