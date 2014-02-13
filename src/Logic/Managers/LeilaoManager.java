package Logic.Managers;

import Logic.Pojos.Leilao;
import Logic.Pojos.Produto;
import java.util.Calendar;
import java.util.LinkedList;

public class LeilaoManager {
    private LinkedList<Leilao> lei_list;

    public LeilaoManager() {
        lei_list = new LinkedList<>();
    }
    
    public void nextLeilao(){
        lei_list.removeFirst();
    }
    
    public boolean isEmpty(){
        return lei_list.isEmpty();
    }
    
    public Object getLeiData(String data){
        Leilao l = lei_list.getFirst();
        switch(data){
            case "data_fim": 
                return l.getData_fim().getTimeInMillis();
            case "data_ini":
                return l.getData_int().getTime();
            case "produto":
                return l.getProduto();
            
        }
        return null;
    }
    
    public int getLei_listSize() {
        return lei_list.size();
    }
    
    public void setLei_list(LinkedList<Leilao> lei_list) {
        this.lei_list = lei_list;
    }
    
    public void addLeilao(Produto produto, Calendar data_int, Calendar data_fim) {
        lei_list.add(new Leilao(produto,data_int,data_fim));
    }
    
}
