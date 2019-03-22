package src;

import java.awt.*;

public class Peça {

    public enum ColorPeça{
        Black,
        White
    }

    public enum TipusPeça{
        King,
        Queen,
        Rook,
        Bishop,
        Knight,
        Pawn,
        None
    }




    //ATRIBUTS
    private ColorPeça color;
        //Color de la peça (de la enum)
    private TipusPeça tipus;
        //Tipus de peça (de la enum)


    //MÈTODES
    public Peça(){

    }

    public Peça(ColorPeça c, TipusPeça t){
        this.ColorPeça = c;
        this.TipusPeça = t;
    }
    bool posValida(int p){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.

}
