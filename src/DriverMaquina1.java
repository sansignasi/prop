package src;

import lib.Pair;
import java.util.ArrayList;
import src.Maquina1;
import src.Piece;
import src.Taulell;

public class DriverMaquina1 {

    public static int jugador = 0;
    public static int jg =0 ;
    public static Maquina1 m = new Maquina1();


    public static Piece[][] matriu = { {new Rook('w'), null, new King('w'),null,null,new Bishop('w'),null,new Rook('w')}, //R,-,K,-,-,B,-,R
            {new Pawn('w'), new Pawn('w'), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('b'), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null}};


    public static Taulell t = new Taulell(matriu);

    public  static void testgetMovimentAlgorism1() {
        System.out.println("abans crida");
        Pair p = m.getMovimentAlgorisme1(t, jugador);
        System.out.println("despres crida");
        System.out.println((int)p.getFirst() + " " + (int)p.getSecond());
    }
    public static void testHeuristic1() {
        int h1 = m.Heuristic1( t, jugador);
    }
    public static void testHeuristic2() {

        int h2 = m.Heuristic2(t, jugador);
    }
    public static void testcalculaMovimentsPosibles() {

        ArrayList<Pair> v = m.calculaMovimentsPosibles(t,jugador);
    }
    public static void testestatTerminal() {

        boolean b = m.estatTerminal(t,jugador);
    }
    public static void testMiniMax() {

        Pair mm = m.MiniMax(t, jg);
    }
    public static void testvalorMax() {

        int vm = m.valorMax(t, jg);
    }
    public static void testvalorMin() {

        int vmin = m.valorMin(t,jg);
    }
    public static void main (String [] args){

        System.out.println((t.tePiece(0,0)));

        int k = m.Heuristic1(t,0);
        System.out.println(k);
        testgetMovimentAlgorism1();

    }

}
