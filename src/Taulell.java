package src;

import lib.Pair;

public class Taulell {
    //ATRIBUTS
    private Peça [] matriu;
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)
        //i = files, j = columnes
    //MÈTODES
    void inicialitzaTaulell(String FEN){
        //PRE: matriu està buida
        //POST: s'inicialitza el taulell amb les peces de cada jugador a la posició del problema
        int index = 0;
        for (int i = 0; i<FEN.length (); i++) {
            char c = FEN.charAt (i);
            if (index < 64 && c != ' ') {
                if (c == '1' && index < 63)
                {
                    index++;
                }
                else if (c == '2' && index < 62)
                {
                    index += 2;
                }
                else if (c == '3' && index < 61)
                {
                    index += 3;
                }
                else if (c == '4' && index < 60)
                {
                    index += 4;
                }
                else if (c == '5' && index < 59)
                {
                    index += 5;
                }
                else if (c == '6' && index < 58)
                {
                    index += 6;
                }
                else if (c == '7' && index < 57)
                {
                    index += 7;
                }
                else if (c == '8' && index < 56)
                {
                    index += 8;
                }
                else if (c == 'P')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.Pawn);
                    index++;
                }
                else if (c == 'N')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.Knight);
                    index++;
                }
                else if (c == 'B')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.Bishop);
                    index++;
                }
                else if (c == 'R')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.Rook);
                    index++;
                }
                else if (c == 'Q')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.Queen);
                    index++;
                }
                else if (c == 'K')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.White, Peça.TipusPeça.King);
                    index++;
                }
                else if (c == 'p')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.Pawn);
                    index++;
                }
                else if (c == 'n')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.Knight);
                    index++;
                }
                else if (c == 'b')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.Bishop);
                    index++;
                }
                else if (c == 'r')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.Rook);
                    index++;
                }
                else if (c == 'q')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.Queen);
                    index++;
                }
                else if (c == 'k')
                {
                    matriu[index] = new Peça(Peça.ColorPeça.Black, Peça.TipusPeça.King);
                    index++;
                }
                else if (c == '/')
                {
                    continue;
                }
            }
        }

    }
    void mostrarTaulell() {}
        //PRE:
        //POST: mostra l'estat actual de les peces al taulell
    void actualitzarTaulell(Peça p1, pos p){}
        //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
        //POST: s'actualitza la matriu, amb la peça p1 posicionada a p
    public Taulell() {
        matriu = new Peça[8][8];
    }

    public boolean teRei(int jug){
        Jugador j = getJugador(jug);

        for (int i=0; i<)
    }
}
