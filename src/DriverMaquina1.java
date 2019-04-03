package src;

import lib.Pair;
import java.util.ArrayList;
import src.Maquina1;
import src.Piece;
import src.Taulell;

public class DriverMaquina1 {

    public static int jugador = 1;
    public static int jg =1 ;
    public static Maquina1 m = new Maquina1();


    public static Piece[][] matriu = { {new Rook('b'), null, new King('b'),null,null,new Bishop('b'),null,new Rook('b')}, //R,-,K,-,-,B,-,R
            {new Pawn('b'), new Pawn('b'), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('w'), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,new King('w'),null,null}};


    public static Taulell t = new Taulell(matriu);

    public  static void testgetMovimentAlgorism1() {
        Pair p = m.getMovimentAlgorisme1(t, jugador);
        System.out.println(p.getFirst() + " " + p.getSecond());
        //Pair p1 = (Pair)p.getSecond();
        //System.out.println(p1.getFirst() + " " + p1.getSecond());
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


        int k = m.Heuristic1(t,0);
        System.out.println(k);
        int k1 = m.Heuristic1(t,1);
        System.out.println(k1);
        testgetMovimentAlgorism1();

    }

}
