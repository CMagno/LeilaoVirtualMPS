package GUI;

import java.awt.HeadlessException;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Felipe
 */
public class JOptionPaneFake {

    public static Calendar showInputDateDialog(String mensagem_da_janela, String rotulo_da_janela) {
        Calendar c = null;
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            JFormattedTextField textField = new JFormattedTextField(mascara);
            JLabel rotulo = new JLabel(mensagem_da_janela);
            JPanel tela = new JPanel();
            tela.add(rotulo);
            tela.add(textField);
            JOptionPane.showMessageDialog(null, tela, rotulo_da_janela, JOptionPane.PLAIN_MESSAGE);
            String dataValida = textField.getText();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date d = formatter.parse(dataValida);
            c = Calendar.getInstance();
            c.setTime(d);
        } catch (Exception ex) {
            return null;
        }
        return c;
    }
    
    public static String showInputTextDialog(String mensagem_da_janela, String rotulo_da_janela) {
        try {
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
        } catch (Exception ex) {
            return null;
        }
    }
}
