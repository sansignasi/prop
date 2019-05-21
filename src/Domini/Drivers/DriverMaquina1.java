package src.Domini.Drivers;

import lib.Pair;
import java.util.ArrayList;
import java.util.Scanner;

import src.Domini.*;

public class DriverMaquina1 {

    public static Maquina1 m = new Maquina1(0,0);

    //es pot treballar amb aquesta matriu de prova o es pot carregar un taulell amb un fen directament

    public static Piece[][] matriu = { {null, null, null,null,new King('b',0,4),null,null,null}, //R,-,K,-,-,B,-,R
            {new Pawn('b',1,0), new Pawn('b',1,1), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('w',2,0), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,new King('w',6,3),null,null,null,null},
            {null,null,null,null,null,null,null,null}};


    public static Taulell t = new Taulell(matriu);

    public  static void testgetMovimentAlgorism1(int jugador, int profunditat) throws IncorrectFENException {
        Pair p = m.jugarTorn(t, jugador, profunditat);
        System.out.println("El moviment que retorna la maquina 1 es " + p.getFirst() + " " + p.getSecond());
        System.out.println();

    }

    public static void testHeuristic1(int j) {
        int h1 = m.Heuristic1( t, j);
        System.out.println("L'avaluació del taulell de l'Heurístic 1 es " + h1);
        System.out.println();


    }

    public static void testcalculaMovimentsPosibles(int j) {

        ArrayList<Pair> p = m.calculaMovimentsPosibles(t,j);
        System.out.println("El vector resultants amb tots els moviments possibles de les peces del jugador es de: ");
        for (int i = 0; i < p.size(); ++i) {
            System.out.println(p.get(i).getFirst() + " " + p.get(i).getSecond());
        }
        System.out.println();


    }
    public static void testestatTerminal(int jugador, int profunditat) {

        boolean b = m.estatTerminal(t,jugador, profunditat);
        System.out.println("El taulell resultant és estat terminal? " + b);
        System.out.println();
    }
    public static void testMiniMax(int jugador, int profunditat) {

        Pair mm = m.MiniMax(t, jugador, profunditat);
        System.out.println("La peça i la posició calculades pel MiniMax son: " + mm.getFirst() + " " + mm.getSecond());
        System.out.println();

    }
    public static void testvalorMax(int jugador, int profunditat) {

        int vm = m.valorMax(t, jugador, profunditat);
        System.out.println("El valor retornat per la funció valorMax és: " + vm);
        System.out.println();


    }
    public static void testvalorMin(int jugador, int profunditat) {
        int vmin = m.valorMin(t,jugador, profunditat);
        System.out.println("El valor retornat per la funció valorMin és: " + vmin);
        System.out.println();
    }

    public static void main (String [] args) throws Exception{
        int opt = 99;
        while (opt!=0){
            System.out.println("~~~~DRIVER MAQUINA1~~~~");
            System.out.println("Tria una opció:");
            System.out.println("1.TestCalculaMovimentsPossibles");
            System.out.println("2.TestEstatTerminal");
            System.out.println("3.TestValorMax");
            System.out.println("4.TestValorMin");
            System.out.println("5.TestMiniMax");
            System.out.println("6.TestHeuristic1");
            System.out.println("7.TestGetMovimentAlgorisme1");
            System.out.println("8.InicialitzarFEN");
            System.out.println("0.Exit");

            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    Scanner reader = new Scanner(System.in);
                    System.out.println("Escull un color: 1(negres) o 0 (blanques)");
                    int j = reader.nextInt();
                    testcalculaMovimentsPosibles(j);
                    break;
                case 2:
                    Scanner in = new Scanner(System.in);
                    System.out.println("Escull la profunditat (introduïr 0 si es Terminal)");
                    int p = in.nextInt();
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in.nextInt();
                    testestatTerminal(j,p);
                    break;
                case 3:
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("Escull la profunditat (número de moviments que vols fer * 2)");
                    p = in2.nextInt();
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in2.nextInt();
                    testvalorMax(j,p);
                    break;
                case 4:
                    Scanner in3 = new Scanner(System.in);
                    System.out.println("Escull la profunditat (número de moviments que vols fer * 2)");
                    p = in3.nextInt();
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in3.nextInt();
                    testvalorMin(j,p);
                    break;

                case 5:
                    Scanner in4 = new Scanner(System.in);
                    System.out.println("Escull la profunditat (número de moviments que vols fer * 2)");
                    p = in4.nextInt();
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in4.nextInt();
                    testMiniMax(j,p);
                    break;
                case 6:
                    Scanner in5 = new Scanner(System.in);
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in5.nextInt();
                    testHeuristic1(j);
                    break;

                case 7:
                    Scanner in7 = new Scanner(System.in);
                    System.out.println("Escull el jugador (0:blanques, 1:negres)");
                    j = in7.nextInt();
                    System.out.println("Escull la profunditat (número de moviments)");
                    p = in7.nextInt();
                    testgetMovimentAlgorism1(j,p);
                break;

                case 8:
                    Scanner in8 = new Scanner(System.in);
                    System.out.println("Introdueix el FEN");
                    String s = in8.nextLine();
                    t = new Taulell(s);
                    System.out.println();
                    t.mostrarTaulell();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        }
    }

}
