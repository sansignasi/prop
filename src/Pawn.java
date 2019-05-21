package src;

import lib.Pair;

import java.util.ArrayList;

public class Pawn extends Piece {

    //CREADORES
    public Pawn(Piece p) {
        super(p);
    }

    public Pawn(char c) {
        super(c);
    }

    public Pawn(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS
    public String getTipus() {
        return "Pawn";
    }

    public int getValor() {
        return 1;
    }

    public char getLletra(){
        if(super.getColor() == 'w')return 'P';
        else return 'p';
    }

    //MÈTODES
    /*public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
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
                    if (auxi < 0 || auxj < 0 || auxi > 7 || auxj > 7 || m[auxi][auxj] == null || m[auxi][auxj].getColor() == p.getColor())
                        obs = true;  //te sales del tablero, hay pieza de tu equipo o no hay nada cuenta como obs
                    else { //si hay enemigo
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

                    if (auxi < 0 || auxi > 7 || auxj < 0 || auxj > 7 || m[auxi][auxj] == null || m[auxi][auxj].getColor() == p.getColor())
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
    }*/

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

