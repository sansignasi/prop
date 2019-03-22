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
    private int valor;
        //Valor de la peça

    public int getPuntuacio(){return (this).valor;}
    //MÈTODES
    public Peça(){

    }

    public Peça(ColorPeça c, TipusPeça t){
        this.ColorPeça = c;
        this.TipusPeça = t;
    }
    void calculaValorPeça(){
        if(this.tipus==TipusPeça.Queen){this.valor=9}
        if(this.tipus==TipusPeça.Rook){this.valor=5}
        if(this.tipus==TipusPeça.Bishop){this.valor=3}
        if(this.tipus==TipusPeça.Knight){this.valor=3}
        if(this.tipus==TipusPeça.Pawn){this.valor=1}
    }
boolean        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició p, false altrament.

}
