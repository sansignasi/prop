package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;
import src.Domini.Maquina1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class VistaSelecProbJugar {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Selecció de problema a jugar");
    private JPanel panel1;
    private JButton vsJugadorButton;
    private JButton vsMaquina1Button;
    private JButton vsMaquina2Button;
    private JButton backButton;
    private JList list1;
    private JLabel text1;
    private JScrollPane scrollpane1;
    private JLabel labelAutor;
    private JLabel labelDificultat;
    private JLabel labelMoviments;


    public VistaSelecProbJugar(CtrlPresentacion c) throws IncorrectFENException {
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();

    }

    private void inicializarComponentes() throws IncorrectFENException { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        labelAutor.setVisible(false);
        labelDificultat.setVisible(false);
        labelMoviments.setVisible(false);
        vsJugadorButton.setEnabled(false);
        vsMaquina1Button.setEnabled(false);
        vsMaquina2Button.setEnabled(false);
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

    private void inicializarLlistaProblemas() throws IncorrectFENException {
        DefaultListModel model = new DefaultListModel();
        list1.setModel(model);
        Set<String> noms = controladorPresentacion.getNomProblemes();
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
                controladorPresentacion.cambiarVistaAMenuPrincipal();
            }
        });

        vsJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vsMaquina1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vsMaquina2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

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
                vsJugadorButton.setEnabled(true);
                vsMaquina1Button.setEnabled(true);
                vsMaquina2Button.setEnabled(true);
            }
        });

    }
}