import lib.Pair;
import src.IncorrectFENException;
import src.Piece;
import src.Taulell;

import java.util.ArrayList;

public class DriverTaulell {
    public static void testTaulell() throws IncorrectFENException {
        Piece[][] matriu = new Piece[8][8];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = null;
            }
        }
        Taulell t = new Taulell();
        t.carregaFEN("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1");
        Piece[][] mat = t.getTaulell();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]!=null) {
                    System.out.print(mat[i][j].getLletra() + " " + " " + " ");
                }
                else System.out.print(" " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
        String st = t.taulellAFEN();
        System.out.print(st);
    }


    public static void main(String[] args) throws IncorrectFENException {
        testTaulell();
    }

}
