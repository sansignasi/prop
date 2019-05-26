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
    private BoardPreview boardPreview;
    private VistaEscollirProbsSimulacio vistaEscollirProbsSimulacio;
    private VistaSimulacio vistaSimulacio;
    private VistaGestioDeProblemes vistaGestioDeProblemes;
    private VistaMenuImportar vistaImportar;


    public static CtrlPresentacion getInstance(){
        if (ctrlPresentacion == null)
            ctrlPresentacion = new CtrlPresentacion() {
            };
        return ctrlPresentacion;
    }

    private CtrlPresentacion() {

    }
    private void asignar() throws Exception {
        controladorDomini = CtrlDomini.getInstance();
        controladorDomini.reload();
        vistaLogin = new VistaLogin(this);
        vistaFormularioLogin = new VistaFormularioLogin(this);
        vistaFormularioRegistro = new VistaFormularioRegistro(this);
        vistaMenuPrincipal = new VistaMenuPrincipal(this);
        vistaSelecProbJugar = new VistaSelecProbJugar(this);
        vistaEscollirProbsSimulacio = new VistaEscollirProbsSimulacio(this);
        vistaSimulacio = new VistaSimulacio(this);
        vistaGestioDeProblemes = new VistaGestioDeProblemes(this);
        vistaImportar = new VistaMenuImportar(this);
    }
    public void inicializarPresentacion() throws Exception {
        asignar();
        controladorDomini.CarregaBP();
        controladorDomini.CarregaBU();
        controladorDomini.afegirProblema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1",2,"prob1");
        vistaLogin.hacerVisible(); //descomentar para testear presentacion
        //vistaImportar.hacerVisible();
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

    public void cambiarVistaASelecProbJugar() throws Exception {
        vistaSelecProbJugar.hacerVisible();
    }

    public void cambiarVistaAEscollirProbsSimulacio() throws Exception {vistaEscollirProbsSimulacio.hacerVisible();}

    public void cambiarVistaABoard(){
        board.hacerVisible();
    }

    public void cambiarVistaAJugarPartida(String tipusjug,String nomprob) throws IncorrectFENException {
        board = new Board(this,nomprob);
        board.hacerVisible();
    }
    public void cambiarVistaAPreview(String nomprob) throws IncorrectFENException {
        boardPreview = new BoardPreview(this,nomprob);
        boardPreview.hacerVisible();
    }

    public void cambiarVistaASimulacion(ArrayList<String> probs, String atacant, String defensor) throws IncorrectFENException {
        vistaSimulacio.hacerVisible(probs,atacant,defensor);
    }

    public void cambiarVistaAGestioDeProblemes() throws Exception {
        vistaGestioDeProblemes.hacerVisible();
    }

    public void cambiarVistaAImportar() {
        vistaImportar.hacerVisible();
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

    public void crearusuari(String user, String psw) throws Exception {
        controladorDomini.crearusuari(user,psw);
    }

    public char[][] matriuProblema(String nomprob) {
        return controladorDomini.matriuProblema(nomprob);
    }

    public Set<String> getNomProblemes() throws Exception {
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

    public void actualitzaBoard(char[][] mchar) throws IncorrectFENException {
        //board.actualitzaMchar(mchar);
    }


    public ArrayList<Boolean> ferSimulacio(ArrayList<String> probs, String atacant, String defensor) throws IncorrectFENException {
       return controladorDomini.ferSimulacio(probs,atacant,defensor);
    }
    public void movimentUsuari(int ii,int ij,int fi,int fj){

    }

    public String getRankingProb(String nomprob){
        return controladorDomini.getRankingProb(nomprob);
    }

    public  ArrayList<String> getNomProblemesUsuari() {
        String s = controladorDomini.getCurrentuser();

        return controladorDomini.getNomProblemesUsuari(s);
    }

    public void eliminarProblema(String nomp) {
        controladorDomini.eliminarProblema(nomp);
    }

    public boolean existeixFENambNmovs(String fen, int nmovs) {
        return controladorDomini.existeixFENambNmovs(fen,nmovs);
    }

}