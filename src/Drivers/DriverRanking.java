package src.Drivers;

import src.*;

import java.util.Scanner;

public class DriverRanking {

    public static void testjugarPartida(Problema p, Jugador j1, Jugador j2) throws IncorrectFENException {
        Problema prox = new Problema("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1",2,"p1");
        BaseDeProblemes bp = BaseDeProblemes.getInstance();
        if(!bp.existeixProblema("p1")) {
            bp.afegirProblema(prox);
        }
        Partida game = new Partida(bp.buscarProblema("p1"),j1,j2);
        game.jugarPartida();
        bp.buscarProblema("p1").restoreTaulell();
    }


    public static void main(String[] args) throws IncorrectFENException {
        int opt = 99;
        while (opt != 0) {
            System.out.println("~~~~DRIVER PARTIDA~~~~");
            System.out.println("Tria una opció:");
            System.out.println("1.Test Jugar Partida");
            System.out.println("0.Exit");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    Scanner s = new Scanner(System.in);
                    System.out.println("Introdueix un FEN:");
                    String fen2 = s.nextLine();
                    System.out.println("Escriu el nombre de moviments per guanyar.");
                    System.out.println();
                    Scanner scF = new Scanner(System.in);
                    int nmovs = scF.nextInt();
                    Problema prob = new Problema(fen2, nmovs, "Problema Test");
                    System.out.println("Es jugarà aquest problema");
                    prob.visualitzaProblema();
                    Jugador jug1 = new Jugador();
                    Jugador jug2 = new Jugador();
                    System.out.println("Qui comença atacant? Opció 1: Usuari, Opció 2: M1");
                    int j1 = sc.nextInt();
                    System.out.println("Qui defensa? Opció 1: Usuari, Opció 2: M1");
                    int j2 = sc.nextInt();
                    if(j1==1)jug1 = new Usuari(0,0);
                    else jug1 = new Maquina1(0,0);
                    if(j2==1)jug2 = new Usuari(1,1);
                    else if(j2==2) jug2 = new Maquina1(1,1);
                    testjugarPartida(prob,jug1,jug2);

                case 0:
                    System.out.println("Exit.");
                    break;
            }
        }
    }
}
