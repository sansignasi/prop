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
    private Pair pos;



    //MÈTODES

    //CONSTRUCTORES

    public Piece (Piece p){
        Piece p1 = new Piece();
        p1.color = p.color;
        Pair aux = new Pair(0,0);
        aux.setFirst(p.getPos().getFirst());
        aux.setSecond(p.getPos().getSecond());
    }

    public Piece() {
        super();
    }

    public Piece(char c) {
        super();
        //COLOR
        if (c=='b'){
            this.color = PieceColor.Black;
        }
        else this.color = PieceColor.White;
        pos = new Pair(null,null);
    }

    public Piece(char c, int x, int y) {
        super();
        //COLOR
        if (c=='b'){
            this.color = PieceColor.Black;
        }
        else this.color = PieceColor.White;
        pos = new Pair(x,y);
    }

    public void setPos(int a, int b) {
        pos.setFirst(a);
        pos.setSecond(b);
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

    public Pair getPos() {
        return pos;
    }

    //ALTRES MÈTODES

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        return null;
    }

}



