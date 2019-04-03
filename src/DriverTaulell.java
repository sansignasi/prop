package src;
import java.util.Scanner;
import lib.Pair;


import java.util.ArrayList;

public class DriverTaulell {

    private static Taulell t = new Taulell();


    public static void testCarregarFen() throws IncorrectFENException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Meteme un buen FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
    }
    public static void testMostraTaulell(){
        t.mostrarTaulell();
    }
    public static void testActualitzaTaulell() throws IncorrectFENException{

        System.out.println("Escriu quina peça vols moure: ");
        Scanner sc = new Scanner(System.in);
        String inPiece = sc.nextLine();
        int py = (int)inPiece.charAt(0) - 'a'; //COLUMNES
        int px = (int)inPiece.charAt(2) - '1'; //FILES
        Piece p = t.getPiece(px,py);
        System.out.println("Escriu on la vols moure");
        sc = new Scanner(System.in);
        String inPos = sc.nextLine();
        int fy = (int)inPos.charAt(0) - 'a'; //COLUMNES
        int fx = (int)inPos.charAt(2) - '1'; //FILES
        Pair posf = new Pair(fx,fy);
        t.actualitzarTaulell(p,posf);
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