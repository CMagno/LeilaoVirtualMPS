package GUI.FormValidation;

public class Doubler implements ValidationStrategy {

    @Override
    public boolean isValid(String data) {
        try{
            Double.parseDouble(data);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
    
    
}
