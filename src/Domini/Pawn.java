package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Pawn extends Piece {

    //CREADORES

    /**
     * Creadora de la classe Pawn que inicialitza la superclasse amb la Piece rebuda com a paràmetre
     * @param p Piece que es rep com a paràmetre
     */
    public Pawn(Piece p) {
        super(p);
    }

    /**
     * Creadora de la classe Pawn que inicialitza el color de la superclasse Piece
     * @param c Color
     */
    public Pawn(char c) {
        super(c);
    }

    /**
     * Creadora de la classe Pawn que inicialitza els atributs rebuts com a paràmetre de la superclasse
     * @param c color
     * @param x x
     * @param y y
     */
    public Pawn(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS

    /**
     * Getter del tipus de Tipus de Piece
     * @return Retorna un string dient que es una Piece del tipus Pawn
     */
    public String getTipus() {
        return "Pawn";
    }

    /**
     * Getter del valor associat a la classe pawn
     * @return Retorna un enter
     */
    public int getValor() {
        return 1;
    }

    /**
     * Getter de la lletra associada a la classe Pawn
     * @return Retorna una p
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'P';
        else return 'p';
    }

    //MÈTODES

    /**
     * Funció que calcula els moviments possibles que pot realitzar un Pawn des de la posició on està
     * @param m Matriu de Piece
     * @param i Posició x de la piece
     * @param j Posició y de la piece
     * @return Retorna un vector de pairs amb Piece i posició possible on pot anar
     */
    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        Piece p = m[i][j];
        ArrayList<Pair> res = new ArrayList<>();

        if (p.getColor() == 'b') {
            if (i < 7) {
                if (m[i + 1][j] == null) res.add(new Pair(p, new Pair(i + 1, j)));
                if ((j < 7 && m[i+1][j+1] != null) && m[i + 1][j + 1].getColor() != p.getColor()) res.add(new Pair(p, new Pair(i + 1, j + 1)));
                if ((j > 0 && m[i+1][j-1] != null) && m[i + 1][j - 1].getColor() != p.getColor()) res.add(new Pair(p, new Pair(i + 1, j - 1)));
            }
        }

        else if (p.getColor() == 'w') {
            if (i > 0) {
                if (m[i - 1][j] == null) res.add(new Pair(p, new Pair(i - 1, j)));
                if ((j < 7 && m[i-1][j+1] != null) && m[i - 1][j + 1].getColor() != p.getColor()) res.add(new Pair(p, new Pair(i - 1, j + 1)));
                if ((j > 0 && m[i-1][j-1] != null) && m[i - 1][j - 1].getColor() != p.getColor()) res.add(new Pair(p, new Pair(i - 1, j - 1)));
            }
        }
        return res;
    }
    /**
     * Funció que calcula els moviments per saber com influeix en l'escac i mat
     * @param m Matriu de Piece
     * @param i Posició x de la piece
     * @param j Posició y de la piece
     * @return Retorna un vector de posicions amb Piece p i posició
     */
    public ArrayList<Pair> calculaMovimentsJaqueMate(Piece[][] m, int i, int j) {
        Piece p = m[i][j];
        int dir = 3;
        ArrayList<Pair> res = new ArrayList<>();
        boolean obs; //nos dice si ha encontrado un obstaculo en esa direccion
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) {//diagonal delante izq
                    if (super.getColor() == 'w') {
                        --auxi;
                        --auxj;
                    } else {
                        ++auxi;
                        ++auxj;
                    }
                    if (auxi < 0 || auxj < 0 || auxi > 7 || auxj > 7)
                        obs = true;  //te sales del tablero
                    else {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    }
                }
                if (dir == 2) {//palante
                    if (super.getColor() == 'w') {
                        --auxi;
                    } else ++auxi;
                    if (auxi < 0 || auxi > 7) obs = true;
                    else if (m[auxi][auxj] == null) {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    } else obs = true;  //te sales del tablero, hay otra pieza
                }
                if (dir == 3) {//diagonal delante derecha
                    if (super.getColor() == 'w') {
                        --auxi;
                        ++auxj;
                    } else {
                        ++auxi;
                        --auxj;
                    }

                    if (auxi < 0 || auxi > 7 || auxj < 0 || auxj > 7)
                        obs = true;  //te sales del tablero, hay otra pieza
                    else { //si hay enemigo
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    }
                }
            }
            --dir;
        }
        return res;
    }

}

