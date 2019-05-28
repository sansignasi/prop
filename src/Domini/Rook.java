package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Rook extends Piece {

    //CONSTRUCTORES

    /**
     * Creadora de la classe Rook que inicialitza la superclasse Piece amb la Piece rebuda com a paràmetre
     * @param p Piece p
     */
    public Rook(Piece p) {
        super(p);
    }
    /**
     * Creadora de la classe Rook que inicialitza el color de la superclasse
     * @param c color
     */
    public Rook(char c) {
        super(c);
    }
    /**
     * Creadora de la classe Rook que inicialitza els atributs de la superclasse que s'han rebut com a paràmetre
     * @param c Color
     * @param x X
     * @param y Y
     */
    public Rook(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS
    /**
     * Getter del tipus de Piece
     * @return Retorna que es una Piece del tipus Rook
     */
    public String getTipus() {
        return "Rook";
    }
    /**
     * Getter del valor de la classe Rook
     * @return Retorna un enter
     */
    public int getValor() {
        return 5;
    }
    /**
     * Getter del char associat a la classe Rook
     * @return Retorna un char
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'R';
        else return 'r';
    }

    //MÈTODES

    /**
     * Funció que calcula els moviments possibles que pot realitzar un Rook des de la posició on està
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
        dir = 4;
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) { //recta der
                    ++auxj;
                    if (auxj > 7) obs = true;
                }
                else if(dir == 2) { //recta abajo
                    ++auxi;
                    if (auxi > 7) obs = true;
                }
                else if(dir == 3) { //recta izq
                    --auxj;
                    if (auxj < 0) obs = true;
                }
                else if(dir == 4) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
                }
                if(!obs){
                    if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    else if (m[auxi][auxj].getColor() == p.getColor())obs = true;
                    else{
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
        dir = 4;
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) { //recta der
                    ++auxj;
                    if (auxj > 7) obs = true;
                }
                else if(dir == 2) { //recta abajo
                    ++auxi;
                    if (auxi > 7) obs = true;
                }
                else if(dir == 3) { //recta izq
                    --auxj;
                    if (auxj < 0) obs = true;
                }
                else if(dir == 4) { //recta arriba
                    --auxi;
                    if (auxi < 0) obs = true;
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
