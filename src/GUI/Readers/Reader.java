package GUI.Readers;

public class Reader {
    
    public static final int jCalendar = 1;
    public static final int jText = 2;
    
    private Reader(){
        
    }
    
    public static String showInput(String mensagem,String titulo,int type){
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
