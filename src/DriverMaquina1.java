package src;

import lib.Pair;
import java.util.ArrayList;
import src.Maquina1;
import src.Piece;
import src.Taulell;

public class DriverMaquina1 {

    public static int jugador = 1;
    public static int jg = 1 ;
    public static Maquina1 m = new Maquina1();


    /*public static Piece[][] matriu = { {new Rook('b',0,0), null, new King('b',0,2),null,null,new Bishop('b',0,5),null,new Rook('b',0,7)}, //R,-,K,-,-,B,-,R
            {new Pawn('b',1,0), new Pawn('b',1,1), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('w',2,0), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,new King('b',7,5),null,null}};*/

    public static Piece[][] matriu = { {null, null, null,null,null,null,null,null}, //R,-,K,-,-,B,-,R
            {new Pawn('b',1,0), new Pawn('b',1,1), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('w',2,0), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null}};


    public static Taulell t = new Taulell(matriu);

    public  static void testgetMovimentAlgorism1() {
        //t.mostrarTaulell();
        Pair p = m.getMovimentAlgorisme1(t, jugador);
        System.out.println("El moviment que retorna la maquina 1 es " + p.getFirst() + " " + p.getSecond());
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
        testgetMovimentAlgorism1();

    }

}
