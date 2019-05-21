package src.presentacio;

import src.CtrlDomini;
import src.IncorrectFENException;
import src.presentacio.VistaFormularioLogin;
import src.presentacio.VistaLogin;

public class CtrlPresentacion {
    private CtrlDomini controladorDomini;
    private VistaLogin vistaLogin;
    private VistaFormularioLogin vistaFormularioLogin;

    public CtrlPresentacion() {
        controladorDomini = new CtrlDomini();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
    }

    public void inicializarPresentacion() throws IncorrectFENException {
        //controladorDomini.menuPrincipal(); //descomentar para testear dominio
        vistaLogin.hacerVisible(); //descomentar para testear presentacion
    }

    public void cambiarVistaAFormularioLogin() {
        vistaLogin.desactivar();
        vistaFormularioLogin.hacerVisible();
    }
    public void cambiarVistaALogin() {
        vistaFormularioLogin.desactivar();
        vistaLogin.hacerVisible();
    }
}