package src.Presentacio;

import src.Controladors.CtrlPresentacion;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Set;

public class VistaGestioDeProblemes {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Gestió de problemes");
    private JPanel panel1;
    private JButton backButton;
    private JButton previewButton1;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JButton crearButton;
    private JList list2;
    private JLabel labelMoviments;
    private JLabel labelDificultat;
    private JLabel labelAutor;
    private JScrollPane scrollpane1;
    private JList list1;
    private JLabel text1;
    private JButton importarButton;

    public VistaGestioDeProblemes(CtrlPresentacion c) throws Exception {
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();
    }

    private void inicializarComponentes() throws Exception { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        labelAutor.setVisible(false);
        labelDificultat.setVisible(false);
        labelMoviments.setVisible(false);
        previewButton1.setEnabled(false);
        eliminarButton.setEnabled(false);
        modificarButton.setEnabled(false);
        inicializarFrameVista();
        inicializarLlistaProblemas();
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700, 495));
        frameVista.setBounds(50, 50, 700, 400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }

    private void inicializarLlistaProblemas() throws Exception {
        DefaultListModel model = new DefaultListModel();
        list1.setModel(model);
        Set<String> noms = controladorPresentacion.getNomProblemesUsuari();
        for (Iterator i = noms.iterator(); i.hasNext();) {
            model.addElement(i.next());
        }
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


    }


}
