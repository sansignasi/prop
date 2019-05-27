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

    /**
     * Constructora de la classe Piece
     * @param p Crearem una Piece idèntica a la rebuda per paràmetre
     */
    public Piece (Piece p){
        this.color = p.color;
        Pair aux = new Pair(0,0);
        //System.out.println("la posicio de la piece per parametre es " + p.getPos());
        aux.setFirst(p.getPos().getFirst());
        aux.setSecond(p.getPos().getSecond());
        this.pos = aux;
        //System.out.println("la posicio despres del pair es " + this.getPos());


    }

    /**
     * Creadora per defecte de la classe Piece
     */
    public Piece() {
        super();
    }

    /**
     * Creadora de la classe Piece
     * @param c Crearem una Piece amb color equivalent al paràmetre c
     */
    public Piece(char c) {
        super();
        //COLOR
        if (c=='b'){
            this.color = PieceColor.Black;
        }
        else this.color = PieceColor.White;
        pos = new Pair(null,null);
    }

    /**
     * Creadora de la classe Piece que crearà una Piece inicialitzant tots els atributs que s'han rebut per paràmetre
     * @param c Color
     * @param x Posició x
     * @param y Posició y
     */
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

    /**
     * Funció abstracta que es redefinirà a les subclasses
     */
    public String getTipus() {
        return null;
    }

    /**
     * Funció abstracta que es redefinirà a les subclasses
     * @return
     */
    public int getValor() {
        return 0;
    }

    /**
     * Getter del Jugador
     * @return Retorna 1 o 0 en funció de si la Piece es blanca o negra
     */
    public int getJugador() {
        if (this.color==PieceColor.Black) return 1;
        else return 0;
    }

    /**
     * Getter del color de la Piece
     * @return Retorna un char que indica el color (b = negre, w = blanc)
     */
    public char getColor() {
        if (this.color==PieceColor.Black) return 'b';
        else return 'w';
    }

    /**
     * Funció abstracta que es redefinirà a les subclasses
     * @return
     */
    public char getLletra(){
        return 0;
    }

    /**
     * Retorna la posició de la Piece
     * @return Retorna un Pair que indica la posició de la Piece
     */
    public Pair getPos() {
        return pos;
    }

    //SETTERS

    /**
     * Setter de la posició de la Piece
     * @param a Indica la x
     * @param b Indica la y
     */
    public void setPos(int a, int b) {
        pos.setFirst(a);
        pos.setSecond(b);
    }

    //ALTRES MÈTODES

    /**
     * Funció abstracta que es redefinirà a les subclasses
     * @param m Matriu de Piece
     * @param i Posició x de la piece
     * @param j Posició y de la piece
     * @return
     */
    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        return null;
    }

    /**
     * Funció abstracta que es redefinirà a les subclasses
     * @param m Matriu de Piece
     * @param i Posició x de la piece
     * @param j Posició y de la piece
     * @return
     */
    public ArrayList<Pair> calculaMovimentsJaqueMate(Piece[][] m, int i, int j) {return null;}

}



