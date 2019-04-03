package src;
import java.util.Scanner;
import lib.Pair;


import java.util.ArrayList;

public class DriverTaulell {
    public static void testCarregarFen() throws IncorrectFENException {
        Piece[][] matriu = new Piece[8][8];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = null;
            }
        }
        Taulell t = new Taulell();
        Scanner sc = new Scanner(System.in);
        System.out.println("Meteme un buen FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        Piece[][] mat = t.getTaulell();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]!=null) {
                    System.out.print(mat[i][j].getLletra() + " " + " " + " ");
                }
                else System.out.print("-" + " " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
        String st = t.taulellAFEN();
        System.out.print(st);
    }
    public static void testMostraTaulell() throws IncorrectFENException {
        Piece[][] matriu = new Piece[8][8];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = null;
            }
        }
        Taulell t = new Taulell();
        Scanner sc = new Scanner(System.in);
        System.out.println("Meteme un buen FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        t.mostrarTaulell();
    }
    public static void testActualitzaTaulell() throws IncorrectFENException{
        Piece[][] matriu = new Piece[8][8];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = null;
            }
        }
        Taulell t = new Taulell();
        Scanner sc = new Scanner(System.in);
        System.out.println("Meteme un buen FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        Piece[][] mat = t.getTaulell();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]!=null) {
                    System.out.print(mat[i][j].getLletra() + " " + " " + " ");
                }
                else System.out.print("-" + " " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Escriu quina peça vols moure:");
        sc = new Scanner(System.in);
        int px = sc.nextInt();
        int py = sc.nextInt();
        Piece p = t.getPiece(px,py);
        System.out.println("Escriu on la vols moure");
        sc = new Scanner(System.in);
        int fx = sc.nextInt();
        int fy = sc.nextInt();
        Pair posf = new Pair(fx,fy);
        t.actualitzarTaulell(p,posf);
        Piece[][] mat2 = t.getTaulell();
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[i].length; j++) {
                if (mat[i][j]!=null) {
                    System.out.print(mat2[i][j].getLletra() + " " + " " + " ");
                }
                else System.out.print("-" + " " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
        String st = t.taulellAFEN();
        System.out.print(st);
    }


    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Carregar FEN");
            System.out.println("2.Actualitzar Taulell");
            System.out.println("3.Mostrar Taulell");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    testCarregarFen();
                    break;
                case 2:
                    testActualitzaTaulell();
                    break;
                case 3:
                    testMostraTaulell();
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
            System.out.println();
        }
    }

}