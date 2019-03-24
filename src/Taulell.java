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
        for (char keyVar : this.squares.keySet()) {
            for (int j = 1; j <= 8; j++) {
                this.squares.get(keyVar)[j].piece = null;
            }
        }
        this.pieces = new LinkedList<BoardPiece>();

        // Split the FEN with whitespaces:
        String[] fenArray = fen.split(" ");
        if (fenArray.length < 4) {
            throw new IncorrectFENException("Number of argument incorrect in the FEN.");
        }

        // Parse the board description:
        String[] boardArray = fenArray[0].split("/");
        if (boardArray.length != 8) {
            throw new IncorrectFENException("Board representation incorrect in the FEN.");
        }

        for (int lines = 1; lines <= 8; lines++) {
            String line = boardArray[lines - 1];
            int colsY = 1;
            for (int cols = 0; cols < line.length(); cols++) {
                char letter = line.charAt(cols);
                PieceColor color;
                if (String.valueOf(letter).matches("[rbqkpn]")) {
                    color = PieceColor.BLACK;
                } else if (String.valueOf(letter).matches("[RBQKPN]")) {
                    color = PieceColor.WHITE;
                } else {
                    try {
                        colsY = colsY + Integer.parseInt(String.valueOf(letter));
                    } catch (NumberFormatException e) {
                        throw new IncorrectFENException("Board representation incorrect in the FEN.");
                    }
                    continue;
                }
                PieceType name = PieceType.getType(letter);
                char x = letters[colsY];
                int y = numbers[lines];
                this.addPiece(name, color, x, y);
                colsY++;
            }
        }

        // Parse the color of the player to move next:
        if (fenArray[1].equals("b")) {
            this.currentMove = PieceColor.BLACK;
        } else if (fenArray[1].equals("w")) {
            this.currentMove = PieceColor.WHITE;
        } else {
            throw new IncorrectFENException("Color of the player to move next incorrect.");
        }
    }

    void mostrarTaulell() {}
        //PRE:
        //POST: mostra l'estat actual de les peces al taulell




}
