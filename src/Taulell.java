package src;

public class Taulell {
    //ATRIBUTS
    private Peça [][] taulell;
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)
        //i = files, j = columnes
    //MÈTODES
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
    void actualitzarTaulell(Peça p1, pos p){}
        //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
        //POST: s'actualitza la matriu, amb la peça p1 posicionada a p
    public Taulell() {
        matriu = new Peça[8][8];
    }
}
