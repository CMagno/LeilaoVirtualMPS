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
    
    public Leilao getLeilao(){
        return lei_list.getFirst();   
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
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder("");
        for(Leilao l :  lei_list){
            string.append(l.toString());
            string.append("\n");
        }
        return string.toString();
    }
    
}
