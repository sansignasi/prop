package src.Presentacio;

import src.Controladors.CtrlDomini;
import src.Controladors.CtrlPresentacion;
import src.Domini.IncorrectFENException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Board {

    private static CtrlPresentacion controladorPresentacion;
    private static CtrlDomini ctrlDomini;

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";
    private char[][] mchar;

    public Board(CtrlPresentacion c,String nomprob){
        controladorPresentacion = c;
        mchar = controladorPresentacion.matriuProblema(nomprob);
        initializeGui();
    }

    public void actualitzaMchar(char[][] mcharx){
        mchar = mcharx;
    }


    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO - add functionality!
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

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
                        chessBoard.add(chessBoardSquares[jj][ii]);
                        boolean hihapiece = false;
                        if(mchar[ii][jj]!='-') {
                            chessBoardSquares[jj][ii].setIcon(img);
                            hihapiece = true;
                        }
                        int finalIi = ii;
                        int finalJj = jj;
                        boolean finalHihapiece = hihapiece;
                        chessBoardSquares[jj][ii].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(!finalHihapiece && contMovs[0] ==0){
                                    JOptionPane.showMessageDialog(null,"Selecciona una peça");
                                }
                                else if(finalHihapiece && contMovs[0] ==0){
                                    posIni[0] = finalIi;
                                    posIni[1] = finalJj;
                                    contMovs[0]++;
                                }
                                else if(!(finalIi == posIni[0] && finalJj == posIni[1])){
                                    posFi[0] = finalIi;
                                    posFi[1] = finalJj;
                                    contMovs[0]--;
                                    System.out.println(posIni[0] + "" + posIni[1]);
                                    System.out.println(posFi[0] + "" + posFi[1]);
                                    controladorPresentacion.movimentUsuari(posIni[0],posIni[1],posFi[0],posFi[1]);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Moviment no vàlid. Mou la peça a una posició diferent a la inicial.");
                                    contMovs[0]--;
                                }

                            }
                        });
                }
            }
        }
    }

    public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return gui;
    }

    public void hacerVisible() {

        JFrame f = new JFrame("Jugar Problema");
        f.setPreferredSize(new Dimension(600,600));
        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

}
