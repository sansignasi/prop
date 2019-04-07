package src;

import java.time.Duration;
import java.time.Instant;

public class Partida {
    //ATRIBUTS
    private Problema p;
    private int mov; //num de movimientos restantes que el jugador 1 tiene para hacer jaquemate
    private float temps; //tiempo que se ha jugado en la partida
    private String estat; //estado de la partida en el momento actual (en pausa, curs, fi).
    private Jugador j1; //jugador 1  de la partida (el que ataca)
    private Jugador j2; //jugador 2 de la partida (el que defiende)
    private int atacant;
    private Taulell T;
    //METODES

    public Partida(Problema p, Jugador j1, Jugador j2){
        this.j1 = j1;
        this.j2 = j2;
        this.p = p;
        mov = p.getMoviments();
        atacant = p.getAtacant();
        this.T = p.getTaulell();
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    /*public void jugar(){
        estat = "curs";
        Instant start = Instant.now();
        for(; mov > 0 || estat.equals("fi"); --mov) {
            //j1.jugatorn();
            if (estat.equals("fi")) break;
            //j2.jugatorn();
        }
        Instant finish = Instant.now();
        temps = Duration.between(start, finish).toSeconds();
        finalizar();
    }*/




    public void pausar() {
        //PRE: la partida no esta en pausa
        //POST: se pausa la partida. El estado pasa a ser en pausa, el tiempo pra de contar.
        if(estat.equals("curs")){
            estat = "pausa";
        }

    }

    public void reanudar() {
        //PRE: la partida esta en pausa
        //POST: se reanuda la partida
        if(estat.equals("pausa")){
            estat = "curs";
        }


    }
    public void abandonar() {
        //PRE:
        //POST: se abandona la partida

    }
    public void gurdar() {
        //PRE:
        //POST: se guarda el estado actual de la partida: como estan las piezas, a quien le toca mover, tiempo,y el
        //numero de movimientos restantes


    }
    public void finalizar() {
        //PRE:
        //POST: muestra los resultados finales de la partida y actualiza ranking si necesario.

    }
}
