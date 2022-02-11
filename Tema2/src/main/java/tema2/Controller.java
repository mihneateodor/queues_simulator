package tema2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Executie exec;
    private GeneratorClienti gen;
    private View view;

    public Controller(View view){
        this.view=view;
        view.simuleazaActionListener(new SimuleazaActionListener());
    }

    class SimuleazaActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String nrClientiS, nrCoziS, timpDeSimulareS, timpServireMinimS, timpServireMaximS, timpArriveMinimS, timpArriveMaximS;
            Integer nrClienti, nrCozi, timpDeSimulare, timpServireMinim, timpServireMaxim, timpArriveMinim, timpArriveMaxim;
            try{
                nrClientiS=view.getNumarClienti();
                nrCoziS=view.getNumarCozi();
                timpDeSimulareS=view.getTimpDeSimulare();
                timpServireMinimS=view.getTimpServireMinim();
                timpServireMaximS=view.getTimpServireMaxim();
                timpArriveMinimS=view.getTimpArriveMinim();
                timpArriveMaximS= view.getTimpArriveMaxim();
                nrClienti= Integer.parseInt(nrClientiS);
                nrCozi = Integer.parseInt(nrCoziS);
                timpDeSimulare = Integer.parseInt(timpDeSimulareS);
                timpServireMinim = Integer.parseInt(timpServireMinimS);
                timpServireMaxim = Integer.parseInt(timpServireMaximS);
                timpArriveMinim = Integer.parseInt(timpArriveMinimS);
                timpArriveMaxim = Integer.parseInt(timpArriveMaximS);
                gen=new GeneratorClienti(nrClienti,timpServireMinim,timpServireMaxim,timpArriveMinim,timpArriveMaxim);
                exec=new Executie(nrCozi,gen,timpDeSimulare,view);
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(view, "Reintrodu date numerice!");
            }
        }
    }
}
