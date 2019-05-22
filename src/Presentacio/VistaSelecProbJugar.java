package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.Maquina1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaSelecProbJugar {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Menu Principal");
    private JPanel panel1;
    private JButton vsJugadorButton;
    private JButton vsMaquina1Button;
    private JButton vsMaquina2Button;
    private JButton backButton;


    public VistaSelecProbJugar(CtrlPresentacion c){
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();

    }

    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();

    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700, 400));
        frameVista.setBounds(50, 50, 700, 400);
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
                controladorPresentacion.cambiarVistaAMenuPrincipal();

            }
        });

    }
}
