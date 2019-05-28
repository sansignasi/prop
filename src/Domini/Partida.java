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
    private boolean j1u=false;
    private boolean j2u=false;
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
        if(j1 instanceof Usuari) j1u = true;
        if(j2 instanceof Usuari) j2u = true;
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
        long timej1 = 0;
        long taux = 0;
        int x;
        int y;
        Piece p;
        Pair move = null;
        ArrayList<Pair> posmovs;
        boolean jaquemate = false;
        for (int i = 0; i < (mov * 2) - 1 && !jaquemate; ++i) {
            Boolean posok = false;
            T.mostrarTaulell();
            while (!posok) {
                Boolean peçaselec = false;
                while (!peçaselec) {
                    if (i % 2 == 0) {
                        System.out.println("Torn de l'atacant");
                        taux = System.nanoTime();
                        move = j1.jugarTorn(T, j1.getColor(), prob.getMoviments() * 2);
                        if(j1u){
                            taux = System.nanoTime() - taux;
                            timej1+=taux;
                        }
                        if (move != null) peçaselec = true;
                        else System.out.println("En aquesta posició no hi ha peça, torna a intentar-ho");
                    } else {
                        System.out.println("Torn del defensor");
                        move = j2.jugarTorn(T, j2.getColor(), prob.getMoviments() * 2);
                        if (move != null) peçaselec = true;
                        else System.out.println("En aquesta posició no hi ha peça, torna a intentar-ho");
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
                        System.out.println("La peça de la posició " + letraColumna((int) posp.getSecond()) + Math.abs((int) posp.getFirst() - 8) + " es mou a la posició " + letraColumna((int) pos.getSecond()) + Math.abs((int) pos.getFirst() - 8));
                    }
                }
                if (!posok) System.out.println("Posició incorrecta, torna a intentar-ho");
            }
            if (T.jaque(j2.getColor())) {
                if (T.jaquemate(j2.getColor())) {
                    jaquemate = true;
                } else System.out.println("~~L'atacant fa escac!~~");
            }
            if (T.jaque(j1.getColor())) {
                if (T.jaquemate(j1.getColor())) {
                    jaquemate = true;
                } else System.out.println("~~El defensor fa escac!~~");
            }
        }
        T.mostrarTaulell();

        if (!jaquemate) {
            System.out.println("~~L'atacant no ha aconseguit fer escac i mat en els moviments establerts, guanya el defensor!~~");
            prob.restoreTaulell();
            return false;
        }
        else {
            if (T.jaquemate(j2.getColor())){
                System.out.println("~~L'atacant guanya amb Escac i mat!~~");
                this.prob.putRanking(j1.getNom(),timej1);
                prob.restoreTaulell();
                return true;
            }
            else{
                System.out.println("~~El defensor guanya amb Escac i mat!~~");
                prob.restoreTaulell();
                return false;
            }
        }


    }

    /**
     * Funció que retorna el char equivalent al paràmetre rebut com a input
     * @param i Enter amb el que volem calcular el char
     * @return Retorna el char equivalent a l'enter i
     */
    public char letraColumna(int i){
        if(i == 0)return 'a';
        else if (i == 1)return 'b';
        else if (i == 2)return 'c';
        else if (i == 3)return 'd';
        else if (i == 4)return 'e';
        else if (i == 5)return 'f';
        else if (i == 6)return 'g';
        else return 'h';
    }
}
