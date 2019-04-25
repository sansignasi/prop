package src;
import java.util.Scanner;
import lib.Pair;

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
        t.taulellAFEN();
    }
    public static void testActualitzaTaulell(){
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

    public static void testJaquemate() {
        taux.mostrarTaulell();
        if(taux.jaquemate(1))System.out.println("Hay Jaquemate");
        else System.out.println("No hay jaquemate");
    }

    public static Piece[][] matriu = { {null, null, null,new King('b',0,3),null,null,new Queen('w',0,6),null}, //R,-,K,-,-,B,-,R
            {null,null, null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {null, null,null,new King('w',2,3),null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null}}; //para probar eel jaquemate


    public static Taulell taux = new Taulell(matriu); //para probar el jaquemate

    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Carregar FEN");
            System.out.println("2.Actualitzar Taulell");
            System.out.println("3.Mostrar Taulell");
            System.out.println("4.Test Jaque Mate");
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
                case 4:
                    testJaquemate();
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
            System.out.println();
        }
    }

}