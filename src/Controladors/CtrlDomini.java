package src.Controladors;
import src.Domini.*;


import java.util.*;

public class CtrlDomini {

    private CtrlPersistencia db = CtrlPersistencia.getInstance();

    ArrayList<Problema> ProblemesPrecarregats = new ArrayList<Problema>();

    public BaseDeProblemes bproblemes;
    public BaseUsuaris busers;
    public String currentuser;

    private static CtrlDomini singletonObject;


    public static CtrlDomini getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    private CtrlDomini(){}

    public void CarregaBProblemes() throws Exception{

        Vector<String> sbproblemes = db.loadBProblems("dades/BProblemes.txt");
    }

    public void CarregaBUsers() throws Exception{

        Vector<String> sbusers = db.loadBUsers("dades/BUsers.txt");
    }

    public void GuardaBroblemes () throws Exception{

    }

    public void GuardaBUsers () throws Exception{

    }

    public void reload() {
        bproblemes = BaseDeProblemes.getInstance();
        busers = BaseUsuaris.getInstance();
    }

    public int verificarusuari(String user, String psw){ //0 OK 1 contra incorrecta 2 no existe user
        Usuari u = new Usuari("aa","aa");
        busers.afegirusuari(u);
        int i = busers.verificarusuari(user,psw);
        return i;
    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public void crearusuari(String user, String psw){
        Usuari u = new Usuari(user,psw);
        busers.afegirusuari(u);
        //GuardaBUsers();
    }

    public char[][] matriuProblema(String nomprob) throws IncorrectFENException{
        String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
        Problema prob1 = new Problema(fen1,2,"prob1");
        bproblemes.afegirProblema(prob1);
        return bproblemes.buscarProblema(nomprob).matriuChars();
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
                    boolean b = pv1.validarProblema(pv1.getTaulell(),pv1.getAtacant(),pv1.getMoviments()*2+1);
                    if (!b) System.out.println("El problema no es pot resoldre en " + pv1.getMoviments() + " moviments.");
                    else System.out.println("El problema es pot resoldre en " + (pv1.getMoviments()) + " moviments.");
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

}








