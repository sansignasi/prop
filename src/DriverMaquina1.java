import lib.Pair;
import java.util.ArrayList;
import src.Maquina1;
import src.Piece;
import src.Taulell;

import static src.Piece.PieceColor.Black;
import static src.Piece.PieceColor.White;
import static src.Piece.TipusPiece.*;

public class DriverMaquina1 {

    private int jugador = 0;
    private int jg =0 ;
    Maquina1 m;


    Piece[][] matriu = { {new Piece(White, Rook), null, new Piece(White, King),null,null,new Piece(White, Bishop),null,new Piece(White, Rook)}, //R,-,K,-,-,B,-,R
            {new Piece(White, Pawn), new Piece(White, Pawn), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Piece(Black, Pawn), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null}};


    public Taulell t = new Taulell();

    public  void testgetMovimentAlgorism1() {

        Pair p = m.getMovimentAlgorisme1(t, jugador);
    }
    public void testHeuristic1() {
        int h1 = m.Heuristic1( t, jugador);
    }
    public void testHeuristic2() {

        int h2 = m.Heuristic2(t, jugador);
    }
    public void testcalculaMovimentsPosibles() {

        ArrayList<Pair> v = m.calculaMovimentsPosibles(t,jugador);
    }
    public void testestatTerminal() {

        boolean b = m.estatTerminal(t,jugador);
    }
    public void testMiniMax() {

        Pair mm = m.MiniMax(t, jg);
    }
    public void testvalorMax() {

        int vm = m.valorMax(t, jg);
    }
    public void testvalorMin() {

        int vmin = m.valorMin(t,jg);
    }
    public void main (String [] args){
        testgetMovimentAlgorism1();

    }

}
