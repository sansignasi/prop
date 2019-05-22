package src.Controladors;

import src.Domini.IncorrectFENException;
import src.Presentacio.*;

public class CtrlPresentacion {//hola
    private CtrlDomini controladorDomini;
    private VistaLogin vistaLogin;
    private VistaFormularioLogin vistaFormularioLogin;
    private VistaFormularioRegistro vistaFormularioRegistro;
    private VistaMenuPrincipal vistaMenuPrincipal;
    private VistaSelecProbJugar vistaSelecProbJugar;
    private Board board;

    public CtrlPresentacion() throws IncorrectFENException {
        controladorDomini = CtrlDomini.getInstance();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
        vistaFormularioRegistro = new VistaFormularioRegistro(this);
        vistaMenuPrincipal = new VistaMenuPrincipal(this);
        vistaSelecProbJugar = new VistaSelecProbJugar(this);
        board = new Board(this);
    }

    public void inicializarPresentacion() throws IncorrectFENException {
        //controladorDomini.menuPrincipal(); //descomentar para testear dominio
        //vistaLogin.hacerVisible(); //descomentar para testear presentacion
        board.hacerVisible();
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

    public void cambiarVistaASelecProbJugar(){
        vistaSelecProbJugar.hacerVisible();
    }

    public int verificarusuari(String user, String psw){//0 OK 1 contra incorrecta 2 no existe user
        int i = controladorDomini.verificarusuari(user,psw);
        return i;

    }
    public void setCurrentuser(String user){
        controladorDomini.setCurrentuser(user);
    }

    public String getCurrentuser(){
        return controladorDomini.getCurrentuser();
    }

    public void crearusuari(String user, String psw){
        controladorDomini.crearusuari(user,psw);
    }

    public char[][] matriuProblema(String nomprob) throws IncorrectFENException {
        return controladorDomini.matriuProblema(nomprob);
    }
}