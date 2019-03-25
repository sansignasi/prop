package src;

import lib.Pair;

import java.awt.*;
import java.util.ArrayList;

public class Piece {

    public enum PieceColor{
        Black,
        White
    }

    public enum TipusPiece{
        King,
        Queen,
        Rook,
        Bishop,
        Knight,
        Pawn,
        None
    }




    //ATRIBUTS
    private PieceColor color;
        //Color de la peça (de la enum)
    private TipusPiece tipus;
        //Tipus de peça (de la enum)

    //MÈTODES

    public int getJugador(){
        if (color.equals("Black")) return 1;
        else return 0;
    }

    public Piece(){
        Piece p = new Piece();
    }

    public Piece(PieceColor c, TipusPiece t){
        this.color = c;
        this.tipus = t;
    }

    @Override

    public String getTipus(){
        if(tipus.equals("King")) return "King";
        else if(tipus.equals("Queen")) return "Queen";
        else if(tipus.equals("Rook")) return "Rook";
        else if(tipus.equals("Bishop")) return "Bishop";
        else if(tipus.equals("Knight")) return "Knight";
        else if(tipus.equals("Pawn")) return "Pawn";
        else return null;
    }

    public boolean posValida(int p){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        Piece p = m[i][j];
        int dir;
        ArrayList<Pair> res = new ArrayList<>();
        boolean obs; //nos dice si ha encontrado un obstaculo en esa direccion
        if (p.getTipus().equals("Pawn")) { //PEON
            dir = 3;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) {//diagonal delante izq
                        if (p.color.equals("White")) {
                            --auxi;
                            --auxj;
                        } else {
                            ++auxi;
                            ++auxj;
                        }
                        if (auxi < 0 || auxj < 0 || auxi > 7 || auxj > 7 || m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null)
                            obs = true;  //te sales del tablero, hay pieza de tu equipo o no hay nada cuenta como obs
                        else { //si hay enemigo
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 2) {//palante
                        if (p.color.equals("White")) {
                            --auxi;
                        } else ++auxi;
                        if (auxi < 0 || auxi > 7 || m[auxi][auxj].color.equals(p.color) || !m[auxi][auxj].color.equals(p.color))
                            obs = true;  //te sales del tablero, hay otra pieza
                        else if (m[auxi][auxj] == null) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 3) {//diagonal delante derecha
                        if (p.color.equals("White")) {
                            --auxi;
                            ++auxj;
                        } else {
                            ++auxi;
                            --auxj;
                        }
                        if (auxi < 0 || auxi > 7 || auxj < 0 || auxj > 7 || m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null)
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
        if (p.getTipus().equals("Knight")) { //Caballo
            dir = 4;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) {
                        auxi -= 2;
                        --auxj;
                        if (auxi < 0 || auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 2) {
                        auxi -= 2;
                        ++auxj;
                        if (auxi < 0 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 3) {
                        auxi += 2;
                        --auxj;
                        if (auxi > 7 || auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 4) {
                        auxi += 2;
                        ++auxj;
                        if (auxi > 7 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
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
        if (p.getTipus().equals("Bishop")) { //Alfil
            dir = 4;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //diag sup izq
                        --auxi;
                        --auxj;
                        if (auxi < 0 || auxj < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 2) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                    if (dir == 3) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                    if (dir == 4) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                }
                --dir;
            }
            return res;
        }
        if (p.getTipus().equals("Rook")) { //Torre
            dir = 4;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if (auxj > 7 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 2) { //recta abajo
                        ++auxi;
                        if (auxi > 7 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 3) { //recta izq
                        --auxj;
                        if (auxj < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 4) { //recta arriba
                        --auxi;
                        if (auxi < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                }
                --dir;
            }
            return res;
        }
        if (p.getTipus().equals("Queen")) { //Reina
            dir = 8;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if (auxj > 7 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 2) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                    if (dir == 3) { //recta abaj
                        ++auxi;
                        if (auxi > 7 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 4) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                    if (dir == 5) { //recta der
                        --auxj;
                        if (auxj < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 6) { //diag sup izq
                        --auxi;
                        --auxj;
                        if (auxi < 0 || auxj < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 7) { //recta arriba
                        --auxi;
                        if (auxi < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color)) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null)
                            res.add(new Pair(p, new Pair(auxi, auxj))); //si no hay nada añades y sigues
                    }
                    if (dir == 8) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color)) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        } else if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                    }
                }
                --dir;
            }
            return res;
        }
        if (p.getTipus().equals("King")) { //Rey
            dir = 8;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if (auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 2) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 3) { //recta abaj
                        ++auxi;
                        if (auxi > 7 || m[auxi][auxj].color.equals(p.color)) obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 4) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 5) { //recta der
                        --auxj;
                        if (auxj < 0 || m[auxi][auxj].color.equals(p.color)) obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 6) { //diag sup izq
                        --auxi;
                        --auxj;
                        if (auxi < 0 || auxj < 0 || m[auxi][auxj].color.equals(p.color))
                            obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 7) { //recta arriba
                        --auxi;
                        if (auxi < 0 || m[auxi][auxj].color.equals(p.color)) obs = true; //te sales o aliado es un obs
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) { //enemigo añades y paras de contar
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 8) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7 || m[auxi][auxj].color.equals(p.color)) obs = true;
                        else if (!m[auxi][auxj].color.equals(p.color) || m[auxi][auxj] == null) {
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

