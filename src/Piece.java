package src;

import lib.Pair;

import java.util.ArrayList;

public class Piece {

    public enum PieceColor {
        Black,
        White
    }


    //ATRIBUTS
    private PieceColor color;
    //Color de la peça (de la enum)



    //MÈTODES

    //CONSTRUCTORES

    public Piece() {
        Piece p = new Piece();
    }

    public Piece(char c) {
        super();
        //COLOR
        if (c=='b'){
            this.color = PieceColor.Black;
        }
        else this.color = PieceColor.White;
    }


    //GETTERS

    public String getTipus() {
        return null;
    }

    public int getValor() {
        return 0;
    }

    public int getJugador() {
        if (this.color==PieceColor.Black) return 1;
        else return 0;
    }
    public char getColor() {
        if (this.color==PieceColor.Black) return 'b';
        else return 'w';
    }

    public char getLletra(){
        return 0;
    }

    //ALTRES MÈTODES

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        return null;
    }

}



