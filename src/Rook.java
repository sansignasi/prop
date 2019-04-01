package src;

import lib.Pair;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(char c) {
        super(c);
    }

    public String getTipus() {
        return "Rook";
    }

    public int getValor() {
        return 5;
    }

    public char getLletra(){
        if(super.getColor() == 'w')return 'R';
        else return 'r';
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
}
