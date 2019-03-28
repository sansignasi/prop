import lib.Pair;
import src.Piece;
import src.Taulell;

import java.util.ArrayList;

public class DriverTaulell {
    public static void testTaulell(){
        Piece[][] matriu = new Piece[8][8];
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = new Piece('w','k');
            }
        }
        Taulell t = new Taulell(matriu);
        Piece[][] mat = t.getTaulell();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j].getLletra() + " " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
        String st = t
    }


    public static void main(String[] args){
        testTaulell();
    }

}
