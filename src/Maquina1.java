package src;
import lib.Pair;

import java.util.ArrayList;


public class Maquina1 { //Minimax amb profunditat limitada

    private int prof;

    public void initializeProf(){
        prof = 1;
    }

    public Pair getMovimentAlgorisme1(Taulell t, int jugador){


        //es crea un arbre amb profunditat p (veure bé a on la posem, igual puc fer un getProfunditat)
        //cada successor serà un possible moviment de l'arbre es a dir que suposant que les 8 peces estan vives es generen 8*possiblesmovimentsperpeça
        //s'evalua l'estat del taulell successor amb la funció heurística en funció d'atacant o defensor

        initializeProf();
        Pair p = MiniMax(t,jugador);
        return p;
    }

    public int Heuristic1(Taulell t, int jugador){

        Piece[][] p = t.getTaulell();

        int ret = 0;

        for (int i=0; i< p.length; ++i) {
            for (int j = 0; j < p[0].length; ++j) {

                Piece p1 = t.getPiece(i,j);
                if (t.tePiece(i,j) && p1.getJugador()==jugador) { //jugador 1 enemic
                    Piece pC = t.getPiece(i,j);
                    ret += pC.getValor();
                }
                else if (t.tePiece(i,j) && p1.getJugador()==jugador) {

                    Piece pC = t.getPiece(i,j);
                    ret += pC.getValor();
                }
            }
        }
        return ret;
    }

    public int Heuristic2(Taulell t, int jugador){

        Piece [][] p = t.getTaulell();

        int ret = 0;

        for (int i=0; i< p.length; ++i) {
            for (int j = 0; j < p[0].length; ++j) {

                if (t.tePiece(i,j) && jugador == 0) { //jugador 0 enemic
                    Piece pC = t.getPiece(i,j);
                    ret -= pC.getValor();
                }
                else if (t.tePiece(i,j) && jugador == 1) {

                    Piece pC = t.getPiece(i,j);
                    ret += pC.getValor();
                }
            }
        }
        return ret;
    }

    public ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){

        ArrayList<Pair> a = new ArrayList<>();
        Piece[][] m = t.getTaulell();

        jugador = 1;

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

    public boolean estatTerminal(Taulell t, int jugador){
        //if (! t.teRei(jugador)) return true;
        //else if (prof <= 0) return true;
        //else return false;
        return true;
    }

    public Pair MiniMax(Taulell t, int jg){

        int max,cmax; //puntuacio de la heurística
        max = -99999999;
        Pair movret = new Pair(0,0);
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
        int aux1 = prof;

        for (int i=0; i<p.size();++i) {
            Taulell aux = t;
            Pair mov = p.get(i);
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(t,jg);
            //System.out.println("lheuristic es "+ cmax + "  el moviment es " + mov);
            if (cmax > max){
                max = cmax;
                movret = mov;
                //System.out.println("l'heuristic es " + max + " el moviment es " + movret);
            }
            prof = aux1;
        }
        return movret;

        /*jg = 1;
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg);
        for (int i = 0; i < p.size(); ++i) System.out.println(p.get(i).getFirst() + " " + p.get(i).getSecond());*/
    }

    public int valorMax(Taulell t, int jg){
        int vmax;
        if (estatTerminal(t,jg)){
            int x = Heuristic1(t,jg);
            //System.out.println("El valor del heuristic es " + x );
            return x;
        }
        else{
            vmax = -99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){
                Taulell aux = t;
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int k = valorMin(aux,jg);
                //System.out.println("el valor de vmax es " + vmax + " el valor de k es " + k);
                vmax = Math.max(vmax,k);
                //System.out.println("ens enduem " + vmax);
            }
            --prof;
            //System.out.println("valorMax retorna " + vmax);
            return vmax;
        }
    }

    public int valorMin(Taulell t, int jg){
        int vmin;
        if (estatTerminal(t,jg)){
            int x = Heuristic1(t,jg);
            //System.out.println("El valor del heuristic es " + x );
            return x;
        }
        else{
            vmin = 99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg);
            for (int i=0; i<p.size(); ++i){
                Taulell aux = t;
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int k = valorMax(aux,jg);
                vmin = Math.min(vmin,k);
            }
            --prof;
            //System.out.println("valorMin retorna " + vmin);
            return vmin;
        }
    }
}
