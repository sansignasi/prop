package src.Domini;

import lib.Pair;
import src.Controladors.CtrlDomini;

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
    private CtrlDomini c = CtrlDomini.getInstance();
    //METODES

    /**
     * Creadora
     * @param p Problema
     * @param j1 Jugador j1
     * @param j2 Jugador j2
     * @throws IncorrectFENException
     */
    public Partida(Problema p, Jugador j1, Jugador j2) throws IncorrectFENException {
        this.j1 = j1;
        this.j2 = j2;
        this.prob = p;
        mov = p.getMoviments();
        atacant = p.getAtacant();
        this.T = p.getTaulell();
    }

    /**
     * Consultora que retorna la matriu de chars corresponent a l'atribut Taulell T
     * @return Retorna una matriu de chars
     */
    public char[][] matriuChars(){
        return T.matriuChars();
    }

    /**
     * Funcio de Jugar Partida
     * @return Retorna un booleà que indica si l'atacant ha pogut guanyar la partida o no
     * @throws IncorrectFENException
     */
    public boolean jugarPartida() throws IncorrectFENException {
        String s;
        Scanner sc = new Scanner(System.in);
        int x;
        int y;
        Piece p;
        Pair move = null;
        ArrayList<Pair> posmovs;
        boolean jaquemate = false;
        for (int i = 0; i < (mov * 2) - 1 && !jaquemate; ++i) {
            Boolean posok = false;
            while (!posok) {
                Boolean peçaselec = false;
                while (!peçaselec) {
                    if (i % 2 == 0) {
                        move = j1.jugarTorn(T, j1.getColor(), prob.getMoviments() * 2);
                        if (move != null) peçaselec = true;
                    } else {
                        move = j2.jugarTorn(T, j2.getColor(), prob.getMoviments() * 2);
                        if (move != null) peçaselec = true;
                    }
                }
                Pair pos = (Pair) move.getSecond();
                p = (Piece) move.getFirst();
                Pair posp = p.getPos();
                posmovs = p.calculaMovimentsPiece(T.getTaulell(), (int) p.getPos().getFirst(), (int) p.getPos().getSecond());
                for (int j = 0; j < posmovs.size() && !posok; ++j) {
                    Pair posaux = (Pair) posmovs.get(j).getSecond();
                    if (posaux.getFirst() == pos.getFirst() && posaux.getSecond() == pos.getSecond()) {
                        T.actualitzarTaulell(p, (Pair) move.getSecond());
                        c.actualizarMchar(matriuChars());
                        posok = true;
                    }
                }
            }
            if (T.jaque(j2.getColor())) {
                if (T.jaquemate(j2.getColor())) {
                    jaquemate = true;
                }
            }
            if (T.jaque(j1.getColor())) {
                if (T.jaquemate(j1.getColor())) {
                    jaquemate = true;
                }
            }
        }
        if (!jaquemate) {
            prob.restoreTaulell();
            return false;
        }
        else {
            if (T.jaquemate(j2.getColor())){
                prob.restoreTaulell();
                return true;
            }
            else{
                prob.restoreTaulell();
                return false;
            }
        }


    }
}
