package src;

public class Taulell {
    //ATRIBUTS
    private Peça [][] matriu;
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)

    //MÈTODES
    void mostrarTaulell() {}
        //PRE:
        //POST: mostra l'estat actual de les peces al taulell
    void actualitzarTaulell(Peça p1, pos p){}
        //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
        //POST: s'actualitza la matriu, amb la peça p1 posicionada a p
    public Taulell() {
        matriu = new Peça[8][8];
    }
}
