package tema2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel continut=new JPanel();
    private JLabel dateIntrare = new JLabel("<html>Numar clienti:<br/><br/>\r\nNumar cozi:<br/><br/>\r\n" +
            "Timp de simulare:<br/><br/>\r\nTimp servire minim:<br/><br/>\r\nTimp servive maxim:<br/><br/>" +
            "\r\nTimp sosire minim:<br/><br/>\r\nTimp sosire maxim:</html>");
    private JTextField textFieldNumarClienti = new JTextField();
    private JTextField textFieldNumarCozi = new JTextField();
    private JTextField textFieldTimpDeSimulare = new JTextField();
    private JTextField textFieldTimpServireMinim = new JTextField();
    private JTextField textFieldTimpServireMaxim = new JTextField();
    private JTextField textFieldTimpArriveMinim = new JTextField();
    private JTextField textFieldTimpArriveMaxim = new JTextField();
    private JButton btnSimulare = new JButton("Simuleaza");
    private JEditorPane afisare = new JEditorPane();

    public View(){
        this.setBounds(100, 100, 836, 588);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        continut.setLayout(null);
        this.setTitle("Simulator Cozi");

        dateIntrare.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dateIntrare.setBounds(28, 10, 147, 255);
        continut.add(dateIntrare);

        afisare.setFont(new Font("Tahoma", Font.PLAIN, 12));
        afisare.setEnabled(true);
        afisare.setEditable(false);
        afisare.setBounds(28, 310, 769, 207);
        continut.add(afisare);

        textFieldNumarClienti.setBounds(219, 28, 96, 19);
        continut.add(textFieldNumarClienti);
        textFieldNumarCozi.setBounds(219, 57, 96, 19);
        continut.add(textFieldNumarCozi);
        textFieldTimpDeSimulare.setBounds(219, 96, 96, 19);
        continut.add(textFieldTimpDeSimulare);
        textFieldTimpServireMinim.setBounds(219, 125, 96, 19);
        continut.add(textFieldTimpServireMinim);
        textFieldTimpServireMaxim.setBounds(219, 159, 96, 19);
        continut.add(textFieldTimpServireMaxim);
        textFieldTimpArriveMinim.setBounds(219, 194, 96, 19);
        continut.add(textFieldTimpArriveMinim);
        textFieldTimpArriveMaxim.setBounds(219, 228, 96, 19);
        continut.add(textFieldTimpArriveMaxim);

        btnSimulare.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSimulare.setBounds(384, 227, 182, 21);
        continut.add(btnSimulare);

        this.setContentPane(continut);
    }
    public String getNumarClienti(){ return textFieldNumarClienti.getText(); }
    public String getNumarCozi() { return textFieldNumarCozi.getText();}
    public String getTimpDeSimulare() { return textFieldTimpDeSimulare.getText(); }
    public String getTimpServireMinim() { return textFieldTimpServireMinim.getText(); }
    public String getTimpServireMaxim() { return textFieldTimpServireMaxim.getText(); }
    public String getTimpArriveMinim() { return textFieldTimpArriveMinim.getText(); }
    public String getTimpArriveMaxim() { return textFieldTimpArriveMaxim.getText(); }
    public void simuleazaActionListener (ActionListener a){ btnSimulare.addActionListener(a); }
    public void setAfisare(String mesaj){
        afisare.setText(mesaj);
    }
}
