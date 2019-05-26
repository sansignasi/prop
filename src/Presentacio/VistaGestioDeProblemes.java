package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
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
        asignarListenersComponentes();

    }

    private void inicializarFrameVista() throws Exception { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700, 495));
        frameVista.setBounds(50, 50, 700, 400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);
        inicializarLlistaProblemas();
    }

    private void inicializarLlistaProblemas() throws Exception {
        DefaultListModel model = new DefaultListModel();
        list1.setModel(model);
        ArrayList<String> noms = controladorPresentacion.getNomProblemesUsuari();
        for(int i = 0; i < noms.size();++i){
            model.addElement(noms.get(i));
        }
    }

    public void hacerVisible() throws Exception {
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
        inicializarLlistaProblemas();
    }

    public void desactivar() {
        frameVista.setVisible(false);
        frameVista.setEnabled(false);
    }

    private void asignarListenersComponentes() {
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String nomp = list1.getSelectedValue().toString();
                labelMoviments.setText(String.valueOf(controladorPresentacion.getMovimentsProblema(nomp)));
                labelDificultat.setText(controladorPresentacion.getDificultadProblema(nomp));
                labelAutor.setText(controladorPresentacion.getCreadorProblema(nomp));
                labelAutor.setVisible(true);
                labelDificultat.setVisible(true);
                labelMoviments.setVisible(true);
                modificarButton.setEnabled(true);
                eliminarButton.setEnabled(true);
                previewButton1.setEnabled(true);
                DefaultListModel model2 = new DefaultListModel();
                list2.setModel(model2);

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                controladorPresentacion.cambiarVistaAMenuPrincipal();
            }
        });

        previewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomp = list1.getSelectedValue().toString();
                try {
                    System.out.print("hola ");
                    controladorPresentacion.cambiarVistaAPreview(nomp);
                } catch (IncorrectFENException e1) {
                    e1.printStackTrace();
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        importarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
