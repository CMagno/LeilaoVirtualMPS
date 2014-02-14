package GUI.FormValidation;

public class Cpf implements ValidationStrategy{

    @Override
    public boolean isValid(String data) {
        // digitos de 0 a 9
        //3 digitos 'ponto' 3 digitos 'ponto' 3 digitos 'traco' 2 digitos 
       
        return data.matches("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}");
    }
    
}