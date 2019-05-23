package src.Controladors;

import src.Domini.IncorrectFENException;
import src.Presentacio.*;

import java.util.ArrayList;
import java.util.Set;

public class CtrlPresentacion {
    private CtrlDomini controladorDomini;
    private static CtrlPresentacion ctrlPresentacion;
    private VistaLogin vistaLogin;
    private VistaFormularioLogin vistaFormularioLogin;
    private VistaFormularioRegistro vistaFormularioRegistro;
    private VistaMenuPrincipal vistaMenuPrincipal;
    private VistaSelecProbJugar vistaSelecProbJugar;
    private Board board;
    private VistaEscollirProbsSimulacio vistaEscollirProbsSimulacio;

    public static CtrlPresentacion getInstance() throws IncorrectFENException {
        if (ctrlPresentacion == null)
            ctrlPresentacion = new CtrlPresentacion() {
            };
        return ctrlPresentacion;
    }

    private CtrlPresentacion() {

    }
    private void asignar() throws IncorrectFENException {
        controladorDomini = CtrlDomini.getInstance();
        controladorDomini.reload();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
        vistaFormularioRegistro = new VistaFormularioRegistro(this);
        vistaMenuPrincipal = new VistaMenuPrincipal(this);
        vistaSelecProbJugar = new VistaSelecProbJugar(this);
        board = new Board(this);
        vistaEscollirProbsSimulacio = new VistaEscollirProbsSimulacio(this);
    }
    public void inicializarPresentacion() throws IncorrectFENException {
        asignar();
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

    public void cambiarVistaAEscollirProbsSimulacio() throws IncorrectFENException {vistaEscollirProbsSimulacio.hacerVisible();}

    public void cambiarVistaABoard() throws IncorrectFENException {
        board.hacerVisible();
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

    public Set<String> getNomProblemes() throws IncorrectFENException {
        return controladorDomini.getNomProblemes();
    }

    public int getMovimentsProblema(String s){
        return controladorDomini.getMovimentsProblema(s);
    }

    public String getCreadorProblema(String s){
        return controladorDomini.getCreadorProblema(s);
    }

    public String getDificultadProblema(String s){
        return controladorDomini.getDificultadProblema(s);
    }



}