package src;
import lib.Pair;

import java.util.ArrayList;

public class Problema {
    //ATRIBUTS
    private String nomprob; //nom del problema
    private String FEN; //codificacio en FEN del problema
    private String dificultad; //dificultad asociada al problema
    private int nMax; // num maximo de jugadas para el jaquemate
    private int atacant; // jugador que comença i ataca, b black, w white
    private Boolean validat; //validat o no
    private String creador; //nombre del usuario creador del problema
   private Taulell T; //(representa el objeto taulell, matriz de piezas, que tendra el estado inicial del problema)
    //METODES

    public Problema(){
    }
    public Problema(String s, int n, String nom) throws IncorrectFENException {
        FEN = s;
        nMax = n;
        T = new Taulell(s);
        nomprob = nom;
        calcularDificultad();
    }

    public void calcularatacant() {
        String[] fenArray = FEN.split(" ");
        if (fenArray[1].equals("w")) atacant = 0;
        else atacant = 1;
    }

    public void visualitzaProblema(){
        System.out.println(nomprob);
        System.out.println();
        System.out.println("Dificultad: "+dificultad);
        System.out.println();
        System.out.println();
        T.mostrarTaulell();
    }

    public void calcularDificultad() {
        //PRE:
        //POST: calcula la dificultad asociada al problema
        this.dificultad = "Mas dificil que el final de BD";
    }

    public int getMoviments(){
        return nMax;
    }
    public int getAtacant(){
        return atacant;
    }
    public Taulell getTaulell(){
        return T;
    }

    /*// Jugador que comença a moure (atacant)
        if (fenArray[1].equals("b")) {
        Problema.setAtacant('b');
    } else if (fenArray[1].equals("w")) {
        Problema.setAtacant('w');
    } else {
        throw new IncorrectFENException("Color del jugador atacant incorrecte.");
    }*/

    //borrarProblema()
        //PRE: el problema identificado como nomprob existe
        //POST: elimina el problema nomprob de la base de problemas y su ranking asociado
    //modificarProblema()
        //PRE: el problema identificado como nomprob existe
        //POST: modifica la posicion de las piezas, el numero de piezas y el tema
    //validarProblema()
        //PRE: el problema esta siendo creado o modificado
        //POST: verifica que el problema con un determinado tablero, un numero de movimientos N y quien hace el jaque
        // mate tiene solución
    //cargarProblema()
        //PRE: el problema nomprob tiene una partida guardada
        //POST: carga la partida guardada previamente del problema nomprob


    public int validarProblema(Taulell t, int jug, int mov){
        int k = MiniMax(t,jug,mov);
        return 0;
    }


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

    public boolean estatTerminal(Taulell t, int jugador, int prf){
        if (!t.teRei(Math.abs(jugador-1)) && prf >= 0) return true;
        else return false;
    }

    public int MiniMax(Taulell t, int jg, int profunditat){

        int max,cmax; //puntuacio de la heurística
        max = -99999999;
        int ret = -1;
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments

        for (int i=0; i<p.size();++i) {
            //System.out.println("estem dins de minimax i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
            Taulell aux = new Taulell();
            aux.copiaTaulell(t);
            int prf = profunditat;
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(aux,jg,prf-1);

            if (cmax > max){
                ret = cmax;
            }
        }
        return ret;
    }

    public int valorMax(Taulell t, int jg, int prf){
        int vmax;

        if (estatTerminal(t,jg,prf)){
            return prf;
        }
        else{
            vmax = -99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments

            for (int i=0; i<p.size(); ++i){
                //System.out.println("estem dins de valormax i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMin(aux,jg, prf-1);
                vmax = Math.max(vmax,l);
            }
            return vmax;
        }
    }

    public int valorMin(Taulell t, int jg, int prf){
        int vmin;

        if (estatTerminal(t,jg,prf)){
            return prf;
            //System.out.println("El valor del heuristic es " + x );
        }
        else{
            vmin = 99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                //System.out.println("estem dins de valormin i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1);
                vmin = Math.min(vmin,l);

            }
            return vmin;
        }
    }
}
