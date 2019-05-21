package src.Domini;

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


    //CONSTRUCTORES
    public Piece (Piece p){
        this.color = p.color;
        Pair aux = new Pair(0,0);
        //System.out.println("la posicio de la piece per parametre es " + p.getPos());
        aux.setFirst(p.getPos().getFirst());
        aux.setSecond(p.getPos().getSecond());
        this.pos = aux;
        //System.out.println("la posicio despres del pair es " + this.getPos());


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

    //SETTERS
    public void setPos(int a, int b) {
        pos.setFirst(a);
        pos.setSecond(b);
    }

    //ALTRES MÈTODES

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        return null;
    }
    public ArrayList<Pair> calculaMovimentsJaqueMate(Piece[][] m, int i, int j) {return null;}

}



