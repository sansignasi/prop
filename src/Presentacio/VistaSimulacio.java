package src.Presentacio;

import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class VistaSimulacio {
    private CtrlPresentacion controladorPresentacion;

    private JFrame frameVista = new JFrame("Vista Simulació");
    private JPanel panel1;
    private JList list1;
    private JLabel gif;
    private JButton backButton;
    private DefaultListModel model1 = new DefaultListModel();
    private ArrayList<String> p;
    private String a;
    private String d;


    public VistaSimulacio(CtrlPresentacion c) throws MalformedURLException {
        controladorPresentacion = c;
        inicializarComponentes();
        asignarListenersComponentes();

    }
    private void inicializarComponentes() { //todas las preferencias de cada componente iran aqui(hacer una funcion nueva pa cada comp)
        inicializarFrameVista();
        list1.setModel(model1);
        URL url = this.getClass().getResource("Loading.gif");
        Icon icon = new ImageIcon(url);
        gif.setIcon(icon);
    }

    private void inicializarFrameVista() { //preferencias del Jframe
        frameVista.setPreferredSize(new Dimension(450, 345));
        //frameVista.setBounds(50, 50, 700, 400);
        frameVista.setLocation(550,215);
        frameVista.setResizable(false);
        frameVista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameVista.setContentPane(panel1);
    }


    public void hacerVisible(ArrayList<String> probs, String atacant, String defensor) throws IncorrectFENException {
        gif.setVisible(true);
        model1.clear();
        a = atacant;
        p = probs;
        d = defensor;
        frameVista.setEnabled(true);
        frameVista.pack();
        frameVista.setVisible(true);
        backButton.setVisible(false);
        aTask t1 = new aTask();
        t1.execute();
        //enviarresultatsimulacio(controladorPresentacion.ferSimulacio(probs,atacant,defensor));
    }

    public void desactivar() {
        frameVista.setVisible(false);
        frameVista.setEnabled(false);
    }

    public void enviarresultatsimulacio(ArrayList<Boolean> b) {
        for(int i = 0; i < b.size();++i){
            if(b.get(i))model1.addElement((i+1)+". "+p.get(i)+": L'atacant ha guanyat la partida!");
            else model1.addElement((i+1)+". "+p.get(i)+": L'atacant ha perdut la partida");
        }
    }

    private void asignarListenersComponentes() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desactivar();
            }
        });



    }

    private class aTask extends SwingWorker<ArrayList<Boolean>,Void>{

        @Override
        protected ArrayList<Boolean> doInBackground() throws Exception {
            ArrayList<Boolean> b = controladorPresentacion.ferSimulacio(p,a,d);
            return b;
        }

        @Override
        protected void done(){
            try {
                ArrayList<Boolean> b = get();
                enviarresultatsimulacio(b);
                URL url = this.getClass().getResource("success.jpg");
                Icon icon = new ImageIcon(url);
                gif.setIcon(icon);
                gif.setVisible(false);
                backButton.setVisible(true);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
