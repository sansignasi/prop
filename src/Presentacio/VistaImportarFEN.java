package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class VistaImportarFEN {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Validar");
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton backButton;
    private JLabel gif;
    private String FEN;
    private int movs;
    private String nomprob;


    public VistaImportarFEN(CtrlPresentacion c) throws MalformedURLException {
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();

    }

    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();
        URL url = this.getClass().getResource("Loading.gif");
        Icon icon = new ImageIcon(url);
        gif.setIcon(icon);
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(450, 345));
        //frameVista.setBounds(50, 50, 700, 400);
        frameVista.setLocation(550,215);
        frameVista.setResizable(false);
        frameVista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }

    public void hacerVisible(String FEN, int n,String nomprob) throws IncorrectFENException {
        gif.setVisible(true);
        this.nomprob = nomprob;
        this.FEN = FEN;
        movs = n;
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
        backButton.setVisible(false);
        aTask t1 = new aTask();
        t1.execute();
        //enviarresultatsimulacio(controladorPresentacion.ferSimulacio(probs,atacant,defensor));
    }

    public void desactivar() {
        frameVista.setVisible(false);
        frameVista.setEnabled(false);
    }
    private void asignarListenersComponentes() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                textArea1.setText("");
            }
        });

    }

    private class aTask extends SwingWorker<Boolean,Void>{

        @Override
        protected Boolean doInBackground() throws IncorrectFENException {
            try{
                Boolean b = controladorPresentacion.validarProblema(FEN,movs);
                return b;
            }
            catch (IncorrectFENException e){
                JOptionPane.showMessageDialog(null, "FEN incorrecte");
            }
            return false;
        }

        @Override
        protected void done(){
            try {
                Boolean b = get();
                gif.setVisible(false);
                backButton.setVisible(true);
                if(b){
                    textArea1.append("El Problema s'ha validat amb éxit i s'ha afegit a la base de problemes.");
                    controladorPresentacion.afegirProblemaBP(FEN,movs,nomprob);
                }
                else{
                    textArea1.append("El Problema no s'ha pogut validar amb éxit.");
                }

            } catch (IncorrectFENException e) {
                JOptionPane.showMessageDialog(null, "FEN incorrecte");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
