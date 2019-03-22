package src;
import lib.Pair;

import java.util.ArrayList;

public class Maquina1 { //Minimax amb profunditat limitada

    private int prof;

    public void initializeProf(){

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

        ArrayList<Peça> p = t.getTaulell();

        int ret = 0;

        for (int i=0; i<p.size();++i){
            if (p.get(i).tePeça() and jugador == 1){ //jugador 1 enemic

                Peça pC = p.get(i);

                if (pC.tipusPeça == 'K') ret -= 900;
                else if (pC.tipusPeça == 'Q') ret -= 90;
                else if (pC.tipusPeça == 'T') ret -= 50;
                else if (pC.tipusPeça == 'B') ret -= 30;
                else if (pC.tipusPeça == 'P') ret -= 10;
                else if (pC.tipusPeça == 'N') ret -= 30;
            }
            else if (p.get(i).tePeça() and jugador == 0){ //jugador 1 enemic

                Peça pC = p.get(i);

                if (pC.tipusPeça == 'K') ret += 900;
                if (pC.tipusPeça == 'Q') ret += 90;
                if (pC.tipusPeça == 'T') ret += 50;
                if (pC.tipusPeça == 'B') ret += 30;
                if (pC.tipusPeça == 'P') ret += 10;
                if (pC.tipusPeça == 'N') ret += 30;
            }
        }
        return ret;
    }

    public int Heuristic2(Taulell t, int jugador){
        int ret = 0;
        return ret;

        ArrayList<Peça> p = t.getTaulell();

        int ret = 0;

        for (int i=0; i<p.size();++i){
            if (p.get(i).tePeça() and jugador == 0){ //jugador 1 enemic

                Peça pC = p.get(i);

                if (pC.tipusPeça == 'K') ret -= 900;
                else if (pC.tipusPeça == 'Q') ret -= 90;
                else if (pC.tipusPeça == 'T') ret -= 50;
                else if (pC.tipusPeça == 'B') ret -= 30;
                else if (pC.tipusPeça == 'P') ret -= 10;
                else if (pC.tipusPeça == 'N') ret -= 30;
            }
            else if (p.get(i).tePeça() and jugador == 1){ //jugador 1 enemic

                Peça pC = p.get(i);

                if (pC.tipusPeça == 'K') ret += 900;
                if (pC.tipusPeça == 'Q') ret += 90;
                if (pC.tipusPeça == 'T') ret += 50;
                if (pC.tipusPeça == 'B') ret += 30;
                if (pC.tipusPeça == 'P') ret += 10;
                if (pC.tipusPeça == 'N') ret += 30;
            }
        }
        return ret;
    }

    public ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){
        ArrayList ret = new ArrayList();
        return ret;
    }

    public boolean estatTerminal(Taulell t, int jugador){
        if (! t.teRei(jugador)) return true;
        else if (t.getMoviments() == 0) return true;
        else if (prof == 0) return true;
        else return false;
    }

    public Pair MiniMax(Taulell t, int jugador){
        int max,cmax; //puntuacio de la heurística
        max = -9999;
        Pair movret = new Pair(0,0);
        
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments

        for (int i=0; i<p.size();++i) {
            Pair mov = p.get(i);
            t.actualitzarTaulell((Peça)p.get(i).getFirst(),(int)p.get(i).getSecond());
            --prof;
            cmax = valorMin(t,jugador);
            if (cmax > max){
                max = cmax;
                movret = mov;
            }
        }
        return movret;
    }

    public int valorMax(Taulell t, int jugador){
        int vmax;
        if (estatTerminal(t,jugador)){
            if (jugador == 0) return Heuristic1(t, jugador);
            else return Heuristic2(t,jugador);
        }
        else{
            vmax = -9999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){
                t.actualitzarTaulell((Peça)p.get(i).getFirst(),(int)p.get(i).getSecond());
                --prof;
                vmax = Math.max(vmax,valorMin(t,jugador));
            }
            return vmax;
        }
    }

    public int valorMin(Taulell t, int jugador){
        int vmin;
        if (estatTerminal(t,jugador)){
            if (jugador == 0) return Heuristic1(t, jugador);
            else return Heuristic2(t,jugador);
        }
        else{
            vmin = 9999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){
                t.actualitzarTaulell((Peça)p.get(i).getFirst(),(int)p.get(i).getSecond());
                --prof;
                vmin = Math.min(vmin,valorMax(t,jugador));
            }
            return vmin;
        }
    }
}