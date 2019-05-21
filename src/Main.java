package src;

import src.presentacio.CtrlPresentacion;

public class Main {

    public static void main(String[] args) throws IncorrectFENException{

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CtrlPresentacion mainWindow = new CtrlPresentacion();
                try {
                    mainWindow.inicializarPresentacion();
                } catch (IncorrectFENException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}