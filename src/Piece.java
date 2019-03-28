package src;

import lib.Pair;

import java.awt.*;
import java.util.ArrayList;

public class Piece {

    public enum PieceColor {
        Black,
        White
    }

    public enum TipusPiece {
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
    private int valor;
    //Valor de la peça



    //MÈTODES

    //CONSTRUCTORES

    public Piece() {
        Piece p = new Piece();
    }

    public Piece(PieceColor c, TipusPiece t) {
        this.color = c;
        this.tipus = t;
    }
    public Piece(char c,char t) {
        //COLOR
        if (c=='b'){
            this.color = PieceColor.Black;
        }
        else this.color = PieceColor.White;
        //TIPUS
        if (t=='k' || t=='K'){
            this.tipus = TipusPiece.King;
        }
        else if (t=='q'|| t=='Q'){
            this.tipus = TipusPiece.Queen;
        }
        else if (t=='r'|| t=='R'){
            this.tipus = TipusPiece.Rook;
        }
        else if (t=='b'|| t=='B'){
            this.tipus = TipusPiece.Bishop;
        }
        else if (t=='n'|| t=='N'){
            this.tipus = TipusPiece.Knight;
        }
        else if (t=='p'|| t=='P') {
            this.tipus = TipusPiece.Pawn;
        }
    }


    //GETTERS

    public String getTipus() {
        if (tipus==TipusPiece.King) return "King";
        else if (tipus==TipusPiece.Queen) return "Queen";
        else if (tipus==TipusPiece.Rook) return "Rook";
        else if (tipus==TipusPiece.Bishop) return "Bishop";
        else if (tipus==TipusPiece.Knight) return "Knight";
        else if (tipus==TipusPiece.Pawn) return "Pawn";
        else return null;
    }

    public int getValor() {
        if (tipus==TipusPiece.King) return 9999;
        else if (tipus==TipusPiece.Queen) return 9;
        else if (tipus==TipusPiece.Rook) return 5;
        else if (tipus==TipusPiece.Bishop) return 3;
        else if (tipus==TipusPiece.Knight) return 3;
        else if (tipus==TipusPiece.Pawn) return 1;
        else return 0;
    }

    public int getJugador() {
        if (this.color==PieceColor.Black) return 1;
        else return 0;
    }
    public char getColor() {
        if (this.color==PieceColor.Black) return 'b';
        else return 'w';
    }

    public char getLletra(){
        if(this.color==PieceColor.Black){
            if (tipus==TipusPiece.King) return 'k';
            else if (tipus==TipusPiece.Queen) return 'q';
            else if (tipus==TipusPiece.Rook) return 'r';
            else if (tipus==TipusPiece.Bishop) return 'b';
            else if (tipus==TipusPiece.Knight) return 'n';
            else if (tipus==TipusPiece.Pawn) return 'p';
        }
        else if(this.color==PieceColor.White){
            if (tipus==TipusPiece.King) return 'K';
            else if (tipus==TipusPiece.Queen) return 'Q';
            else if (tipus==TipusPiece.Rook) return 'R';
            else if (tipus==TipusPiece.Bishop) return 'B';
            else if (tipus==TipusPiece.Knight) return 'N';
            else if (tipus==TipusPiece.Pawn) return 'P';
        }
        return 0;
    }

    //ALTRES MÈTODES

    public ArrayList<Pair> calculaMovimentsPiece(Piece[][] m, int i, int j) {
        Piece p = m[i][j];
        int dir;
        ArrayList<Pair> res = new ArrayList<>();
        boolean obs; //nos dice si ha encontrado un obstaculo en esa direccion
        if (p.tipus == TipusPiece.Pawn) { //PEON
            dir = 3;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) {//diagonal delante izq
                        if (p.color.equals(PieceColor.White)) {
                            --auxi;
                            --auxj;
                        } else {
                            ++auxi;
                            ++auxj;
                        }
                        if (auxi < 0 || auxj < 0 || auxi > 7 || auxj > 7 || m[auxi][auxj] == null || m[auxi][auxj].color.equals(p.color) )
                            obs = true;  //te sales del tablero, hay pieza de tu equipo o no hay nada cuenta como obs
                        else { //si hay enemigo
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 2) {//palante
                        if (p.color.equals(PieceColor.White)) {
                            --auxi;
                        } else ++auxi;
                        if (auxi < 0 || auxi > 7) obs = true;
                        else if (m[auxi][auxj] == null) {
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else if (m[auxi][auxj].color.equals(p.color) || !m[auxi][auxj].color.equals(p.color))
                            obs = true;  //te sales del tablero, hay otra pieza

                    }
                    if (dir == 3) {//diagonal delante derecha
                        if (p.color.equals(PieceColor.White)) {
                            --auxi;
                            ++auxj;
                        } else {
                            ++auxi;
                            --auxj;
                        }

                        if (auxi < 0 || auxi > 7 || auxj < 0 || auxj > 7 || m[auxi][auxj] == null || m[auxi][auxj].color.equals(p.color))
                            obs = true;  //te sales del tablero, hay otra pieza
                        else { //si hay enemigo
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                }
                --dir;
            }
        }
        if (p.tipus == TipusPiece.Knight) { //Caballo
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
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 2) {
                        auxi -= 2;
                        ++auxj;
                        if (auxi < 0 || auxj > 7) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 3) {
                        auxi += 2;
                        --auxj;
                        if (auxi > 7 || auxj < 0) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                    }
                    if (dir == 4) {
                        auxi += 2;
                        ++auxj;
                        if (auxi > 7 || auxj > 7) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else obs = true;
                    }
                    if (dir == 5) {
                        auxj += 2;
                        --auxi;
                        if (auxi < 0 || auxj > 7) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else obs = true;
                    }
                    if (dir == 6) {
                        auxj += 2;
                        ++auxi;
                        if (auxi > 7 || auxj > 7) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else obs = true;
                    }
                    if (dir == 7) {
                        auxj -= 2;
                        ++auxi;
                        if (auxi > 7 || auxj < 0) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else obs = true;
                    }
                    if (dir == 8) {
                        auxj -= 2;
                        --auxi;
                        if (auxi < 0 || auxj < 0) obs = true;
                        else if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                            res.add(new Pair(p, new Pair(auxi, auxj)));
                            obs = true;
                        }
                        else obs = true;
                    }
                }
                --dir;
            }
        }
        if (p.tipus == TipusPiece.Bishop)  { //Alfil
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
                        else{
                            if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color)) obs = true;
                            else {
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 2) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color)) obs = true;
                            else {
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 3) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0)obs = true;
                        else{
                            if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color)) obs = true;
                            else {
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 4) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color)) obs = true;
                            else {
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                }
                --dir;
            }
        }
            if (p.tipus == TipusPiece.Rook) {  //Torre
            dir = 4;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if(auxj > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 2) { //recta abajo
                        ++auxi;
                        if(auxi > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 3) { //recta izq
                        --auxj;
                        if(auxj < 0)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 4) { //recta arriba
                        --auxi;
                        if(auxi < 0)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                }
                --dir;
            }
        }
        if (p.tipus == TipusPiece.Queen) { //Reina
            dir = 8;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if (auxj > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 2) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 3) { //recta abaj
                        ++auxi;
                        if (auxi > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 4) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 5) { //recta der
                        --auxj;
                        if (auxj < 0)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 6) { //diag sup izq
                        --auxi;
                        --auxj;
                        if (auxi < 0 || auxj < 0)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 7) { //recta arriba
                        --auxi;
                        if (auxi < 0) obs = true;
                        else {
                            if (m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color)) obs = true;
                            else {
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                    if (dir == 8) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7)obs = true;
                        else{
                            if(m[auxi][auxj] == null) res.add(new Pair(p, new Pair(auxi, auxj)));
                            else if (m[auxi][auxj].color.equals(p.color))obs = true;
                            else{
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                        }
                    }
                }
                --dir;
            }
        }
        if (p.tipus == TipusPiece.King) { //Rey
            dir = 8;
            while (dir > 0) {//calcular todas las dir de movimiento posibles
                int auxi = i;
                int auxj = j;
                obs = false;
                while (!obs) {
                    if (dir == 1) { //recta der
                        ++auxj;
                        if (auxj > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 2) { //diag inf der
                        ++auxi;
                        ++auxj;
                        if (auxi > 7 || auxj > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 3) { //recta abaj
                        ++auxi;
                        if (auxi > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 4) { //diag inf izq
                        ++auxi;
                        --auxj;
                        if (auxi > 7 || auxj < 0)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 5) { //recta der
                        --auxj;
                        if (auxj < 0)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 6) { //diag sup izq
                        --auxi;
                        --auxj;
                        if (auxi < 0 || auxj < 0)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 7) { //recta arriba
                        --auxi;
                        if (auxi < 0)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                    if (dir == 8) { //diag sup der
                        --auxi;
                        ++auxj;
                        if (auxi < 0 || auxj > 7)obs = true;
                        else{
                            if (m[auxi][auxj] == null || !m[auxi][auxj].color.equals(p.color)){
                                res.add(new Pair(p, new Pair(auxi, auxj)));
                                obs = true;
                            }
                            else obs = true;
                        }
                    }
                }
                --dir;
            }
        }
        return res;
    }
}



