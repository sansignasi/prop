/*package src;
import lib.Pair;

import static org.junit.jupiter.api.Assertions.*;

class TaulellTest {


    @org.junit.jupiter.api.Test
    void carregaFEN() throws IncorrectFENException {
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        /*matriu equivalent al FEN = { {null, null, null,new King('b',0,3),null,null,new Queen('w',0,6),null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,new King('w',2,3),null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null}};*//*

        assertNull(t.getTaulell()[0][0]);
        assertNull(t.getTaulell()[5][5]);
        assertEquals(t.getTaulell()[0][3].getTipus(),"King");
        assertEquals(t.getTaulell()[0][3].getColor(),'b');
        assertEquals(t.getTaulell()[0][6].getTipus(),"Queen");
        assertEquals(t.getTaulell()[0][6].getColor(),'w');
        assertEquals(t.getTaulell()[2][3].getTipus(),"King");
        assertEquals(t.getTaulell()[2][3].getColor(),'w');
    }

    @org.junit.jupiter.api.Test
    void copiaPiece() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        Piece px = t.copiaPiece(t.getPiece(0,3));
        assertEquals(px.getTipus(),t.getPiece(0,3).getTipus());
        assertEquals(px.getColor(),t.getPiece(0,3).getColor());
    }

    @org.junit.jupiter.api.Test
    void copiaTaulell() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        Taulell tx = new Taulell();
        tx.copiaTaulell(t);
        assertEquals(tx.getPiece(0,3).getTipus(),t.getPiece(0,3).getTipus());
        assertEquals(tx.getPiece(0,3).getColor(),t.getPiece(0,3).getColor());
    }

    @org.junit.jupiter.api.Test
    void actualitzarTaulell() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        t.actualitzarTaulell(t.getPiece(0,3),new Pair(0,0));
        assertEquals(t.tePiece(0,0),true);
        assertEquals(t.getPiece(0,0).getTipus(),"King");
    }

    @org.junit.jupiter.api.Test
    void tePiece() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        assertEquals(t.tePiece(0,3),true);
        assertEquals(t.tePiece(7,6),false);
    }

    @org.junit.jupiter.api.Test
    void teRei() throws IncorrectFENException {
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3R4/8/8/8/8/8 w - - 0 1");
        assertEquals(t.teRei(1),true);
        assertEquals(t.teRei(0),false);
    }

    @org.junit.jupiter.api.Test
    void taulellAFEN() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        assertEquals(t.taulellAFEN(),"3k2Q1/8/3K4/8/8/8/8/8");
    }

    @org.junit.jupiter.api.Test
    void jaque() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/8/8/8/8/8/8 w - - 0 1");
        assertEquals(t.jaque(1),true);
        assertEquals(t.jaque(0),false);
    }

    @org.junit.jupiter.api.Test
    void jaquemate() throws IncorrectFENException{
        Taulell t = new Taulell();
        t.carregaFEN("3k2Q1/8/3K4/8/8/8/8/8 w - - 0 1");
        assertEquals(t.jaque(1),true);
        assertEquals(t.jaque(0),false);
    }
}
*/