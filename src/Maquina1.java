package src;
import lib.Pair;

import java.util.ArrayList;

public class Maquina1 { //Minimax amb profunditat limitada
    public Pair getMovimentAlgorisme1(Taulell t, int jugador){


        //es crea un arbre amb profunditat p (veure bé a on la posem, igual puc fer un getProfunditat)
        //cada successor serà un possible moviment de l'arbre es a dir que suposant que les 8 peces estan vives es generen 8*possiblesmovimentsperpeça
        //s'evalua l'estat del taulell successor amb la funció heurística en funció d'atacant o defensor
        Pair p = MiniMax(t,jugador);
        return p;
    }

    public int Heuristic1(Taulell t, int jugador){
        int ret = 0;
        return ret;
    }

    public int Heuristic2(Taulell t, int jugador){
        int ret = 0;
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
            cmax = valorMin(t.mourePeça(p.get(i)),jugador);
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
                vmax = Math.max(vmax,valorMin(t.mourePeça(p.get(i)),jugador));
            }
            return vmax;
        }
    }

    public int valorMin(Taulell t, int jugador){
        int vmin;
        if (estatTerminal(t,jugador)){
            //retorna la puntuació de l'heurístic -> classe a part o funció interna
        }
        else{
            vmin = 9999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){
                vmin = Math.min(vmin,valorMax(t.mourePeça(p.get(i)),jugador));
            }
            return vmin;
        }
    }
}
