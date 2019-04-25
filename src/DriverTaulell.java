package src;
import java.util.Scanner;
import lib.Pair;

public class DriverTaulell {

    private static Taulell t = new Taulell();

    public static void testCarregarFen() throws IncorrectFENException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
    }
    public static void testMostraTaulell(){
        t.mostrarTaulell();
        t.taulellAFEN();
    }
    public static void testActualitzaTaulell(){
        t.mostrarTaulell();
        System.out.println("Escriu quina peça vols moure(ex a5): ");
        Scanner sc = new Scanner(System.in);
        String inPiece = sc.nextLine();
        int py = (int) inPiece.charAt(0) - 'a'; //COLUMNES
        int px = Math.abs(((int)inPiece.charAt(1) - '1')-7); //FILES
        Piece p = t.getPiece(px,py);
        System.out.println("Escriu on la vols moure(ex a5):");
        sc = new Scanner(System.in);
        String inPos = sc.nextLine();
        int fy = (int) inPos.charAt(0) - 'a'; //COLUMNES
        int fx = Math.abs(((int)inPos.charAt(1) - '1')-7); //FILES
        Pair posf = new Pair(fx,fy);
        t.actualitzarTaulell(p,posf);
        t.mostrarTaulell();
    }
    public static void testMate()throws IncorrectFENException  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        t.mostrarTaulell();
        if(t.jaque(1))System.out.println("Es fa escac");
        else System.out.println("No es fa escac");
    }

    public static void testJaquemate()throws IncorrectFENException  {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un FEN:");
        String fen2 = sc.nextLine();
        t.carregaFEN(fen2);
        t.mostrarTaulell();
        if(t.jaquemate(1))System.out.println("Es fa escac y mat");
        else System.out.println("No es fa escac y mat");
    }


    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Carregar FEN");
            System.out.println("2.Actualitzar Taulell");
            System.out.println("3.Mostrar Taulell");
            System.out.println("4.Test situació escac");
            System.out.println("5.Test situació escac i mat");
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
                    testMate();
                    break;
                case 5:
                    testJaquemate();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
            System.out.println();
        }
    }

}