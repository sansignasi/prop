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
    public Piece(){
        Piece p = new Piece();
    }

    public Piece(PieceColor c, TipusPiece t){
        PieceColor = c;
        TipusPiece = t;
    }
    boolean posValida(int p){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.


}
