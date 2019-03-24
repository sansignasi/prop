package src;

import java.awt.*;

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
        if(tipus.equals("Queen")) return "Queen";
        if(tipus.equals("Rook")) return "Rook";
        if(tipus.equals("Bishop")) return "Bishop";
        if(tipus.equals("Knight")) return "Knight";
        if(tipus.equals("Pawn")) return "Pawn";
        else return null;
    }

    public boolean posValida(int p){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.



}
