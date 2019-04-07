package src;

import lib.Pair;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Piece p) {
        super(p);
    }

    public Knight(char c) {
        super(c);
    }

    public Knight(char c,int x, int y) {
        super(c,x,y);
    }

    public String getTipus() {
        return "Knight";
    }

    public int getValor() {
        return 3;
    }

    public char getLletra(){
        if(super.getColor() == 'w')return 'N';
        else return 'n';
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
}
