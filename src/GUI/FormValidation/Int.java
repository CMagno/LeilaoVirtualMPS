package GUI.FormValidation;

public class Int implements ValidationStrategy {

    @Override
    public boolean isValid(String data) {
        try{
            Integer.parseInt(data);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
}
