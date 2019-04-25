package src;

import lib.Pair;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverPiece {

    public static void testgetJugador(char c){
        Piece p = new Pawn(c);
        System.out.println(p.getJugador());
    }

    public static void testgetTipus(String s) {
        Piece p;
        if(s.equals("Peó"))p = new Pawn('w');
        else if(s.equals("Alfil"))p = new Bishop('w');
        else if(s.equals("Torre"))p = new Rook('w');
        else if(s.equals("Cavall"))p = new Knight('w');
        else if(s.equals("Reina"))p = new Queen('w');
        else p = new King('w');
        System.out.println(p.getTipus());
    }

    public static void testgetLletra(String s, char c) {
        Piece p;
        if(s.equals("Peó"))p = new Pawn(c);
        else if(s.equals("Alfil"))p = new Bishop(c);
        else if(s.equals("Torre"))p = new Rook(c);
        else if(s.equals("Cavall"))p = new Knight(c);
        else if(s.equals("Reina"))p = new Queen(c);
        else p = new King(c);
        System.out.println(p.getLletra());
    }

    public static void testcalculaMovimentsPiece(String s, int x, int y){
        Piece[][] matriu = new Piece[8][8];
        Piece p;
        if(s.equals("Peó"))p = new Pawn('w');
        else if(s.equals("Alfil"))p = new Bishop('w');
        else if(s.equals("Torre"))p = new Rook('w');
        else if(s.equals("Cavall"))p = new Knight('w');
        else if(s.equals("Reina"))p = new Queen('w');
        else p = new King('w');
        matriu[x][y] = p;
        ArrayList<Pair> ar = p.calculaMovimentsPiece(matriu,x,y);
        for(int i = 0; i < ar.size(); ++i){
            Pair pi = ar.get(i);
            Pair p2 = (Pair) pi.getSecond();
            System.out.println("possible pos "+p2.getFirst()+" "+p2.getSecond());
        }
    }
    public static void main (String [] args){
        int opt = 99;
        while (opt!=0){
            System.out.println("~~~~DRIVER PEÇA~~~~");
            System.out.println("Tria una opció:");
            System.out.println("1.TestGetJugador");
            System.out.println("2.TestGetTipus");
            System.out.println("3.TestGetLletra");
            System.out.println("4.TestcalculaMovimentsPiece");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Escull un color: 'b'(negres) o 'w' (blanques)");
                    char c1 = reader.next().charAt(0);
                    testgetJugador(c1);
                    break;
                case 2:
                    Scanner reader2 = new Scanner(System.in);
                    System.out.println("Escull un tipus de peça(Peó,Alfil,Cavall,Torre,Rei,Reina)");
                    String t1 = reader2.nextLine();
                    testgetTipus(t1);
                    break;
                case 3:
                    Scanner reader3 = new Scanner(System.in);
                    Scanner readchar = new Scanner(System.in);
                    System.out.println("Escull un tipus de peça(Peó,Alfil,Cavall,Torre,Rei,Reina)");
                    String t2 = reader3.nextLine();
                    System.out.println("Escull un color(w o b)");
                    char c2 = readchar.next().charAt(0);
                    testgetLletra(t2,c2);
                    break;
                case 4:
                    Scanner reader4 = new Scanner(System.in);
                    System.out.println("Escull un tipus de peça(Peó,Alfil,Cavall,Torre,Rei,Reina)");
                    String t3 = reader4.nextLine();
                    System.out.println("Introdueix una coordenada per col.locar la peça en el taulell(ex 5 2)");
                    int x = reader4.nextInt();
                    int y = reader4.nextInt();
                    if(x > 7 || y > 7 || x < 0 || y < 0){
                        System.out.println("Posició invalida");
                        break;
                    }
                    testcalculaMovimentsPiece(t3,x,y);
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
        }
    }
}
