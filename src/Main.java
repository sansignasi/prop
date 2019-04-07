package src;

import lib.Pair;
import src.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt!=0){
            System.out.println("Tria una opció:");
            System.out.println("1.Jugar problema");
            System.out.println("2.Validar problema");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt){
                case 1:
                    //Problema 1
                    System.out.println("Escull un dels següents problemes per jugar-lo");
                    String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
                    Problema prob1 = new Problema(fen1,2,"Problema 1, mat de blanques en 2");
                    prob1.visualitzarProblema();
                    //Problema 2
                    String fen2 = "5r2/1pR3Bn/1KP1k3/2P1p3/1p3pP1/5Q2/3Pp2p/1B1b4 w - - 0 1";
                    Problema prob2 = new Problema(fen2,2,"Problema 2, mat de blanques en 2");
                    prob2.visualitzarProblema();
                    //Problema 3
                    String fen3 = "B6K/B1N5/2np3p/2r2n1Q/1p2k3/1P2PNP1/4P3/8 w - - 0 1";
                    Problema prob3 = new Problema(fen3,2,"Problema 3, mat de blanques en 2");
                    prob3.visualitzarProblema();
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
}
