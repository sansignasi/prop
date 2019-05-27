package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Bishop extends Piece{

    /**
     * Creadora de la classe Bishop que crea una Piece Bishop inicialitzant tots els paràmetres de Piece als parametres de la Piece rebuda com a input
     * @param p Piece rebuda com a input
     */
    public Bishop(Piece p) {
        super(p);
    }

    /**
     * Creadora de la classe Bishop que crea un Bishop inicialitzant el color de la superclasse Piece al paràmetre rebut com a input
     * @param c Color de la Piece
     */
    public Bishop(char c) {
        super(c);
    }

    /**
     * Creadora de la classe Bishop que inicialitza els paràmetres de la seva superclasse Piece als paràmetres rebuts com a input
     * @param c Color
     * @param x X
     * @param y Y
     */
    public Bishop(char c,int x, int y) {
        super(c,x,y);
    }

    /**
     * Getter del tipus de Piece
     * @return Retorna el nom de la Classe
     */
    public String getTipus() {
        return "Bishop";
    }

    /**
     * Getter del valor que li hem assignat a la classe Bishop
     * @return Retorna el seu valor
     */
    public int getValor() {
        return 3;
    }

    /**
     * Getter de la lletra corresponent de la classe Bishop
     * @return Retorna la lletra corresponent de la classe Bishop que és la 'B'
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'B';
        else return 'b';
    }

    /**
     * Funció que calcula els moviments possibles que pot realitzar un Bishop des de la posició on està
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
                if (dir == 1) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0)obs = true;
                }
                else if (dir == 2) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if (dir == 3) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if (dir == 4) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                if(!obs){
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
        dir = 4;
        while (dir > 0) {//calcular todas las dir de movimiento posibles
            int auxi = i;
            int auxj = j;
            obs = false;
            while (!obs) {
                if (dir == 1) { //diag sup izq
                    --auxi;
                    --auxj;
                    if (auxi < 0 || auxj < 0)obs = true;
                }
                else if (dir == 2) { //diag sup der
                    --auxi;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if (dir == 3) { //diag inf izq
                    ++auxi;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if (dir == 4) { //diag inf der
                    ++auxi;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
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
