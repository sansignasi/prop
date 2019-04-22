package src;

import lib.Pair;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Partida {
    //ATRIBUTS
    private Problema prob;
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
        this.prob = p;
        mov = p.getMoviments();
        atacant = p.getAtacant();
        this.T = p.getTaulell();
    }

    public void jugarPartida(){
        String s;
        Scanner sc = new Scanner(System.in);
        int x;
        int y;
        Piece p;
        Pair move;
        ArrayList<Pair> posmovs;
        boolean jaquemate = false;
        for(int i = 0; i < (mov*2)-1 && !jaquemate;++i){
            Boolean posok = false;
            T.mostrarTaulell();
            if(i%2 == 0){
                System.out.println("Torn de l'atacant");
                move = j1.jugarTorn(T,j1.getColor(),prob.getMoviments()*2);
            }
            else{
                System.out.println("Torn del defensor");
                move = j2.jugarTorn(T,j2.getColor(),prob.getMoviments()*2);
            }
            Pair pos = (Pair)move.getSecond();
            p = (Piece)move.getFirst();
            Pair posp = p.getPos();
            System.out.println(posp.getFirst()+" "+posp.getSecond());
            System.out.println(pos.getFirst()+" "+pos.getSecond());
            posmovs = p.calculaMovimentsPiece(T.getTaulell(),(int)p.getPos().getFirst(),(int)p.getPos().getSecond());
            for(int j = 0; j < posmovs.size();++j){
                Pair posaux = (Pair)posmovs.get(j).getSecond();
                if(posaux.getFirst() == pos.getFirst() && posaux.getSecond() == pos.getSecond()){
                    T.actualitzarTaulell(p,(Pair)move.getSecond());
                    posok = true;
                }
            }
            if(!posok)System.out.println("Posició incorrecta");
            if(T.jaquemate(j2.getColor())) System.out.println("L'atacant guanya amb Escac i mat!");
            else if (T.jaquemate(j1.getColor())) System.out.println("El defensor guanya amb Escac i mat!");

        }
        T.mostrarTaulell();


    }

}
