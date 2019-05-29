package src.Presentacio;

import lib.Pair;
import src.Controladors.CtrlDomini;
import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.EventObject;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.border.*;


public class Board {

    private static CtrlPresentacion controladorPresentacion;
    private JFrame f;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private char[][] mchar;
    private String tipusjug;
    private int nmovs;
    private String nomprob;
    private Boolean tornuser=true;
    private int[] vecMov;
    private long taux = 0;

    public Board(CtrlPresentacion c,String nomprob,String tipusjug){

        controladorPresentacion = c;
        this.tipusjug = tipusjug;
        this.nomprob = nomprob;
        nmovs = controladorPresentacion.getMovimentsProblema(nomprob);
        mchar = controladorPresentacion.matriuProblema(nomprob);
        initializeGui();
    }

    public void actualitzaMchar(char[][] mcharx){
        mchar = mcharx;
    }


    public final void initializeGui() {
        // set up the main GUI
        taux = System.nanoTime();
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        final int[] contMovs = {0};
        final int[] posIni = new int[2];
        final int[] posFi = new int[2];

        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        ImageIcon img = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[ii][jj]));
                        chessBoard.add(chessBoardSquares[ii][jj]);
                        if(mchar[ii][jj]!='-') {
                            chessBoardSquares[ii][jj].setIcon(img);
                        }
                        int finalIi = ii;
                        int finalJj = jj;
                        chessBoardSquares[ii][jj].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boolean finalHihapiece = false;
                                if(mchar[finalIi][finalJj]!='-') finalHihapiece = true;
                                if(tornuser && nmovs>0) {//torn jugador atacant
                                    if (!finalHihapiece && contMovs[0] == 0) {//Si no hi ha peça i és el primer click
                                        JOptionPane.showMessageDialog(null, "Selecciona una peça");
                                    }
                                    else if (finalHihapiece && contMovs[0] == 0) {
                                        if (colorPiece(mchar[finalIi][finalJj]) == controladorPresentacion.getAtacantProblema(nomprob)){//Si és blanca
                                            posIni[0] = finalIi;
                                            posIni[1] = finalJj;
                                            contMovs[0]++;
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Selecciona una peça del teu color");
                                        }
                                    }
                                    else if (finalIi == posIni[0] && finalJj == posIni[1]){//Posició final = inicial
                                            JOptionPane.showMessageDialog(null, "Moviment no vàlid. Mou la peça a una posició diferent a la inicial.");
                                            contMovs[0]--;
                                        }
                                    else {
                                        posFi[0] = finalIi;
                                        posFi[1] = finalJj;
                                        try {
                                            if(controladorPresentacion.movimentValid(mchar,posIni,posFi,nomprob)) {
                                                //Actualitzes taulell
                                                tornuser = false;
                                                nmovs--;
                                                contMovs[0]--;
                                                mchar[posFi[0]][posFi[1]] = mchar[posIni[0]][posIni[1]];
                                                mchar[posIni[0]][posIni[1]] = '-';
                                                ImageIcon img3 = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[posFi[0]][posFi[1]]));
                                                chessBoardSquares[posFi[0]][posFi[1]].setIcon(img3);
                                                ImageIcon icon = new ImageIcon(
                                                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                                                chessBoardSquares[posIni[0]][posIni[1]].setIcon(icon);
                                                //el 1 de jaque indica defensor
                                                if(controladorPresentacion.hayJaque(mchar, nomprob, 1))JOptionPane.showMessageDialog(null, "L'atacant fa escac.");
                                                if(nmovs == 0) finalizarpartida();
                                                //TORN DE LA MÀQUINA
                                                if (tipusjug.equals("maquina1") && nmovs > 0 && !tornuser) {
                                                    CalculaM1 m1 = new CalculaM1();
                                                    m1.execute();
                                                }
                                                else if (tipusjug.equals("maquina2") && nmovs > 0 && !tornuser) {
                                                    CalculaM2 m2 = new CalculaM2();
                                                    m2.execute();
                                                }

                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Moviment no vàlid.");
                                                contMovs[0]--;
                                            }
                                        } catch (IncorrectFENException e1) {
                                            e1.printStackTrace();
                                        } catch (Exception e1) {
                                            e1.printStackTrace();
                                        }
                                    }

                                }
                                else if(tipusjug.equals("jugador") && nmovs>0 && !tornuser){
                                    if (!finalHihapiece && contMovs[0] == 0) {//Si no hi ha peça i és el primer click
                                        JOptionPane.showMessageDialog(null, "Selecciona una peça");
                                    }
                                    else if (finalHihapiece && contMovs[0] == 0) {
                                        if (colorPiece(mchar[finalIi][finalJj]) != controladorPresentacion.getAtacantProblema(nomprob)){//Si és blanca
                                            posIni[0] = finalIi;
                                            posIni[1] = finalJj;
                                            contMovs[0]++;
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Selecciona una peça del teu color");
                                        }
                                    }
                                    else if (finalIi == posIni[0] && finalJj == posIni[1]){//Posició final = inicial
                                        JOptionPane.showMessageDialog(null, "Moviment no vàlid. Mou la peça a una posició diferent a la inicial.");
                                        contMovs[0]--;
                                    }
                                    else {
                                        posFi[0] = finalIi;
                                        posFi[1] = finalJj;
                                        try {
                                            if(controladorPresentacion.movimentValid(mchar,posIni,posFi,nomprob)){
                                                //Actualitzes taulell
                                                tornuser = true;
                                                contMovs[0]--;
                                                mchar[posFi[0]][posFi[1]]= mchar[posIni[0]][posIni[1]];
                                                mchar[posIni[0]][posIni[1]] = '-';
                                                ImageIcon img3 = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[posFi[0]][posFi[1]]));
                                                chessBoardSquares[posFi[0]][posFi[1]].setIcon(img3);
                                                ImageIcon icon = new ImageIcon(
                                                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                                                chessBoardSquares[posIni[0]][posIni[1]].setIcon(icon);
                                                if(controladorPresentacion.hayJaque(mchar, nomprob, 0))JOptionPane.showMessageDialog(null, "El defensor fa escac.");

                                            }
                                            else{
                                                JOptionPane.showMessageDialog(null, "Moviment no vàlid.");
                                                contMovs[0]--;
                                            }
                                        } catch (IncorrectFENException e1) {
                                            e1.printStackTrace();
                                        }
                                    }

                                }
                            }
                        });
                }
            }
        }
    }

    private int colorPiece(char c){
        if(c=='p'||c=='n'||c=='b'||c=='r'||c=='q'||c=='k') return 1;
        else return 0;
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }


    public void hacerVisible() {

        f = new JFrame("Jugar Problema");
        f.setPreferredSize(new Dimension(600,600));
        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setResizable(false);
        f.setLocation(450,150);
        // ensures the frame is te minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

    private class CalculaM1 extends SwingWorker<int[],Void>{
        @Override
        protected int[] doInBackground() throws Exception {
            int[] vec = controladorPresentacion.movimentM1(mchar, nmovs, nomprob);
            return vec;
        }

        @Override
        protected void done(){
            try {
                vecMov = get();
                mchar[vecMov[2]][vecMov[3]] = mchar[vecMov[0]][vecMov[1]];
                mchar[vecMov[0]][vecMov[1]] = '-';
                ImageIcon imgx = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[vecMov[2]][vecMov[3]]));
                chessBoardSquares[vecMov[2]][vecMov[3]].setIcon(imgx);
                ImageIcon icon2 = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                chessBoardSquares[vecMov[0]][vecMov[1]].setIcon(icon2);
                if(controladorPresentacion.hayJaque(mchar, nomprob, 0))JOptionPane.showMessageDialog(null, "El defensor fa escac.");
                tornuser = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IncorrectFENException e) {
                e.printStackTrace();
            }
        }
    }

    private class CalculaM2 extends SwingWorker<int[],Void>{
        @Override
        protected int[] doInBackground() throws Exception {
            int[] vec = controladorPresentacion.movimentM2(mchar, nmovs, nomprob);
            return vec;
        }

        @Override
        protected void done(){
            try {
                vecMov = get();
                mchar[vecMov[2]][vecMov[3]] = mchar[vecMov[0]][vecMov[1]];
                mchar[vecMov[0]][vecMov[1]] = '-';
                ImageIcon imgx = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[vecMov[2]][vecMov[3]]));
                chessBoardSquares[vecMov[2]][vecMov[3]].setIcon(imgx);
                ImageIcon icon2 = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                chessBoardSquares[vecMov[0]][vecMov[1]].setIcon(icon2);
                if(controladorPresentacion.hayJaque(mchar, nomprob, 0))JOptionPane.showMessageDialog(null, "El defensor fa escac.");

                tornuser = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IncorrectFENException e) {
                e.printStackTrace();
            }
        }
    }

    private void finalizarpartida() throws Exception {
        taux = System.nanoTime() - taux;
        if(controladorPresentacion.hayJaqueMate(mchar, nomprob, 1)){
            if(tipusjug.equals("jugador"))JOptionPane.showMessageDialog(null, "L'atacant guanya amb escac i mat!.");
            else{
                controladorPresentacion.afegirAlRanking(nomprob,taux);
                JOptionPane.showMessageDialog(null, "L'atacant guanya amb escac i mat!.");
            }

            desactivar();
            controladorPresentacion.cambiarVistaASelecProbJugar();
        }
        else{
            JOptionPane.showMessageDialog(null, "L'atacant no ha fet escac i mat en el numero de moviments establerts");
            desactivar();
            controladorPresentacion.cambiarVistaASelecProbJugar();
        }
    }

    public void desactivar() {
        f.setVisible(false);
        f.setEnabled(false);
    }

}
