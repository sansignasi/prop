package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class VistaEscollirProbsSimulacio {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Selecció de problemes a simular");
    private JPanel panel1;
    private JButton backButton;
    private JButton afegirButton;
    private JButton eliminarButton;
    private JButton simularButton;
    private JScrollPane scrollpane1;
    private JList list1;
    private JLabel text1;
    private JList list2;
    private JComboBox atacant;
    private JComboBox defensor;
    private DefaultListModel model1 = new DefaultListModel();
    private DefaultListModel model2 = new DefaultListModel();
    private String nompselec1;
    private String nompselec2;

    public VistaEscollirProbsSimulacio(CtrlPresentacion c) throws Exception {
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();
        inicializarLlistaProblemas();
    }

    private void inicializarComponentes() throws IncorrectFENException { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        afegirButton.setEnabled(false);
        eliminarButton.setEnabled(false);
        simularButton.setEnabled(false);
        list2.setModel(model2);
        list1.setModel(model1);
        inicializarFrameVista();

    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(700, 365));
        frameVista.setBounds(50, 50, 700, 400);
        frameVista.setResizable(false);
        frameVista.setLocationRelativeTo(null);
        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }

    private void inicializarLlistaProblemas() throws Exception {
        Set<String> noms = controladorPresentacion.getNomProblemes();
        for (Iterator i = noms.iterator(); i.hasNext();) {
            model1.addElement(i.next());
        }
    }

    public void hacerVisible() throws IncorrectFENException {
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

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                afegirButton.setEnabled(true);
                eliminarButton.setEnabled(false);
            }
        });

        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                eliminarButton.setEnabled(true);
                afegirButton.setEnabled(false);

            }
        });

        afegirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) list1.getSelectedValue();
                if(!model2.contains(s)) model2.addElement(s);
                else JOptionPane.showMessageDialog(null,"Ja has afegit aquest problema a la simulació");
                simularButton.setEnabled(true);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) list2.getSelectedValue();
                model2.removeElement(s);
                if(model2.isEmpty())simularButton.setEnabled(false);
            }
        });

        simularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> probs = new ArrayList<>();
                for(int i = 0; i< list2.getModel().getSize();i++){
                    probs.add((String) list2.getModel().getElementAt(i));
                }
                try {
                    controladorPresentacion.cambiarVistaASimulacion(probs,atacant.getSelectedItem().toString(),defensor.getSelectedItem().toString());
                } catch (IncorrectFENException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}
