package src.Presentacio;

import src.Controladors.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaMenuImportar {
    CtrlPresentacion controladorPresentacion;
    private JTextField text_nom;
    private JPanel panel1;
    private JFrame frameVista = new JFrame("Importar FEN");
    private JTextField text_fen;
    private JButton crearProblemaButton;
    private JButton backButton;
    private JComboBox comboBox1;

    public VistaMenuImportar(CtrlPresentacion c){
        controladorPresentacion = c;
        inicializarComponentes(); //iniciar las configs de cada elemento
        asignarListenersComponentes(); //asignar listeners de los elementos(clikar boton, etc...)
    }

    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(500,200));
        frameVista.setResizable(false);
        frameVista.setLocation(400,215);
        frameVista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                try {
                    controladorPresentacion.cambiarVistaAGestioDeProblemes();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        crearProblemaButton.addActionListener(new ActionListener() { //boton de login
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomp = text_nom.getText();
                String fen = text_fen.getText();
                int nmovs = comboBox1.getSelectedIndex()+1;
                if (nomp.isEmpty()) JOptionPane.showMessageDialog(null, "Introdueix el nom del problema");
                else {
                    if (fen.isEmpty()) JOptionPane.showMessageDialog(null, "Introdueix el FEN del problema.");
                    else {
                        try {
                            if(controladorPresentacion.getNomProblemes().contains(nomp)){
                                JOptionPane.showMessageDialog(null, "Ja existeix un problema amb aquest nom.");
                            }
                            else if(controladorPresentacion.existeixFENambNmovs(fen,nmovs)){
                                JOptionPane.showMessageDialog(null, "Ja existeix un problema amb aquest FEN i aquest nombre de moviments");
                            }
                            else{
                                controladorPresentacion.cambiarVistaAImportarFEN(fen,nmovs,nomp);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    }
                }
        });
    }
}
