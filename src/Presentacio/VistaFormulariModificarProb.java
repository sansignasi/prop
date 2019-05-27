package src.Presentacio;

import src.Controladors.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaFormulariModificarProb {
    CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Informació problema a modificar");
    private JTextField text_nom;
    private JPanel panel1;
    private JComboBox movs;
    private JComboBox color;
    private JButton backButton;
    private JButton crearProblemaButton;
    private String FENincompleto;
    private String nomprob;

    public VistaFormulariModificarProb(CtrlPresentacion c){
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

    public void hacerVisible(String s, String nomprob) {
        this.nomprob = nomprob;
        FENincompleto = s;
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
            }
        });

        crearProblemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomp = text_nom.getText();
                String atacant = (String)color.getSelectedItem();
                int nmovs = movs.getSelectedIndex()+1;
                String atk;
                if(atacant.equals("Blanques"))atk = "w";
                else atk = "b";
                String fen = FENincompleto+" "+atk+" - - 0 1";
                if (nomp.isEmpty()) JOptionPane.showMessageDialog(null, "Introdueix el nom del problema");
                else {
                    try {
                        if(controladorPresentacion.getNomProblemes().contains(nomp)){
                            JOptionPane.showMessageDialog(null, "Ja existeix un problema amb aquest nom.");
                        }
                        else if(controladorPresentacion.existeixFENambNmovs(fen,nmovs)){
                            JOptionPane.showMessageDialog(null, "Ja existeix un problema amb aquest FEN i aquest nombre de moviments");
                        }
                        else{
                            //controladorPresentacion.cambiarVista
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }
}
