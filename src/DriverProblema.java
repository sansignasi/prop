package src;

import lib.Pair;
import java.util.ArrayList;
import java.util.Scanner;

import src.Maquina1;
import src.Piece;
import src.Taulell;

public class DriverProblema {

    public static Problema p = new Problema();

    //es pot treballar amb aquesta matriu de prova o es pot carregar un taulell amb un fen directament

    public static Piece[][] matriu = { {null, null, null,null,new King('b',0,4),null,null,null}, //R,-,K,-,-,B,-,R
            {new Pawn('b',1,0), new Pawn('b',1,1), null,null,null,null,null,null}, //Pe,Pe,-,-,-,-,-,-
            {new Pawn('w',2,0), null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            {null,null,new Bishop('b',5,2),null,null,null,null,null},
            {null,null,null,new King('w',6,3),null,null,null,null},
            {null,null,null,null,null,null,null,null}};


    public static Taulell t = new Taulell(matriu);

    public static void testValidariOptimitzarProblema(int jugador, int profunditat) {
        int n = p.validariOptimitzarProblema(t,jugador,profunditat*2);
        if (n == -1) System.out.println("El problema no es pot resoldre en " + profunditat + " moviments. i la n es " + n);
        else System.out.println("El problema es pot resoldre en " + n + " moviments.");

    }

    public static void testValidarProblema(int jugador, int profunditat) {
        boolean b = p.validarProblema(t,jugador,profunditat*2);
        //System.out.println("la n es " + n);
        if (!b) System.out.println("El problema no es pot resoldre");
        else System.out.println("El problema es pot resoldre en " + profunditat + " moviments.");

    }

    public static void testestatTerminal(int jugador, int profunditat) {

        boolean b = p.estatTerminal(t,jugador, profunditat);
        System.out.println("El taulell resultant és estat terminal? " + b);
        System.out.println();
    }

    public static void testMiniMax(int jugador, int profunditat) {

        int mm = p.MiniMaxOptim(t, jugador, profunditat);
        System.out.println("Els moviments minims trobats pel MiniMax son: " + mm);
        System.out.println();

    }
    public static void testvalorMax(int jugador, int profunditat) {

        int vm = p.valorMax(t, jugador, profunditat);
        System.out.println("El valor retornat per la funció valorMax és: " + vm);
        System.out.println();


    }
    public static void testvalorMin(int jugador, int profunditat) {
        int vmin = p.valorMin(t,jugador, profunditat);
        System.out.println("El valor retornat per la funció valorMin és: " + vmin);
        System.out.println();


    }

    public static void main (String [] args) throws IncorrectFENException{
        int opt = 99;
        while (opt!=0){
            System.out.println("~~~~DRIVER PROBLEMA~~~~");
            System.out.println("Tria una opció:");
            System.out.println("1.TestValidariOptimitzarProblema");
            System.out.println("2.TestEstatTerminal");
            System.out.println("3.TestValorMax");
            System.out.println("4.TestValorMin");
            System.out.println("5.TestMiniMax");
            System.out.println("6.TestValidarProblema");
            System.out.println("7.TestCalcularAtacant");
            System.out.println("8.TestVisualitzarProblema");

            System.out.println("0.Exit");

            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    Scanner in5 = new Scanner(System.in);
                    System.out.println("Escull els moviments");
                    int p = in5.nextInt();
                    System.out.println("Escull el jugador");
                    int j = in5.nextInt();
                    testValidariOptimitzarProblema(j,p);
                    break;

                case 2:
                    Scanner in = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in.nextInt();
                    System.out.println("Escull el jugador");
                    j = in.nextInt();
                    testestatTerminal(j,p);
                    break;
                case 3:
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in2.nextInt();
                    System.out.println("Escull el jugador");
                    j = in2.nextInt();
                    testvalorMax(j,p);
                    break;
                case 4:
                    Scanner in3 = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in3.nextInt();
                    System.out.println("Escull el jugador");
                    j = in3.nextInt();
                    testvalorMin(j,p);
                    break;

                case 5:
                    Scanner in4 = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in4.nextInt();
                    System.out.println("Escull el jugador");
                    j = in4.nextInt();
                    testMiniMax(j,p);
                    break;

                case 6:
                    Scanner in6 = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in6.nextInt();
                    System.out.println("Escull el jugador");
                    j = in6.nextInt();
                    testValidarProblema(j,p);
                    break;

                case 7:
                    Scanner in7 = new Scanner(System.in);
                    System.out.println("Escull ");
                    p = in7.nextInt();
                    System.out.println("Escull el jugador");
                    j = in7.nextInt();
                    testValidarProblema(j,p);

                    break;
                case 8:
                    Scanner in8 = new Scanner(System.in);
                    System.out.println("Escull la profunditat");
                    p = in8.nextInt();
                    System.out.println("Escull el jugador");
                    j = in8.nextInt();
                    testValidarProblema(j,p);
                    break;

                case 0:
                    System.out.println("Exit");
                    break;
            }
        }
    }

}
