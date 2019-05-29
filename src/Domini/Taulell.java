package src.Domini;

import lib.Pair;

import java.util.ArrayList;

import static java.lang.StrictMath.sqrt;
import static javax.print.attribute.standard.MediaSizeName.A;

public class Taulell {

    //ATRIBUTS

    private boolean reiblanc;
    private boolean reinegre;

    private Piece [][] matriu = new Piece[8][8];
        //Matriu d'objectes Peça ([i][j] = null si en la posició i,j no hi ha cap Peça)
        //i = files, j = columnes
        //Blanques MAJÚSCULES, Negres minúscules


    //CREADORES

    /**
     * Creadora per defecte de la classe taulell
     */
    public Taulell() {
        this.reiblanc = true;
        this.reinegre = true;
        this.matriu = new Piece[8][8];
        for (int i = 0; i < this.matriu.length; ++i){
            for (int j = 0; j < this.matriu.length; ++j){
                this.matriu[i][j] = null;
            }
        }
    }

    /**
     * Creadora de la classe taulell
     * @param mat Matriu de peces que itroduirem com a atribut matriu de la classe Taulell
     */
    public Taulell(Piece[][] mat){
        {
            this.reiblanc = true;
            this.reinegre = true;
            matriu = mat;
        }
    }

    /**
     * Creadora taulell
     * @param fen Carreguem com a taulell de la classe el taulell resultant de transformar el FEN que s'ha rebut com a paràmetre a un taulell
     * @throws IncorrectFENException
     */
    public Taulell(String fen) throws IncorrectFENException{
        this.reiblanc = true;
        this.reinegre = true;
        carregaFEN(fen);
    }

    //COPIADORES

    /**
     * Funció auxiliar que copia una Piece
     * @param u Piece a copiar
     * @return Retorna una piece idèntica a la Piece rebuda com a paràmetre
     */
    public Piece copiaPiece(Piece u){
        Piece p;
        if (u instanceof Pawn) p = new Pawn(u);
        else if (u instanceof King) p = new King(u);
        else if (u instanceof Knight) p = new Knight(u);
        else if (u instanceof Bishop) p = new Bishop(u);
        else if (u instanceof Queen) p = new Queen(u);
        else p = new Rook(u);
        return p;
    }

    /**
     * Copiem la matriu de l'objecte Taulell rebut com a paràmetre a l'atribut matriu de la classe
     * @param t Taulell a copiar
     */
    public void copiaTaulell(Taulell t){

        Piece[][] k = t.matriu;

        for (int i = 0; i < k.length; ++i){
            for (int j = 0; j < k[0].length; ++j) {

                if(t.tePiece(i,j)) {

                    Piece u = t.getPiece(i,j);
                    Piece p = copiaPiece(u);
                    this.matriu[i][j] = p;
                }
                else this.matriu[i][j] = null;
            }
        }

    }

    //GETTERS

    /**
     * Getter de la matriu de Pieces
     * @return Retorna l'atribut matriu de la classe
     */
    public Piece[][] getTaulell(){
        return matriu;
    }

    /**
     * Getter de la Piece corresponent a la fila i columna j de l'atribut matriu de Pieces
     * @param i Fila
     * @param j Columna
     * @return Es retorna la Piece de la fila i columna j de la matriu de Pieces
     */
    public Piece getPiece(int i, int j){
        return matriu[i][j];
    }
    //SETTERS

    /**
     * Setter que posa el rei del jugador jg a b
     * @param b Posarem el rei a true o false depenent del valor de b
     * @param jg Indica quin serà el rei (blanc o negre) que modificarem
     */
    public void setRei(boolean b, int jg){

        if (jg == 0) this.reiblanc = b;
        else if (jg == 1) this.reinegre = b;
    }

    /**
     * Actualitza el taulell, mou la Piece p1 a la posició posFI
     * @param p1 Piece que mourem (tenim la posició de la Piece dins de l'objecte
     * @param posFi Posició on la mourem
     */
    public void actualitzarTaulell(Piece p1, Pair posFi) {
        //PRE: p1 és una peça de la matriu i p una posició vàlida on es pot moure p1
        //POST: s'actualitza la matriu, amb la peça p1 posicionada a p
        Pair posIni = p1.getPos();

        if(matriu[(int)posFi.getFirst()][(int)posFi.getSecond()]==null){
            Piece aux = copiaPiece(p1);
            matriu[(int)posIni.getFirst()][(int)posIni.getSecond()] = null;
            aux.setPos((int)posFi.getFirst(),(int)posFi.getSecond());
            matriu[(int)posFi.getFirst()][(int)posFi.getSecond()] = aux;


        }
        else{
            Piece aux = copiaPiece(p1);
            matriu[(int)posIni.getFirst()][(int)posIni.getSecond()] = null;
            aux.setPos((int)posFi.getFirst(),(int)posFi.getSecond());
            matriu[(int)posFi.getFirst()][(int)posFi.getSecond()] = aux;

        }

    }

    //MÈTODES

    /**
     * Consultora que retorna un boolea en funció de si a la posició(i,j) de la matriu de Pieces hi ha una Piece o no
     * @param i Indica la fila
     * @param j Indica la columna
     * @return Retorna un booleà que indica si hi ha una Piece a la posició(i,j) de l'atribut matriu de la classe
     */
    public boolean tePiece(int i, int j){
        return (matriu[i][j] != null);
    }

    /**
     * Consultora que retorna un booleà en funció de si el jugador jg del taulell té el rei viu o no
     * @param jg Jugador al que consultarem el seu estat
     * @return Retorna true si té el rei i false en cas contrari
     */
    public boolean teRei(int jg){

        if (jg == 0) return this.reiblanc;
        else return this.reinegre;
    }

    /**
     * Transforma la matriu de Pieces de la classe Taulell a una matriu de chars
     * @return Retorna la matriu de chars equivalent a l'atribut matriu de la classe
     */
    public char[][] matriuChars(){
        char[][] maux = new char[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.getPiece(i,j)==null){
                    maux[i][j] = '-';
                }
                else {
                    maux[i][j] = this.getPiece(i, j).getLletra();
                }
            }
        }
        return maux;
    }

    /**
     * Carrega un FEN i crea un matriu de Pieces a partir d'aquest
     * @param fen String que representa el FEN
     * @throws IncorrectFENException
     */
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

    /**
     * Transforma la matriu de Pieces del Taulell a un string FEN
     * @return Retorna el FEN corresponent a l'estat de la matriu de Pieces del taulell
     */
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
    /**
     * Calcula si el jugador jug està en escac
     * @param jug Jugador que consultarem
     * @return Retorna true si el jugador jug esta en escac i false en cas contrari
     */
    public boolean jaque(int jug) {//jug es el jugador al que le estan haciendo el jaque(0 atacante 1 defensor)
        ArrayList<Pair> posenemy = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (int i = 0; i < 8;++i) {
            for (int j = 0; j < 8; ++j) {
                if (matriu[i][j] != null) { //si hay pieza
                    if (matriu[i][j].getJugador() == jug) { //la pieza es del que defiende
                        if ((matriu[i][j] instanceof King)) { //el rey del defensor
                            x = i;
                            y = j;
                        }
                    } else { //si es enemiga ponemos sus posibles posiciones en el vec posenemy
                        ArrayList<Pair> aux = matriu[i][j].calculaMovimentsPiece(matriu, i, j);
                        posenemy.addAll(aux);
                    }
                }
            }
        }
        boolean amenaza = false;
        for(int i = 0; i < posenemy.size() && !amenaza;++i){
            Pair posenemyaux = posenemy.get(i);
            posenemyaux = (Pair) posenemyaux.getSecond();
            if((int)posenemyaux.getFirst() == x && (int)posenemyaux.getSecond() == y)amenaza = true;
        }
        return amenaza;
    }


    private boolean member(Piece p, ArrayList<Pair> v){
        boolean b = false;
        Pair pos = p.getPos();
        for (int i = 0; i < v.size() && !b; ++i){
            Pair p1 = (Pair)v.get(i).getSecond();
            //System.out.println(p1.getFirst() + " " + p1.getSecond());
            if(pos.getFirst() == p1.getFirst() && pos.getSecond() == p1.getSecond()) b = true;
        }
        return b;
    }

    private boolean intercepted(Pair enemic, Pair rei, ArrayList<Pair> v){

        boolean b = false;

        for (int i = 0; i < v.size() && !b; ++i){

            Pair pos = (Pair)v.get(i).getSecond();

            if(enemic.getFirst() == rei.getFirst()){ //mateixa fila
                if (pos.getFirst() == enemic.getFirst()){
                    if ((Integer)pos.getSecond() > (Integer)enemic.getSecond() && (Integer)pos.getSecond() < (Integer)rei.getSecond()) return true;
                    if ((Integer)pos.getSecond() < (Integer)enemic.getSecond() && (Integer)pos.getSecond() > (Integer)rei.getSecond()) return true;

                }
            }

            if(enemic.getSecond() == rei.getSecond()){ //mateixa columna
                if (pos.getSecond() == enemic.getSecond()){
                    if ((Integer)pos.getFirst() > (Integer)enemic.getFirst() && (Integer)pos.getFirst() < (Integer)rei.getFirst()) return true;
                    if ((Integer)pos.getFirst() < (Integer)enemic.getFirst() && (Integer)pos.getFirst() > (Integer)rei.getFirst()) return true;

                }
            }

        }
        return false;
    }

    /**
     * Calcula si el jugador jug esta en escac i mat
     * @param jug Jugador que consultarem
     * @return Retorna true si esta en escac i mat i false en cas contrari
     */
    public boolean jaquemate(int jug){//jug es el jugador al que le estan haciendo el jaquemate(0 atacante 1 defensor)

        Maquina2 m = new Maquina2();
        ArrayList<Pair> v = m.calculaMovimentsPosibles(this,jug);

        int x = 0;
        int y = 0;
        int poslegales = 0;
        ArrayList<Pair> posrey = new ArrayList<>();
        ArrayList<Pair> posenemy = new ArrayList<>();

        //creem un vector de moviments amb els moviments del rei
        //creem un vector amb tots els possibles moviments de l'enemic

        for (int i = 0; i < 8;++i) {
            for (int j = 0; j < 8; ++j) {
                if (matriu[i][j] != null) { //si hay pieza
                    if (matriu[i][j].getJugador() == jug) { //la pieza es del que defiende
                        if ((matriu[i][j] instanceof King)) { //el rey del defensor
                            x = i;
                            y = j;
                            //System.out.println("soc el rei");
                            posrey = matriu[x][y].calculaMovimentsPiece(matriu,x,y); //calculem moviments del rei i els posem al vector posrey
                            Piece rey = matriu[x][y];
                            Pair pos = new Pair(x,y);
                            Pair aux = new Pair(rey,pos);
                            posrey.add(aux); //afegim la posicio del rei al vector de moviments=
                            //System.out.println("les posicions del rei son " + posrey.size());
                            //for (int i1 = 0; i1 < posrey.size(); ++i1) System.out.println(posrey.get(i1));

                            poslegales = posrey.size(); //posicions legals = posicions que es pot moure el rei
                        }
                    }
                    else { //si es enemiga ponemos sus posibles posiciones en el vec posenemy
                        ArrayList<Pair> aux = matriu[i][j].calculaMovimentsJaqueMate(matriu, i, j);
                        posenemy.addAll(aux);

                    }
                }
            }
        }

         //metemos pos del rey tambien para ver si le hacen jaque

        boolean uncop = false;

        boolean b1;
        boolean b2;
        int cont = 0;

        for (int i = 0; i < posrey.size();++i){ //iterem sobre els possibles moviments del rei

            boolean found = false;
            Pair posreyaux = (Pair) posrey.get(i).getSecond(); //posició del rei

            for (int j = 0; j < posenemy.size() && !found; ++j){ //iterem sobre els moviments dels enemics

                Piece p = (Piece) posenemy.get(j).getFirst();
                Pair posenemyaux = (Pair)posenemy.get(j).getSecond(); //possible posicio de l'enemic


                if(posreyaux.getFirst() == posenemyaux.getFirst() && posreyaux.getSecond() == posenemyaux.getSecond()){ //si son iguals
                    Pair p1 = new Pair(x,y); //posicio del rei original

                    if ((Integer) posreyaux.getFirst() == x && (Integer) posreyaux.getSecond() == y) { //amenaza directa
                            //this.mostrarTaulell();
                            //System.out.println("entro");
                            b1 = intercepted(p.getPos(),p1,v);
                            b2 = member(p,v);

                            if ((!b1 && !b2)||(cont > 0)){
                                found = true;
                                --poslegales;
                            }
                            else if (cont == 0 && (b1 || b2)){
                                ++cont;
                            }
                    }
                    else {
                        found = true;
                        --poslegales;
                    }
                }
            }
        }
        //System.out.println(poslegales);

        if(poslegales == 0) return true;
        else return false;
    }
}