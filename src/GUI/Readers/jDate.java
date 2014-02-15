package GUI.Readers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class jDate implements ReaderStrategy {

    @Override
    public String generatePane(String mensagem_da_janela, String rotulo_da_janela) {


        MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            System.err.println("nao foi possivel gerar mascara");
        }

        JFormattedTextField textField = new JFormattedTextField(mascara);
        JLabel mensagem = new JLabel(mensagem_da_janela);
        JPanel tela = new JPanel();
        tela.add(mensagem);
        tela.add(textField);
        JOptionPane.showMessageDialog(null, tela, rotulo_da_janela, JOptionPane.PLAIN_MESSAGE);
        return textField.getText();
    }
}
