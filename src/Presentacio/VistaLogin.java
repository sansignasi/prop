package src.Presentacio;



import src.Controladors.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Login");
    private JButton loginButton;
    private JPanel panel1;
    private JButton registerButton;
    private JLabel label;

    public VistaLogin(CtrlPresentacion c){
        controladorPresentacion = c;
        inicializarComponentes(); //iniciar las configs de cada elemento
        asignarListenersComponentes(); //asignar listeners de los elementos(clikar boton, etc...)

    }

    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();

    }
    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700,400));
        frameVista.setResizable(false);
        frameVista.setBounds(50,50,700,400);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);

    }
    public void hacerVisible() {
        frameVista.setEnabled(true);
        //frameVista.pack();
        frameVista.setVisible(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
        frameVista.setVisible(false);
    }

    private void asignarListenersComponentes() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                controladorPresentacion.cambiarVistaAFormularioLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                controladorPresentacion.cambiarVistaAFormularioRegister();
            }
        });


    }

}


