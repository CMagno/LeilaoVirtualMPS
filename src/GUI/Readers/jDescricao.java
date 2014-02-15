package GUI.Readers;

import java.awt.Insets;
import java.text.ParseException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class jDescricao implements ReaderStrategy {
    
    @Override
    public String generatePane(String mensagem_da_janela, String rotulo_da_janela){
        
            JTextArea textField = new JTextArea();
            textField.setLineWrap(true);
            textField.setWrapStyleWord(true);
            textField.setMargin(new Insets(5,5,5,5));  
            JScrollPane scroll = new JScrollPane(textField);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            JLabel rotulo = new JLabel(mensagem_da_janela);
            JPanel tela = new JPanel();
            tela.add(rotulo);
            tela.add(scroll);
            JOptionPane.showMessageDialog(null, tela, rotulo_da_janela, JOptionPane.PLAIN_MESSAGE);
            return textField.getText();
        
            
    }
}
