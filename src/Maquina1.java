package src;
import lib.Pair;

public class Maquina1 { //Minimax amb profunditat limitada
    public Pair getMovimentAlgorisme1(Taulell t, int jugador){


        //es crea un arbre amb profunditat p (veure bé a on la posem, igual puc fer un getProfunditat)
        //cada successor serà un possible moviment de l'arbre es a dir que suposant que les 8 peces estan vives es generen 8*possiblesmovimentsperpeça
        //s'evalua l'estat del taulell successor amb la funció heurística en funció d'atacant o defensor


    }

    public int calculaMovimentsPosibles(Taulell t, int jugador){}

    public boolean estat_terminal(Taulell t){}

    public Pair MiniMax(Taulell t, int jugador){
        int max,cmax; //puntuacio de la heurística
        max = 9999;
        Pair movret;
        int n = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments

        for (int i=0; i<n;++i) {

            cmax = valorMin(//aplicar moviment mov al taulell);
            if (cmax > max){
                max = cmax;
                movret = mov;
            }
        }
        return movret;
    }

    public int valorMax(Taulell t){
        int vmax;
        if (estat_terminal(t)){
            //retorna la puntuació de l'heurístic -> classe a part o funció interna
        }
        else{
            vmax = -9999;
            int n = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<n; ++i){
                vmax = max(vmax,valorMin(aplicar(movimenti,t)));
            }
            return vmax;
        }
    }

    public int valorMin(Taulell t){
        int vmin;
        if (estat_terminal(t)){
            //retorna la puntuació de l'heurístic -> classe a part o funció interna
        }
        else{
            vmax = 9999;
            int n = calculaMovimentsPosibles(t,jugador); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<n; ++i){
                vmin = min(vmin,valorMin(aplicar(movimenti,t)));
            }
            return vmin;
        }
    }
}
