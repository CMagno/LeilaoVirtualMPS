package GUI.Readers;

import java.text.ParseException;

public class Reader {
    
    public static final int jCalendar = 1;
    public static final int jText = 2;
    
    
    public static String showInput(String mensagem,String titulo,int type) throws ParseException{
        ReaderStrategy acao;
        switch(type){
            case 1:
                acao = new jDate();
                break;
            case 2: 
                acao = new jDescricao();
                break;
            default:
                return null;
        }
        return acao.generatePane(mensagem, titulo);
    }
    
    
    
    
}
