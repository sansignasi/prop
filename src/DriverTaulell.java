package src;
import java.util.Scanner;


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
    public static void testActualitzaTaulell() throws IncorrectFENException{
        testCarregarFen();
    }


    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Introduir FEN");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    testCarregarFen();
                    break;
                case 2:
                    testActualitzaTaulell();
                    System.out.println("Adiós");
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
            System.out.println();
        }
    }

}