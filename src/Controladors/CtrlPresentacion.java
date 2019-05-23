package src.Controladors;

import src.Domini.IncorrectFENException;
import src.Presentacio.VistaFormularioLogin;
import src.Presentacio.VistaFormularioRegistro;
import src.Presentacio.VistaLogin;
import src.Presentacio.VistaMenuPrincipal;

public class CtrlPresentacion {
    private CtrlDomini controladorDomini;
    private VistaLogin vistaLogin;
    private VistaFormularioLogin vistaFormularioLogin;
    private VistaFormularioRegistro vistaFormularioRegistro;
    private VistaMenuPrincipal vistaMenuPrincipal;

    public CtrlPresentacion() {
        controladorDomini = CtrlDomini.getInstance();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
        vistaFormularioRegistro = new VistaFormularioRegistro(this);
        vistaMenuPrincipal = new VistaMenuPrincipal(this);
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

    public void cambiarVistaAMenuPrincipal(){
        vistaMenuPrincipal.hacerVisible();
    }

    public int verificarusuari(String user, String psw){//0 OK 1 contra incorrecta 2 no existe user
        int i = controladorDomini.verificarusuari(user,psw);
        return i;

    }/*
    public void setCurrentuser(String user){
        controladorDomini.setCurrentuser(user);
    }

    public String getCurrentuser(){
        return controladorDomini.getCurrentuser();
    }
    public void crearusuari(String user, String psw){
        controladorDomini.crearusuari(user,psw);
    }*/
}