package src;

import src.Controladors.CtrlDomini;
import src.Domini.IncorrectFENException;
import src.Controladors.CtrlPresentacion;

public class Main {

    public static void main(String[] args) throws IncorrectFENException {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    CtrlDomini cd = CtrlDomini.getInstance();
                } catch (IncorrectFENException e) {
                    e.printStackTrace();
                }
                CtrlPresentacion mainWindow = null;
                try {
                    mainWindow = CtrlPresentacion.getInstance();
                }
                catch (IncorrectFENException e) {
                    e.printStackTrace();
                }
                try {
                    //cd.menuPrincipal(); //descomentar para probar programa
                    mainWindow.inicializarPresentacion(); //parte grafica
                } catch (IncorrectFENException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}