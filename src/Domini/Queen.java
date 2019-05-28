package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Queen extends Piece {

    //CONSTRUCTORES

    /**
     * Creadora de la classe Queen que inicialitza la superclasse Piece amb la Piece rebuda com a paràmetre
     * @param p Piece p
     */
    public Queen(Piece p) {
        super(p);
    }

    /**
     * Creadora de la classe Queen que inicialitza el color de la superclasse
     * @param c color
     */
    public Queen(char c) {
        super(c);
    }

    /**
     * Creadora de la classe Queen que inicialitza els atributs de la superclasse que s'han rebut com a paràmetre
     * @param c Color
     * @param x X
     * @param y Y
     */
    public Queen(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS

    /**
     * Getter del tipus de Piece
     * @return Retorna que es una Piece del tipus Queen
     */
    public String getTipus() {
        return "Queen";
    }

    /**
     * Getter del valor de la classe Queen
     * @return Retorna un enter
     */
    public int getValor() {
        return 9;
    }

    /**
     * Getter del char associat a la classe Queen
     * @return Retorna un char
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'Q';
        else return 'q';
    }

    //MÈTODES

    /**
     * Funció que calcula els moviments possibles que pot realitzar una Queen des de la posició on està
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
                }
                else if (dir == 2) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if (dir == 3) { //recta abaj
                    ++auxi;
                    if (auxi > 7) obs = true;
                }
                else if (dir == 4) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if (dir == 5) { //recta der
                    --auxj;
                    if (auxj < 0) obs = true;
                }
                else if (dir == 6) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                else if (dir == 7) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
                }
                else if (dir == 8) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                if(!obs) {
                    if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    else if (m[auxi][auxj].getColor() == p.getColor()) obs = true;
                    else {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    }
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
                }
                else if (dir == 2) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if (dir == 3) { //recta abaj
                    ++auxi;
                    if (auxi > 7) obs = true;
                }
                else if (dir == 4) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if (dir == 5) { //recta der
                    --auxj;
                    if (auxj < 0) obs = true;
                }
                else if (dir == 6) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                else if (dir == 7) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
                }
                else if (dir == 8) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                if(!obs){
                    if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    else if (m[auxi][auxj].getColor() == p.getColor()){
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    }
                    else {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        if(!(m[auxi][auxj] instanceof King))obs = true; //si no es un rey se cuenta como obstaculo
                    }
                }
            }
            --dir;
        }
        return res;
    }
}
