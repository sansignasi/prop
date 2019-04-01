package src;

import lib.Pair;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(char c) {
        super(c);
    }

    public String getTipus() {
        return "Bishop";
    }

    public int getValor() {
        return 3;
    }

    public char getLletra(){
        if(super.getColor() == 'w')return 'B';
        else return 'b';
    }

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

}
