package src;

import src.presentacio.CtrlPresentacion;

public class Main {

    public static void main(String[] args) throws IncorrectFENException{

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CtrlDomini cd = new CtrlDomini();
                CtrlPresentacion mainWindow = new CtrlPresentacion();
                try {
                    //cd.menuPrincipal(); //descomentar para probar programa
                    mainWindow.inicializarPresentacion(); //parte grafica
                } catch (IncorrectFENException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}