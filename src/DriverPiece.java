import lib.Pair;
import src.Piece;
import java.util.ArrayList;
public class DriverPiece {
    public void testConstructor(){
        Piece p = new Piece(Piece.PieceColor.White, Piece.TipusPiece.Rook);
    }
    public static void testgetJugador(){
        Piece p = new Piece(Piece.PieceColor.Black, Piece.TipusPiece.Rook);
        System.out.println(p.getJugador());
    }

    public static void testgetTipus() {
        Piece p = new Piece(Piece.PieceColor.White, Piece.TipusPiece.Rook);
        System.out.println(p.getTipus());
    }
    public static void testcalculaMovimentsPiece(){
        Piece[][] matriu = new Piece[8][8];
        Piece p = new Piece(Piece.PieceColor.White, Piece.TipusPiece.King);
        Piece enem = new Piece(Piece.PieceColor.White, Piece.TipusPiece.Pawn);
        matriu[4][3] = p;
        matriu[2k][4] = enem;
        ArrayList<Pair> ar = p.calculaMovimentsPiece(matriu,4,3);
        for(int i = 0; i < ar.size(); ++i){
            Pair pi = ar.get(i);
            Pair p2 = (Pair) pi.getSecond();
            System.out.println("posible pos "+p2.getFirst()+" "+p2.getSecond());
        }
    }
    public static void main (String [] args){
        testgetJugador();
        testgetTipus();
        testcalculaMovimentsPiece();
    }

}
