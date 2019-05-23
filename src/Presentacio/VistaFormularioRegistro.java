package src.Presentacio;

import src.Controladors.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaFormularioRegistro {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Formulario Registro");
    private JPanel panel1;
    private JTextField textField1;
    private JButton backButton;
    private JButton registerButton;
    private JTextField textField2;


    public VistaFormularioRegistro(CtrlPresentacion c) {
        controladorPresentacion = c;
        inicializarComponentes(); //iniciar las configs de cada elemento
        asignarListenersComponentes(); //asignar listeners de los elementos(clikar boton, etc...)

    }

    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700,400));
        frameVista.setBounds(50,50,700,400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }

    public void hacerVisible() {
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
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
                controladorPresentacion.cambiarVistaALogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField1.getText();
                if(user.isEmpty())JOptionPane.showMessageDialog(null,"Introdueix nom d'usuari.");
                else{
                    String psw = textField2.getText();
                    if(psw.isEmpty())JOptionPane.showMessageDialog(null,"Introdueix contrasenya.");
                    else{
                        int i = controladorPresentacion.verificarusuari(user,psw);
                        if(i == 0 || i == 1)JOptionPane.showMessageDialog(null,"Aquest nom d'usuari ja existeix.");
                        else { //registramos en nuevo user
                            /*
                            controladorPresentacion.crearusuari(user,psw);*/
                            desactivar();
                            controladorPresentacion.cambiarVistaAMenuPrincipal();
                        }
                        }
                    }

                }

                //int i = controladorPresentacion.verificarusuari(user,psw);

        });




    }

}