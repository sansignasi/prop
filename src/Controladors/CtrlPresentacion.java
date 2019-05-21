package src.Controladors;

import src.Domini.IncorrectFENException;
import src.Presentacio.VistaFormularioLogin;
import src.Presentacio.VistaFormularioRegistro;
import src.Presentacio.VistaLogin;

public class CtrlPresentacion {
    private CtrlDomini controladorDomini;
    private VistaLogin vistaLogin;
    private VistaFormularioLogin vistaFormularioLogin;
    private VistaFormularioRegistro vistaFormularioRegistro;

    public CtrlPresentacion() {
        controladorDomini = CtrlDomini.getInstance();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
        vistaFormularioRegistro = new VistaFormularioRegistro(this);
    }

    public void inicializarPresentacion() throws IncorrectFENException {
        //controladorDomini.menuPrincipal(); //descomentar para testear dominio
        vistaLogin.hacerVisible(); //descomentar para testear presentacion
    }

    public void cambiarVistaAFormularioLogin() {
        vistaFormularioLogin.hacerVisible();
    }
    public void cambiarVistaALogin() {
        vistaLogin.hacerVisible();
    }

    public void cambiarVistaAFormularioRegister(){
        vistaFormularioRegistro.hacerVisible();
    }

    public int verificarusuari(String user, String psw){//0 OK 1 contra incorrecta 2 no existe user
        System.out.print("holactrlpresent");
        int i = controladorDomini.verificarusuari(user,psw);
        return i;

    }
}