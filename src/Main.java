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
                    System.out.println("Escull un dels següents problemes per jugar-lo:");
                    System.out.println();
                    String fen1 = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
                    Problema prob1 = new Problema(fen1,2,"Problema 1, mat de blanques en 2");
                    prob1.visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    //Problema 2
                    String fen2 = "5r2/1pR3Bn/1KP1k3/2P1p3/1p3pP1/5Q2/3Pp2p/1B1b4 w - - 0 1";
                    Problema prob2 = new Problema(fen2,2,"Problema 2, mat de blanques en 2");
                    prob2.visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    //Problema 3
                    String fen3 = "B6K/B1N5/2np3p/2r2n1Q/1p2k3/1P2PNP1/4P3/8 w - - 0 1";
                    Problema prob3 = new Problema(fen3,2,"Problema 3, mat de blanques en 2");
                    prob3.visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    System.out.println("Escull un dels problemes mostrats per jugar-lo:");
                    int inprob = sc.nextInt();
                    System.out.println("Qui comença atacant? Opció 1: Usuari, Opció 2: M1");
                    int j1 = sc.nextInt();
                    System.out.println("Qui defensa? Opció 1: Usuari, Opció 2: M1");
                    int j2 = sc.nextInt();

                    //Crida a Partida
                    Problema prob = new Problema();
                    Jugador jug1 = new Jugador();
                    Jugador jug2 = new Jugador();
                    //Problema:
                    if(inprob==1){
                        prob = new Problema(fen1,2,"Problema 1, mat de blanques en 2");
                    }
                    else if(inprob==2){
                        prob = new Problema(fen2,2,"Problema 2, mat de blanques en 2");
                    }
                    else if(inprob==3){
                        prob = new Problema(fen3,2,"Problema 3, mat de blanques en 2");
                    }
                    else{
                        System.out.println("Jugador erroni");
                    }
                    //Jugador 1:
                    if(j1==1){
                        jug1 = new Usuari(0,0);
                    }
                    else if(j1==2){
                        jug1 = new Maquina1(0,0);
                    }
                    //Jugador 2:
                    if(j2==1){
                        jug2 = new Usuari(1,1);
                    }
                    else if(j2==2){
                        jug2 = new Maquina1(1,1);
                    }
                    Partida game = new Partida(prob,jug1,jug2);
                    game.jugarPartida();


                    break;
                case 2:
                    String f = "1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1";
                    Problema p = new Problema(f,2,"Mat de blanques en 2");
                    p.visualitzaProblema();
                    System.out.println();
                    System.out.println();
                    int n = p.validarProblema(p.getTaulell(),p.getAtacant(),4);
                    System.out.println("la n es " + n);
                    if (n == -1) System.out.println("El problema no es pot resoldre en " + p.getMoviments() + " moviments. i la n es " + n);
                    else System.out.println("El problema es pot resoldre en " + (p.getMoviments()*2 - n) + " moviments.");
                    break;
                case 3:
                    //testMostraTaulell();
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
            }
            System.out.println();
        }
    }
}