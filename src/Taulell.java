package src;

import lib.Pair;

public class Taulell {

    //ATRIBUTS

    private Piece [][] matriu = new Piece[8][8];
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)
        //i = files, j = columnes
        //Blanques MAJÚSCULES, Negres minúscules


    //CREADORA
    public Taulell() {
        Piece[][] matriu = new Piece[8][8];
    }

    public Taulell(Piece[][] mat){
        matriu = mat;
    }

    public void copiaTaulell(Taulell t){
        Piece[][] k = t.matriu;
        for (int i = 0; i < k.length; ++i){
            for (int j = 0; j < k[0].length; ++j) {
                if (t.tePiece(i,j)){
                    Piece p = new Piece(k[i][j]);
                    matriu[i][j] = p;
                }
            }
        }
    }

    //GETTERS

    public Piece[][] getTaulell(){
        return matriu;
    }

    public Piece getPiece(int i, int j){
        return matriu[i][j];
    }
    //SETTERS

    public void actualitzarTaulell(Piece p1, Pair posFi) {
        //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
        //POST: s'actualitza la matriu, amb la peça p1 posicionada a p
        Pair posIni = p1.getPos();
        if(matriu[(int)posFi.getFirst()][(int)posFi.getSecond()]==null){
            matriu[(int)posIni.getFirst()][(int)posIni.getSecond()] = null;
            matriu[(int)posFi.getFirst()][(int)posFi.getSecond()] = p1;
            p1.setPos((int)posFi.getFirst(),(int)posFi.getSecond());
            System.out.println((int)posIni.getFirst() + " " + (int)posIni.getSecond());
            System.out.println("hola estic dins d'actualitzar i la posicio anterior es " + matriu[(int)posIni.getFirst()][(int)posIni.getSecond()]);

        }
        else{
            //MENJAR PEÇA, SUMAR PUNTUACIÓ
            matriu[(int)posIni.getFirst()][(int)posIni.getSecond()]=null;
            matriu[(int)posFi.getFirst()][(int)posFi.getSecond()]=p1;
            p1.setPos((int)posFi.getFirst(),(int)posFi.getSecond());
            System.out.println("hola estic dins d'actualitzar matar i la posicio anterior es " + matriu[(int)posIni.getFirst()][(int)posIni.getSecond()]);

        }

    }

    //MÈTODES

    public boolean tePiece(int i, int j){
        return (matriu[i][j] != null);
    }

    public boolean teRei(int jg){

        for (int i = 0; i < matriu.length; ++i){
            for (int j = 0; j < matriu[0].length; ++j){
                if (matriu[i][j].getTipus().equals("King") && matriu[i][j].getJugador() == jg) return true;
            }
        }
        return false;
    }

    public void carregaFEN(String fen) throws IncorrectFENException {
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
                switch (lletra){
                    case 'k':
                    case 'K':
                        matriu[lines][colsY] = new King(color,lines,colsY);
                        break;
                    case 'q':
                    case 'Q':
                        matriu[lines][colsY] = new Queen(color,lines,colsY);
                        break;
                    case 'r':
                    case 'R':
                        matriu[lines][colsY] = new Rook(color,lines,colsY);
                        break;
                    case 'b':
                    case 'B':
                        matriu[lines][colsY] = new Bishop(color,lines,colsY);
                        break;
                    case 'n':
                    case 'N':
                        matriu[lines][colsY] = new Knight(color,lines,colsY);
                        break;
                    case 'p':
                    case 'P':
                        matriu[lines][colsY] = new Pawn(color,lines,colsY);
                        break;
                }
                colsY++;
            }
        }
    }

    public String taulellAFEN() {
        String fen = "";
        // Build the board description:
        for(int i=0; i<8; i++) {
            int emptyCounter = 0;
            for(int j=0;j<8;j++) {
                if(this.matriu[i][j]!=null) {
                    if(emptyCounter!=0) {
                        fen += emptyCounter;
                        emptyCounter = 0;
                    }
                        fen += matriu[i][j].getLletra();

                } else {
                    emptyCounter++;
                }
            }
            if(emptyCounter!=0) {
                fen += emptyCounter;
            }
            if(i!=7) {
                fen += "/";
            }
        }
        return fen;
    }


    void mostrarTaulell() {
        //PRE:
        //POST: mostra l'estat actual de les peces al taulell
        char [][] tprint = new char[9][9];
        for(int i=1;i<9;i++){
            tprint[i][0]=(char)(i+'0');
            tprint[0][i]=(char)(64+i);
        }
        for(int j=1;j<9;j++){
            for(int y=1;y<9;y++){
                if(matriu[j-1][y-1]!=null) {
                    tprint[j][y] = matriu[j - 1][y - 1].getLletra();
                }
            }
        }
        for (int i = 0; i < tprint.length; i++) {
            for (int j = 0; j < tprint[i].length; j++) {
                if (tprint[i][j]!=0) {
                    System.out.print(tprint[i][j] + " " + " " + " ");
                }
                else if(i==0 && j==0) System.out.print(" " + " " + " " + " ");
                else System.out.print("-" + " " + " " + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
