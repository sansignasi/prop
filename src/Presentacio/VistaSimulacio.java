package src.Presentacio;

import src.Controladors.CtrlPresentacion;


import javax.swing.*;
import java.awt.*;

public class VistaSimulacio {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Simulació");
    private JTextArea textArea1;
    private JPanel panel1;
    private JList list1;
    private DefaultListModel model1 = new DefaultListModel();


    public VistaSimulacio(CtrlPresentacion c){
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();
    }
    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();
        list1.setModel(model1);

    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700, 365));
        frameVista.setBounds(50, 50, 700, 400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }


    public void hacerVisible(){
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void desactivar() {
        frameVista.setVisible(false);
        frameVista.setEnabled(false);
    }

    public void enviarresultatsimulacio(Boolean b) {

        if(b)model1.addElement("L'atacant ha guanyat la partida!");
        else model1.addElement("L'atacant ha perdut la partida");
    }

    private void asignarListenersComponentes() {


    }



}
