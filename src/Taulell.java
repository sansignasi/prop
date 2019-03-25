package src;

import lib.Pair;

public class Taulell {

    //ATRIBUTS

    private Piece [][] matriu;
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)
        //i = files, j = columnes


    //CREADORA
    public Taulell() {
        Piece[][] matriu = new Piece[8][8];
    }

    //GETTERS

    public Piece[][] getTaulell(){
        return matriu;
    }

    public Piece getPiece(int i, int j){
        return matriu[i][j];
    }
    //SETTERS

    public void actualitzarTaulell(Piece p1, Pair p){}
    //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
    //POST: s'actualitza la matriu, amb la peça p1 posicionada a p

    //MÈTODES

    public boolean tePiece(int i, int j){
        if (matriu[i][j] == null) return false;
        else return true;
    }

    public boolean teRei(int jg){

        for (int i = 0; i < matriu.length; ++i){
            for (int j = 0; j < matriu[0].length; ++j){
                if (matriu[i][j].getTipus() == "King" && matriu[i][j].getJugador() == jg) return true;
            }
        }
        return false;
    }

    void carregaFEN(String fen) throws IncorrectFENException {
        for (int i = 0; i < matriu.length; ++i) {
            for (int j = 0; j < matriu[0].length; ++j) {
                matriu[i][j] = null;
            }
        }

        // Split the FEN with whitespaces:
        String[] fenArray = fen.split(" ");
        if (fenArray.length < 4) {
            throw new IncorrectFENException("FEN incorrecte.");
        }

        // Parse the board description:
        String[] taulellArray = fenArray[0].split("/");
        if (taulellArray.length != 8) {
            throw new IncorrectFENException("Representació FEN del taulell incorrecte.");
        }

        for (int lines = 0; lines < 8; lines++) {
            String line = taulellArray[lines];
            int colsY = 0;
            for (int cols = 0; cols < line.length(); cols++) {
                char lletra = line.charAt(cols);
                char color;
                if (String.valueOf(lletra).matches("[rbqkpn]")) {
                    color = 'b';
                } else if (String.valueOf(lletra).matches("[RBQKPN]")) {
                    color = 'w';
                } else {
                    try {
                        colsY = colsY + Integer.parseInt(String.valueOf(lletra));
                    } catch (NumberFormatException e) {
                        throw new IncorrectFENException("Representació FEN del taulell incorrecte.");
                    }
                    continue;
                }
                int x = colsY;
                int y = lines;
                matriu[lines][colsY] = new Piece(color,lletra);
                colsY++;
            }
        }

        // Jugador que comença a moure (atacant)
        if (fenArray[1].equals("b")) {
            Problema.setAtacant('b');
        } else if (fenArray[1].equals("w")) {
            Problema.setAtacant('w');
        } else {
            throw new IncorrectFENException("Color del jugador atacant incorrecte.");
        }
    }

    void mostrarTaulell() {}
        //PRE:
        //POST: mostra l'estat actual de les peces al taulell




}
