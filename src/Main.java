package src;

import lib.Pair;
import src.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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
                    System.out.println("Escull un dels següents problemes per jugar-lo");
                    String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
                    Problema prob1 = new Problema(fen1,2,"Problema 1");
                    prob1.visualitzarProblema();

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
