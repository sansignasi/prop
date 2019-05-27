package src.Domini;
import lib.Pair;

import java.util.ArrayList;

public class Problema {
    //ATRIBUTS
    private String nomprob; //nom del problema
    public String FEN; //codificacio en FEN del problema
    private String dificultad; //dificultad asociada al problema
    private int nMax; // num maximo de jugadas para el jaquemate
    private int atacant; // jugador que comença i ataca,
    private Boolean validat; //validat o no
    private String creador; //nombre del usuario creador del problema
    private Taulell T; //(representa el objeto taulell, matriz de piezas, que tendra el estado inicial del problema)
    private Ranking R; //Rànking de puntuaciones del problema
    //METODES


    public Problema(){
        T = new Taulell();
        R = new Ranking();
    }

    /** Creadora de Problema
     *
     * @param s Representa el FEN que generarà l'estat del taulell associat al problema
     * @param n Nombre màxim de moviments que podrà realitzar l'atacant
     * @param nom Nom del problema
     * @throws IncorrectFENException
     */
    public Problema(String s, int n, String nom) throws IncorrectFENException {
        FEN = s;
        nMax = n;
        T = new Taulell(s);
        R = new Ranking();
        nomprob = nom;
        calcularDificultat();
        calcularatacant();
    }

    /**
     * Getter de la dificultat del problema
     * @return Retorna la dificultat associada al problema
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Getter del creador del problema
     * @return Retorna el nom de l'usuari creador del problema
     */
    public String getCreador() {
        return creador;
    }

    /**
     * Setter del creador del problema
     * @param creador Assigna l'usuari amb nom creador com a creador del problema
     */
    public void setCreador(String creador) {
        this.creador = creador;
    }

    /**
     * Recarrega el taulell amb el FEN del problema
     * @throws IncorrectFENException
     */
    public void restoreTaulell() throws IncorrectFENException{
        T = new Taulell(FEN);
    }

    /**
     * Setter del ranking
     * @param r Assigna el Ranking r com a ranking del problema
     */
    public void setRanking(Ranking r){
        R = r;
    }

    /**
     * Introdueix un usuari amb el temps que ha trigat a resoldre el problema al ranking del problema
     * @param s Nom de l'usuari
     * @param i Temps que ha trigat a resoldre'l
     */
    public void putRanking(String s, double i){
        R.introRank(s,i);
    }

    /**
     * Calcula l'atribut atacant de la classe amb el FEN associat al problema
     */
    public void calcularatacant() {
        String[] fenArray = FEN.split(" ");
        if (fenArray[1].equals("w")) atacant = 0;
        else atacant = 1;
    }

    /**
     * Retorna el Taulell T transformat a una matriu de chars
     * @return
     */
    public char[][] matriuChars(){
        System.out.print(FEN);
        T.mostrarTaulell();
        return T.matriuChars();
    }

    public void visualitzaProblema(){
        System.out.println(nomprob);
        System.out.println();
        System.out.println("Dificultad: "+dificultad);
        System.out.println();
        System.out.println();
        T.mostrarTaulell();
    }

    /**
     * Calcula el valor que li assignarem a l'atribut dificultat de la classe a partir de les peces de cada jugador i el número Màxim de moviments
     */
    public void calcularDificultat() {

        Piece[][] p = T.getTaulell();

        int meves,seves;
        meves = seves = 0;

        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                if (T.tePiece(i,j)) {
                    if (T.getPiece(i, j).getJugador() == atacant) ++meves;
                    else if (T.getPiece(i, j).getJugador() != atacant) ++seves;
                }
            }
        }

        float dificultat = (meves-seves)/nMax;
        if (dificultat > 0.5) dificultad = "Molt facil";
        else if (dificultat > 0) dificultad = "Facil";
        else if (dificultat > -0.5) dificultad = "Dificil";
        else dificultad = "Molt Dificil";

    }

    /**
     * Getter del nom del problema
     * @return Retorna el nom del problema
     */
    public String getNomprob(){
        return nomprob;
    }

    /**
     * Getter del nombre de moviments
     * @return Retorna el nombre màxim de moviments que podrà fer l'atacant qunan resolgui el problema
     */
    public int getMoviments(){
        return nMax;
    }

    public String getFEN() {
        return FEN;
    }

    /**
     * Getter de l'atacant
     * @return Retorna 0 si l'atacant són les blanques i 1 si són les negres
     */
    public int getAtacant(){
        return atacant;
    }

    /**
     * Getter del Taulell
     * @return Retorna el taulell associat al problema
     */
    public Taulell getTaulell(){
        return T;
    }

    /**
     * Getter del ranking del problema
     * @return Retorna el Ranking associat al problema
     */
    public Ranking getRanking() {return R;}

    /**
     * Considerem que un problema es pot validar si es pot realitzar amb exactament els moviments proposats per l'usuari
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jug Indica quin és el jugador que ataca (0 blanques, 1 negres)
     * @param mov Nombre màxim de moviments que pot realitzar latacant
     * @return
     */
    public int validariOptimitzarProblema(Taulell t, int jug, int mov){
        int k = MiniMaxOptim(t, jug, mov);
        if (k == -1) return -1;
        else return (((mov - k) / 2)-1);
    }

    /**
     * Considerem que un problema es pot validar si es pot realitzar amb els moviments proposats per l'ususari, independentment de si es pot realitzar amb menys
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jug Indica quin és el jugador que ataca (0 blanques, 1 negres)
     * @param mov Nombre màxim de moviments que pot realitzar latacant
     * @return
     */
    public int validarProblema(Taulell t, int jug, int mov){
        int k = MiniMax(t, jug, mov);
        if (k == -1) return -1;
        else return ((mov - k-1) / 2);
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @return Retorna tots els moviments possibles que podrà realitzar el jugador al que supleix la màquina
     */
    public ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){

        ArrayList<Pair> a = new ArrayList<>();
        Piece[][] m = t.getTaulell();

        for (int i=0; i< m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j){
                if (t.tePiece(i,j) && m[i][j].getJugador() == jugador){
                    ArrayList<Pair> aux = m[i][j].calculaMovimentsPiece(m,i,j);
                    a.addAll(aux);
                }
            }
        }

        return a;
    }
    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Retorna un boolea que indica si s'ha arribat a un estat terminal o no
     */
    public boolean estatTerminal(Taulell t, int jugador, int prf){
        if (prf == 0) return true;
        if (!t.teRei((Math.abs(jugador-1)))) return true;
        else return false;
    }
    /**
     * Podem quan hi ha una unica branca que valida el problema amb els moviments proposats per l'usuari
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador que ataca (0 blanques, 1 negres)
     * @param
     * @return Retorna un enter que representa el valor de la profunditat a la que ha arribat el minimax per validar el problema (inicialment profunditat i anirà restant d'u en u recursivament)
     */
    private int MiniMax(Taulell t, int jg, int profunditat){

        int ret,cmax; //puntuacio de la heurística
        ret = -99999999;
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
        int i = 0;
        boolean b = false;

        while(i<p.size() && !b){
            Taulell aux = new Taulell();
            aux.copiaTaulell(t);
            Pair po = (Pair) p.get(i).getSecond();
            if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
            else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond())instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(aux,jg,profunditat-1);
            if (cmax == 0) return 0;
            if (cmax > ret){
                ret = cmax;
            }
            ++i;
        }
        return ret;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador que ataca (0 blanques, 1 negres)
     * @param
     * @return Retorna un enter que representa el valor de la profunditat a la que ha arribat el minimax per validar el problema (inicialment profunditat i anirà restant d'u en u recursivament)
     */
    public int MiniMaxOptim(Taulell t, int jg, int profunditat){

        int ret,cmax; //puntuacio de la heurística
        ret = -99999999;
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
        int i = 0;

        while(i<p.size()){
            Taulell aux = new Taulell();
            aux.copiaTaulell(t);
            Pair po = (Pair) p.get(i).getSecond();
            if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
            else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond())instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(aux,jg,profunditat-1);
            if (cmax > ret){
                ret = cmax;
            }
            ++i;
        }
        return ret;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg  Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la màxima puntuació d'entre totes les subbranques que ha visitat
     */
    public int valorMax(Taulell t, int jg, int prf){
        int vmax;

        if (estatTerminal(t,jg,prf)){
            if(!t.teRei(Math.abs(jg-1))) {
                return prf;
            }
            else return -1;
        }
        else{
            vmax = -99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMin(aux,jg, (prf)-1);
                vmax = Math.max(vmax,l);
            }
            return vmax;
        }
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg  Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la mínima puntuació d'entre totes les subbranques que ha visitat
     */
    public int valorMin(Taulell t, int jg, int prf){
        int vmin;

        if (estatTerminal(t,jg,prf)){
            if(!t.teRei(Math.abs(jg-1))){
                return prf;
            }
            else return -1;
        }
        else{
            vmin = 99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, (prf)-1);
                vmin = Math.min(vmin,l);

            }
            return vmin;
        }
    }
}
