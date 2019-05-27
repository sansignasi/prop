package src.Domini;

import lib.Pair;

import java.util.ArrayList;

public class Rook extends Piece {

    //CONSTRUCTORES
    public Rook(Piece p) {
        super(p);
    }

    public Rook(char c) {
        super(c);
    }

    public Rook(char c,int x, int y) {
        super(c,x,y);
    }

    //GETTERS
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

    //MÈTODES
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
