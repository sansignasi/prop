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
                System.out.println(s);
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

        for (int j = 0; j < s.length; ++j){
            System.out.println(s[j]);
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
        System.out.print(aux);
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


    public void menuPrincipal() throws IncorrectFENException{

        precarregarProblemes();

        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Jugar problema");
            System.out.println("2.Validar problema");
            System.out.println("3.Simulación");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    System.out.println("Escull un dels següents problemes per jugar-lo:");
                    System.out.println();
                    //Problema 1
                    ProblemesPrecarregats.get(0).visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    //Problema 2
                    ProblemesPrecarregats.get(1).visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    //Problema 3
                    ProblemesPrecarregats.get(2).visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    System.out.println("Escull un dels problemes mostrats per jugar-lo:");
                    //Atacant
                    boolean ok = false;
                    int inprob = 0;
                    while(!ok) {
                        inprob = sc.nextInt();
                        if (inprob >= 1 && inprob <= 3) ok = true;
                        else System.out.println("Problema erroni, torna a intentar-ho");
                    }
                    System.out.println("Qui comença atacant? Opció 1: Usuari, Opció 2: M1");
                    ok = false;
                    int j2 = 0;
                    int j1 = 0;
                    //Defensor
                    while(!ok) {
                        j1 = sc.nextInt();
                        if (j1 == 1 || j1 == 2) ok = true;
                        else System.out.println("Jugador erroni, torna a intentar-ho");
                    }
                    ok = false;
                    System.out.println("Qui defensa? Opció 1: Usuari, Opció 2: M1");
                    while(!ok) {
                        j2 = sc.nextInt();
                        if (j2 == 1 || j2 == 2) ok = true;
                        else System.out.println("Jugador erroni, torna a intentar-ho");
                    }

                    //Crida a Partida
                    Problema prob = new Problema();
                    Jugador jug1 = new Jugador();
                    Jugador jug2 = new Jugador();
                    //Problema:
                    if(inprob==1){
                        prob = ProblemesPrecarregats.get(0);
                    }
                    else if(inprob==2){
                        prob = ProblemesPrecarregats.get(1);
                    }
                    else if(inprob==3){
                        prob = ProblemesPrecarregats.get(2);
                    }
                    else{
                        System.out.println("Problema erroni.");
                    }
                    //Jugador 1:
                    if(j1==1){
                        jug1 = new Usuari(prob.getAtacant(),0);
                    }
                    else if(j1==2){
                        jug1 = new Maquina1(prob.getAtacant(),0);
                    }
                    //Jugador 2:
                    int x = prob.getAtacant();
                    if(x == 1) x = 0;
                    else x = 1;
                    if(j2==1){
                        jug2 = new Usuari(x,1);
                    }
                    else if(j2==2){
                        jug2 = new Maquina1(x,1);
                    }
                    Partida game = new Partida(prob,jug1,jug2);
                    game.jugarPartida();
                    break;

                case 2:
                    //Introduir FEN
                    System.out.println("Escriu un FEN per validar-lo.");
                    System.out.println();
                    Scanner scF = new Scanner(System.in);
                    String fen = scF.nextLine();
                    System.out.println("Escriu el nombre de moviments amb els que vols provar de validar-lo.");
                    System.out.println();
                    scF = new Scanner(System.in);
                    int nmovs = scF.nextInt();
                    Problema pv1 = new Problema(fen,nmovs,"ProblemaValidar");
                    pv1.visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    //boolean b = pv1.validarProblema(pv1.getTaulell(),pv1.getAtacant(),pv1.getMoviments()*2+1);
                    // if (!b) System.out.println("El problema no es pot resoldre en " + pv1.getMoviments() + " moviments.");
                    //else System.out.println("El problema es pot resoldre en " + (pv1.getMoviments()) + " moviments.");
                    break;

                case 3:
                    Scanner s = new Scanner(System.in);
                    System.out.println("Selecciona atacante: 1.M1 2.M2");
                    int n = s.nextInt();
                    Jugador atacant;
                    if(n == 1) atacant = new Maquina1();
                    else atacant = new Maquina2();
                    System.out.println("Selecciona defensor: 1.M1 2.M2");
                    int m = s.nextInt();
                    Jugador defensor;
                    if(m == 1) defensor = new Maquina1();
                    else defensor = new Maquina2();
                    Simulacio sim = new Simulacio(atacant,defensor,ProblemesPrecarregats);
                    ArrayList<Boolean> res = sim.simular();
                    for(int i = 0; i < res.size();++i){
                        if(res.get(i))System.out.println("L'atacant ha guanyat el problema "+i);
                        else System.out.println("L'atacant ha perdut el problema "+i);
                    }
                    break;

                case 0:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Opció invàlida. Torna-ho a provar.");
                    break;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception{


        System.out.println("HOLA ESTIC DINS DEL MAIN DEL CTRL DOMINI");
        System.out.println("Creo un problema i l'afegeixo a BP");

        BaseDeProblemes bp = BaseDeProblemes.getInstance();

        String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
        Problema prob1 = new Problema(fen1,2,"Problema 1, mat de blanques en 2");
        double aux = 10;
        prob1.putRanking("pol",aux);

        bp.afegirProblema(prob1);
        Taulell t = prob1.getTaulell();
        t.mostrarTaulell();

        System.out.println("Creo un problema i l'afegeixo a BP");


        CtrlDomini c = CtrlDomini.getInstance();

        c.setBProblemes(bp);
        //c.GuardaBroblemes();
        //c.CarregaBP();

        bp = c.getBProblemes();
        prob1 = bp.buscarProblema(prob1.getNomprob());
        Ranking r = prob1.getRanking();
        t = prob1.getTaulell();
        t.mostrarTaulell();

        /*System.out.println("ara provem el puto busers");

        Usuari u = new Usuari("pol", "lalala");
        BaseUsuaris bu = BaseUsuaris.getInstance();
        bu.afegirusuari(u);

        System.out.println("lusuari te:" + u.getNom() + " " + u.getContraseña());

        c.setBUsers(bu);
        //c.GuardaBUsers();
        System.out.println("surto del guarda");
        //c.CarregaBU();
        System.out.println("surto del carrega");
        bu = c.getBUsuaris();
        u = bu.buscarUsuari("pol");
        System.out.println("l'usuari final te:" + u.getNom() + " " + u.getContraseña());*/


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
}


