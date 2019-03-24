package src;

import lib.Pair;

import java.awt.*;
import java.util.ArrayList;

public class Piece {


    public enum PieceColor{
        Black,
        White
    }

    public enum TipusPiece{
        King,
        Queen,
        Rook,
        Bishop,
        Knight,
        Pawn,
        None
    }




    //ATRIBUTS
    private PieceColor color;
        //Color de la peça (de la enum)
    private TipusPiece tipus;
        //Tipus de peça (de la enum)

    //MÈTODES

    public int getJugador(){
        if (color.equals("Black")) return 1;
        else return 0;
    }

    public Piece(){
        Piece p = new Piece();
    }

    public Piece(PieceColor c, TipusPiece t){
        this.color = c;
        this.tipus = t;
    }

    @Override

    public String getTipus(){
        if(tipus.equals("King")) return "King";
        else if(tipus.equals("Queen")) return "Queen";
        else if(tipus.equals("Rook")) return "Rook";
        else if(tipus.equals("Bishop")) return "Bishop";
        else if(tipus.equals("Knight")) return "Knight";
        else if(tipus.equals("Pawn")) return "Pawn";
        else return null;
    }

    public boolean posValida(int p){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j){
        return null;
    }



}
