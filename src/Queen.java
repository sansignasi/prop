package src;

import lib.Pair;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Piece p) {
        super(p);
    }

    public Queen(char c) {
        super(c);
    }

    public Queen(char c,int x, int y) {
        super(c,x,y);
    }

    public String getTipus() {
        return "Queen";
    }

    public int getValor() {
        return 9;
    }

    public char getLletra(){
        if(super.getColor() == 'w')return 'Q';
        else return 'q';
    }

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
}
