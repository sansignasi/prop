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
    private VistaImportarFEN vistaImportarFEN;
    private VistaCrear vistaCrear;
    private VistaFormulariCrearProb vistaFormulariCrearProb;
    private VistaModificar vistaModificar;
    private VistaFormulariModificarProb vistaFormulariModificarProb;
    private VistaValidarModificacion vistaValidarModificacion;


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
        vistaImportarFEN = new VistaImportarFEN(this);
        vistaFormulariCrearProb = new VistaFormulariCrearProb(this);
        vistaFormulariModificarProb = new VistaFormulariModificarProb(this);
        vistaValidarModificacion = new VistaValidarModificacion(this);
    }

    public void inicializarPresentacion() throws Exception {
        asignar();
        controladorDomini.CarregaBP();
        controladorDomini.CarregaBU();
        vistaLogin.hacerVisible(); //descomentar para testear presentacion
        //VistaCrear vistaCrear = new VistaCrear(this);
        //vistaCrear.hacerVisible();
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


    public void cambiarVistaAJugarPartida(String tipusjug,String nomprob) throws IncorrectFENException {
        board = new Board(this,nomprob,tipusjug);
        board.hacerVisible();
    }
    public void cambiarVistaAPreview(String nomprob) throws IncorrectFENException {
        boardPreview = new BoardPreview(this,nomprob);
        boardPreview.hacerVisible();
    }
    public void cambiarVistaACrear(){
        vistaCrear = new VistaCrear(this);
        vistaCrear.hacerVisible();
    }

    public void cambiarVistaAModificar(String nomp){
        vistaModificar = new VistaModificar(this, nomp);
        vistaModificar.hacerVisible();
    }

    public void cambiarVistaAForumulariCrearProb(String fen){
        vistaFormulariCrearProb.hacerVisible(fen);
    }

    public void cambiarVistaAFormulariModificarProb(String fen, String nomp){
        vistaFormulariModificarProb.hacerVisible(fen,nomp);
    }
    public void cambiarVistaAValidarModificacion(String fen, int nmovs, String nomp, String nomprob) throws IncorrectFENException {
        vistaValidarModificacion.hacerVisible(fen,nmovs,nomp,nomprob);
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
    public void cambiarVistaAImportarFEN(String FEN,int movs,String nomprob) throws IncorrectFENException {
        vistaImportarFEN.hacerVisible(FEN,movs,nomprob);
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

    public void eliminarProblema(String nomp) throws Exception {
        controladorDomini.eliminarProblema(nomp);
    }

    public boolean existeixFENambNmovs(String fen, int nmovs) {
        return controladorDomini.existeixFENambNmovs(fen,nmovs);
    }

    public Boolean validarProblema(String fen, int movs) throws IncorrectFENException {
        return controladorDomini.validarProblema(fen,movs);
    }

    public void afegirProblemaBP(String FEN,int n,String nomprob) throws Exception {
        controladorDomini.afegirProblema(FEN,n,nomprob);
    }

    public String mcharAFEN(char[][] mchar) {
        String fen = "";
        // Build the board description:
        for(int i=0; i<8; i++) {
            int emptyCounter = 0;
            for(int j=0;j<8;j++) {
                if(mchar[i][j]!='-') {
                    if(emptyCounter!=0) {
                        fen += emptyCounter;
                        emptyCounter = 0;
                    }
                    fen += mchar[i][j];

                } else {
                    emptyCounter++;
                }
            }
            if(emptyCounter!=0) {
                fen += emptyCounter;
            }
            if(i!=7) {
                fen += "/";
            }
        }
        return fen;
    }

    public boolean movimentValid(char[][] mchar, int[] posIni, int[] posFi,String nomprob) throws IncorrectFENException {
        return controladorDomini.movimentValid(mchar,posIni,posFi,nomprob);
    }

    public int[] movimentM1(char[][] mchar, int nmovs, String nomprob) throws IncorrectFENException {
        return (controladorDomini.movimentM1(mchar,nmovs,nomprob));
    }

    public int[] movimentM2(char[][] mchar, int nmovs, String nomprob) throws IncorrectFENException {
        return (controladorDomini.movimentM2(mchar,nmovs,nomprob));
    }

    public boolean hayJaque(char[][] mchar, String nomprob, int jug) throws IncorrectFENException {
        return controladorDomini.hayJaque(mchar,nomprob,jug);
    }

    public boolean hayJaqueMate(char[][] mchar, String nomprob, int i) throws IncorrectFENException {
        return controladorDomini.hayJaqueMate(mchar,nomprob,i);
    }

    public void afegirAlRanking(String nomprob, long taux) throws Exception {
        controladorDomini.afegirAlRanking(nomprob,taux);
    }

    public int getAtacantProblema(String nomprob) {
        return controladorDomini.getAtacantProblema(nomprob);
    }
}