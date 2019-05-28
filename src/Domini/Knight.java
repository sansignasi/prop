package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Knight extends Piece {

    //CREADORES

    /**
     * Creadora de la classe Knight
     * @param p Paràmetre amb el que inicialitzarem la superclasse
     */
    public Knight(Piece p) {
        super(p);
    }

    /**
     * Creadora de la classe Knight
     * @param c Paràmetre amb el que inicialitzarem el color de la superclasse
     */
    public Knight(char c) {
        super(c);
    }

    /**
     * Creadora de la classe Knight on inicialitzarem els atributs de la superclasse amb els paràmetres rebuts
     * @param c Color
     * @param x X
     * @param y Y
     */
    public Knight(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS

    /**
     * Getter del tipus de Piece
     * @return Retorna un string
     */
    public String getTipus() {
        return "Knight";
    }

    /**
     * Getter del valor de la classe Knight
     * @return Retorna un enter
     */
    public int getValor() {
        return 3;
    }

    /**
     * Getter de la lletra a la que s'associa la classe Knight
     * @return Retorna una n
     */
    public char getLletra(){
        if(super.getColor() == 'w')return 'N';
        else return 'n';
    }

    //MÈTODES

    /**
     * Funció que calcula els moviments possibles que pot realitzar un Knight des de la posició on està
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
                if (dir == 1) {
                    auxi -= 2;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                else if(dir == 2) {
                    auxi -= 2;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if(dir == 3) {
                    auxi += 2;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if(dir == 4) {
                    auxi += 2;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if(dir == 5) {
                    auxj += 2;
                    --auxi;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if(dir == 6) {
                    auxj += 2;
                    ++auxi;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if(dir == 7) {
                    auxj -= 2;
                    ++auxi;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if(dir == 8) {
                    auxj -= 2;
                    --auxi;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                if(!obs) {
                    if (m[auxi][auxj] == null || !(m[auxi][auxj].getColor() == p.getColor())) {
                        res.add(new Pair(p, new Pair(auxi, auxj)));
                        obs = true;
                    }
                    else obs = true;
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
                if (dir == 1) {
                    auxi -= 2;
                    --auxj;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                else if(dir == 2) {
                    auxi -= 2;
                    ++auxj;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if(dir == 3) {
                    auxi += 2;
                    --auxj;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if(dir == 4) {
                    auxi += 2;
                    ++auxj;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if(dir == 5) {
                    auxj += 2;
                    --auxi;
                    if (auxi < 0 || auxj > 7) obs = true;
                }
                else if(dir == 6) {
                    auxj += 2;
                    ++auxi;
                    if (auxi > 7 || auxj > 7) obs = true;
                }
                else if(dir == 7) {
                    auxj -= 2;
                    ++auxi;
                    if (auxi > 7 || auxj < 0) obs = true;
                }
                else if(dir == 8) {
                    auxj -= 2;
                    --auxi;
                    if (auxi < 0 || auxj < 0) obs = true;
                }
                if(!obs) {
                    res.add(new Pair(p, new Pair(auxi, auxj)));
                    obs = true;
                }
            }
            --dir;
        }
        return res;
    }
}
