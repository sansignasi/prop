package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class King extends Piece {

    //CREADORES

    /**
     * Creadora de la classe King
     * @param p Piece amb la que inicialitzarà la superclasse
     */
    public King(Piece p) {
        super(p);
    }

    /**
     * Creadora de la classe King
     * @param c Color amb el que inicialitzarà la superclasse
     */
    public King(char c) {
        super(c);
    }

    /**
     * Creadora de la classe King que inicialitzarà els paràmetres de la superclasse
     * @param c Color
     * @param x X
     * @param y Y
     */
    public King(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS


    /**
     * Getter del valor de la classe
     * @return Retorna 9999 ja que es tracta d'un rei
     */
    public int getValor() {
        return 200;
    }

    /**
     * Getter de la lletra equivalent a la classe
     * @return Retorna K
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'K';
        else return 'k';
    }

    //MÈTODES
    /**
     * Funció que calcula els moviments possibles que pot realitzar un King des de la posició on està
     * @param m Matriu de Piece
     * @param i Posició x de la piece
     * @param j Posició y de la piece
     * @return Retorna un vector de pairs amb Piece i posició possible on pot anar
     */
    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        Piece p = m[i][j];
        int dir;
        ArrayList<Pair> res = new ArrayList<>();
        boolean obs; //nos dice si ha encontrado un obstaculo en esa direccion
        dir = 8;
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) { //recta der
                    ++auxj;
                    if (auxj > 7) obs = true;
                } else if (dir == 2) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                } else if (dir == 3) { //recta abaj
                    ++auxi;
                    if (auxi > 7) obs = true;
                } else if (dir == 4) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                } else if (dir == 5) { //recta der
                    --auxj;
                    if (auxj < 0) obs = true;
                } else if (dir == 6) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                } else if (dir == 7) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
                } else if (dir == 8) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                if (!obs) {
                    if (m[auxi][auxj] == null || !(m[auxi][auxj].getColor() == p.getColor())) {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    } else obs = true;
                }
            }
            --dir;
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
        int dir;
        ArrayList<Pair> res = new ArrayList<>();
        boolean obs; //nos dice si ha encontrado un obstaculo en esa direccion
        dir = 8;
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) { //recta der
                    ++auxj;
                    if (auxj > 7) obs = true;
                } else if (dir == 2) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                } else if (dir == 3) { //recta abaj
                    ++auxi;
                    if (auxi > 7) obs = true;
                } else if (dir == 4) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                } else if (dir == 5) { //recta der
                    --auxj;
                    if (auxj < 0) obs = true;
                } else if (dir == 6) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                } else if (dir == 7) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
                } else if (dir == 8) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                if (!obs) {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                }
            }
            --dir;
        }
        return res;
    }
}
