package src.Controladors;
import com.google.gson.Gson;
import lib.Pair;
import src.Domini.*;


import java.util.*;

public class CtrlDomini {//hola


    private Gson gson = new Gson();

    private CtrlPersistencia db = CtrlPersistencia.getInstance();

    ArrayList<Problema> ProblemesPrecarregats = new ArrayList<Problema>();

    private BaseDeProblemes bproblemes;
    private BaseUsuaris busers;
    private String currentuser;
    private CtrlPresentacion ctrlPresentacion;

    private static CtrlDomini singletonObject;


    public static CtrlDomini getInstance() throws IncorrectFENException {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    private CtrlDomini() throws IncorrectFENException {
        ctrlPresentacion = CtrlPresentacion.getInstance();
}

    public void setBProblemes(BaseDeProblemes b){
        bproblemes = b;
    }

    public void setBUsers(BaseUsuaris b){
        busers = b;
    }

    public BaseDeProblemes getBProblemes(){
        return bproblemes;
    }

    public BaseUsuaris getBUsuaris(){
        return busers;
    }

    public void CarregaBP() throws Exception{

        Vector<String> sbproblemes = db.loadBProblems("BProblems.txt");

        int i = 0;
        while(i < sbproblemes.size()){
                Problema p = gson.fromJson(sbproblemes.get(i), Problema.class);
                p.restoreTaulell();
                //Ranking r = gson.fromJson(sbproblemes.get(i+1), Ranking.class);
                //p.setRanking(r);
                bproblemes.afegirProblema(p);
                ++i;
        }
    }

    public void CarregaBU() throws Exception{

        Vector<String> sbusers = db.loadBUsers("BUsers.txt");
        for (String s : sbusers) {
            if (!s.equals("null")) {
                Usuari u = gson.fromJson(s,Usuari.class);
                busers.afegirusuari(u);

            }
        }
    }

    public void GuardaBroblemes () throws Exception{

        TreeMap<String,Problema> t = bproblemes.getMap();

        String[] s = new String[t.size()];

        int i = 0;

        for(Map.Entry<String,Problema> entry : t.entrySet()) {
            Problema p  = entry.getValue();
            //Ranking r = p.getRanking();
            s[i] = gson.toJson(entry.getValue());
            //s[i+1] = gson.toJson(r);
            ++i;
        }
        db.EscriureProblemes(s);

    }

    public void GuardaBUsers () throws Exception {

        TreeMap<String, Usuari> t = busers.getMap();

        String[] s = new String[t.size()];

        int i = 0;

        for (Map.Entry<String, Usuari> entry : t.entrySet()) {
            s[i] = gson.toJson(entry.getValue());
            ++i;
        }
        db.EscriureUsuaris(s);
    }

    public void reload() {
        bproblemes = BaseDeProblemes.getInstance();
        busers = BaseUsuaris.getInstance();
    }

    public int verificarusuari(String user, String psw){ //0 OK 1 contra incorrecta 2 no existe user
        int i = busers.verificarusuari(user,psw);
        return i;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public void crearusuari(String user, String psw) throws Exception {
        Usuari u = new Usuari(user,psw);
        currentuser = user;
        busers.afegirusuari(u);
        GuardaBUsers();
    }

    public Set<String> getNomProblemes() throws Exception {
        return bproblemes.getNomProblemes();
    }

    public  ArrayList<String> getNomProblemesUsuari(String s) {
        return bproblemes.getNomProblemesUsuari(s);
    }

    public int getMovimentsProblema(String s){
        return bproblemes.buscarProblema(s).getMoviments();
    }

    public String getCreadorProblema(String s){
        return bproblemes.buscarProblema(s).getCreador();
    }

    public String getDificultadProblema(String s){
        return bproblemes.buscarProblema(s).getDificultad();
    }

    public ArrayList<Boolean> ferSimulacio(ArrayList<String> probs, String atacant, String defensor) throws IncorrectFENException {
        Maquina m1;
        Maquina m2;
        ArrayList<Problema> p = new ArrayList<>();
        if(atacant.equals("Maquina1"))m1 = new Maquina1();
        else m1 = new Maquina2();
        if(defensor.equals("Maquina1"))m2 = new Maquina1();
        else m2 = new Maquina2();
        for(int i = 0; i < probs.size();++i){
            p.add(bproblemes.buscarProblema(probs.get(i)));
        }
        Simulacio s = new Simulacio(m1,m2,p);
        ArrayList<Boolean> b = s.simular();
        return b;
    }

    public String getRankingProb(String nomprob) {
        Problema p = bproblemes.buscarProblema(nomprob);
        Ranking r = p.getRanking();
        return r.getStringRanking();
    }

    public void afegirProblema(String FEN, int n, String nomprob) throws Exception {
        Problema p = new Problema(FEN,n,nomprob);
        p.setCreador(currentuser);
        bproblemes.afegirProblema(p);
        GuardaBroblemes();
    }


    //FUNCIONES IGNASI EL MAS TONTO

    public char[][] matriuProblema(String nomprob) {
        return bproblemes.buscarProblema(nomprob).matriuChars();
    }

    public void actualizarMchar(char[][] mchar) throws IncorrectFENException {
        ctrlPresentacion.actualitzaBoard(mchar);
    }

    public void eliminarProblema(String nomp) throws Exception {
        bproblemes.eliminarProblema(bproblemes.buscarProblema(nomp));
        GuardaBroblemes();
    }

    public boolean existeixFENambNmovs(String fen, int nmovs) {
        return bproblemes.existeixFENambNmovs(fen,nmovs);
    }

    public Boolean validarProblema(String fen, int movs) throws IncorrectFENException {
        Problema p = new Problema(fen,movs,"temp");
        int aux = p.validarProblema(p.getTaulell(),p.getAtacant(),p.getMoviments()*2+1);
        if(aux == movs) return true;
        else return false;
    }

    public void precarregarProblemes() throws IncorrectFENException {
        String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
        Problema prob1 = new Problema(fen1,2,"prob1");
        ProblemesPrecarregats.add(prob1);
        String fen2 = "5r2/1pR3Bn/1KP1k3/2P1p3/1p3pP1/5Q2/3Pp2p/1B1b4 w - - 0 1";
        Problema prob2 = new Problema(fen2,2,"Problema 2, mat de blanques en 2");
        ProblemesPrecarregats.add(prob2);
        String fen3 = "B6K/B1N5/2np3p/2r2n1Q/1p2k3/1P2PNP1/4P3/8 w - - 0 1";
        Problema prob3 = new Problema(fen3,2,"Problema 3, mat de blanques en 2");
        ProblemesPrecarregats.add(prob3);
    }

    public boolean movimentValid(char[][] mchar, int[] posIni, int[] posFi,String nomprob) throws IncorrectFENException {
        String fenincomplete = ctrlPresentacion.mcharAFEN(mchar); //fen incompleto solo con mchar
        String atk;
        if(bproblemes.buscarProblema(nomprob).getAtacant() == 0) atk = "w";
        else atk = "b";
        String fen = fenincomplete+" "+atk+" - - 0 1"; //fen bueno
        Taulell T = new Taulell(fen);
        Piece p = T.getPiece(posIni[0],posIni[1]); //pieza a mover
        ArrayList<Pair> pairs = p.calculaMovimentsPiece(T.getTaulell(), posIni[0], posIni[1]); //movs posibles de tu pieza a mover
        boolean found = false;
        for(int i = 0; i < pairs.size() && !found; ++i){
            Pair posaux = (Pair) pairs.get(i).getSecond();
            if ((int)posaux.getFirst() == posFi[0] && (int)posaux.getSecond() == posFi[1])found = true; //si la pos a la q quieres mover esta en los movs posibles return true
        }
        return found;
    }

    public int[] movimentM1(char[][] mchar, int nmovs, String nomprob) throws IncorrectFENException {
        String fenincomplete = ctrlPresentacion.mcharAFEN(mchar); //fen incompleto solo con mchar
        String atk;
        if(bproblemes.buscarProblema(nomprob).getAtacant() == 0) atk = "w";
        else atk = "b";
        int jug=0;
        if(atk.equals("w"))jug = 1;
        else jug = 0;
        String fen = fenincomplete+" "+atk+" - - 0 1"; //fen bueno
        Taulell T = new Taulell(fen);
        Maquina1 m1 = new Maquina1();
        Pair p = null;
        p = m1.jugarTorn(T,jug,nmovs*2);
        int[] res = new int[4];
        Piece PieceAux = (Piece)p.getFirst();
        Pair PosAux = (Pair) p.getSecond();
        res[0]=(int)PieceAux.getPos().getFirst();
        res[1]=(int)PieceAux.getPos().getSecond();
        res[2]=(int)PosAux.getFirst();
        res[3]=(int)PosAux.getSecond();
        return res;
    }

    public int[] movimentM2(char[][] mchar, int nmovs, String nomprob) throws IncorrectFENException {
        String fenincomplete = ctrlPresentacion.mcharAFEN(mchar); //fen incompleto solo con mchar
        String atk;
        if(bproblemes.buscarProblema(nomprob).getAtacant() == 0) atk = "w";
        else atk = "b";
        int jug=0;
        if(atk.equals("w"))jug = 1;
        else jug = 0;
        String fen = fenincomplete+" "+atk+" - - 0 1"; //fen bueno
        Taulell T = new Taulell(fen);
        Maquina2 m2 = new Maquina2();
        Pair p = null;
        p = m2.jugarTorn(T,jug,nmovs*2);
        int[] res = new int[4];
        Piece PieceAux = (Piece)p.getFirst();
        Pair PosAux = (Pair) p.getSecond();
        res[0]=(int)PieceAux.getPos().getFirst();
        res[1]=(int)PieceAux.getPos().getSecond();
        res[2]=(int)PosAux.getFirst();
        res[3]=(int)PosAux.getSecond();
        return res;
    }


    public boolean hayJaque(char[][] mchar, String nomprob, int jug) throws IncorrectFENException {
        String fenincomplete = ctrlPresentacion.mcharAFEN(mchar); //fen incompleto solo con mchar
        String atk;
        if(bproblemes.buscarProblema(nomprob).getAtacant() == 0) atk = "w";
        else atk = "b";
        String fen = fenincomplete+" "+atk+" - - 0 1"; //fen bueno
        Taulell T = new Taulell(fen);
        return T.jaque(jug);
    }

    public boolean hayJaqueMate(char[][] mchar, String nomprob, int i) throws IncorrectFENException {
        String fenincomplete = ctrlPresentacion.mcharAFEN(mchar); //fen incompleto solo con mchar
        String atk;
        if(bproblemes.buscarProblema(nomprob).getAtacant() == 0) atk = "w";
        else atk = "b";
        String fen = fenincomplete+" "+atk+" - - 0 1"; //fen bueno
        Taulell T = new Taulell(fen);
        return T.jaquemate(i);
    }

    public void afegirAlRanking(String nomprob, long taux) throws Exception {
        Problema p = bproblemes.buscarProblema(nomprob);
        p.putRanking(currentuser,taux);
        GuardaBroblemes();
    }

    public int getAtacantProblema(String nomprob) {
        return bproblemes.buscarProblema(nomprob).getAtacant();
    }
}


