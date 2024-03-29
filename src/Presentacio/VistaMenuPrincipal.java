package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenuPrincipal {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Menu Principal");
    private JPanel panel1;
    private JLabel LabelCurrentUsuer;
    private JButton logoutButton;
    private JButton jugarProblemaButton;
    private JButton gestionarProblemesButton;
    private JButton simularProblemesButton;
    private JLabel currentUserLabel;

    public VistaMenuPrincipal(CtrlPresentacion c){
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();

    }

    private void inicializarComponentes(){
        inicializarFrameVista();
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700,400));
        frameVista.setBounds(50,50,700,400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);


        //logoutButton.setBounds(50,50,100,30); //para mover de sitio el boton
        //frameVista.setContentPane(logoutButton);
    }



    public void hacerVisible() {
        LabelCurrentUsuer.setText(controladorPresentacion.getCurrentuser());
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void desactivar() {
        frameVista.setVisible(false);
        frameVista.setEnabled(false);
    }

    private void asignarListenersComponentes() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                controladorPresentacion.cambiarVistaALogin();
            }
        });

        jugarProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                try {
                    controladorPresentacion.cambiarVistaASelecProbJugar();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        simularProblemesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                try {
                    controladorPresentacion.cambiarVistaAEscollirProbsSimulacio();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });

        gestionarProblemesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                try {
                    controladorPresentacion.cambiarVistaAGestioDeProblemes();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });






    }


}
