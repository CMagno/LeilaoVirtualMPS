package GUI.FormValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date implements ValidationStrategy {
/*
 * Note o setLenient no código !
 * Ele serve para indicar que a conversão NÃO deve dar margem 
 * a algum possível erro na String (se setLenient(boolean) for true 
 * "31/06/2013" será convertido para "01/07/2013", por exemplo).
 */
    @Override
    public boolean isValid(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(data);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }
}
