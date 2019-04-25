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
        if(s.equals("Peó")){
            p = new Pawn('w');
            System.out.println(p.getTipus());
        }
        else if(s.equals("Alfil")){
            p = new Bishop('w');
            System.out.println(p.getTipus());
        }
        else if(s.equals("Torre")){
            p = new Rook('w');
            System.out.println(p.getTipus());
        }
        else if(s.equals("Cavall")){
            p = new Knight('w');
            System.out.println(p.getTipus());
        }
        else if(s.equals("Rei")){
            p = new King('w');
            System.out.println(p.getTipus());
        }
        else if(s.equals("Reina")){
            p = new Queen('w');
            System.out.println(p.getTipus());
        }
        else System.out.println("tipus incorrecte");
    }

    public static void testgetLletra(String s, char c) {
        Piece p;
        if(s.equals("Peó")){
            p = new Pawn(c);
            System.out.println(p.getLletra());
        }
        else if(s.equals("Alfil")){
            p = new Bishop(c);
            System.out.println(p.getLletra());
        }
        else if(s.equals("Torre")){
            p = new Rook(c);
            System.out.println(p.getLletra());
        }
        else if(s.equals("Cavall")){
            p = new Knight(c);
            System.out.println(p.getLletra());
        }
        else if(s.equals("Reina")){
            p = new Queen(c);
            System.out.println(p.getLletra());
        }
        else if(s.equals("Rei")){
            p = new King(c);
            System.out.println(p.getLletra());
        }
        else System.out.println("tipus incorrecte");
    }

    public static void testcalculaMovimentsPiece() throws IncorrectFENException{
        Taulell t = new Taulell();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        t.mostrarTaulell();
        System.out.println("Escull la posició de la peça a testejar(ex a5):");
        String inPiece = sc.nextLine();
        int py = (int) inPiece.charAt(0) - 'a'; //COLUMNES
        int px = Math.abs(((int)inPiece.charAt(1) - '1')-7); //FILES
        Piece p = t.getPiece(px,py);
        ArrayList<Pair> ar = p.calculaMovimentsPiece(t.getTaulell(),px,py);
        for(int i = 0; i < ar.size();++i){
            Pair pi = ar.get(i);
            Pair p2 = (Pair) pi.getSecond();
            System.out.println("Possible posició: "+p2.getFirst()+" "+p2.getSecond());
        }
    }
    public static void main (String [] args)throws IncorrectFENException{
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
                    testcalculaMovimentsPiece();
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
        }
    }
}
