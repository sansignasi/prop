package src.Presentacio;

import src.Controladors.CtrlDomini;
import src.Controladors.CtrlPresentacion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class BoardPreview {

    private static CtrlPresentacion controladorPresentacion;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;

    private static final String COLS = "ABCDEFGH";
    private char[][] mchar;

    public BoardPreview(CtrlPresentacion c,String nomprob){
        controladorPresentacion = c;
        mchar = controladorPresentacion.matriuProblema(nomprob);
        /*
        for(int i = 0; i < mchar.length;++i){
            System.out.println("");
            for(int j = 0; j < mchar.length;++j){
                System.out.print("."+mchar[i][j]+".");
            }
        }*/
        initializeGui();
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBackground(Color.decode("0x857454"));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setBorderPainted(false);
                b.setFocusPainted(false);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.decode("0xB5A254"));
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

        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (8 - ii),
                                SwingConstants.CENTER));
                    default:
                        ImageIcon img = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[ii][jj]));
                        Image img2 = img.getImage();
                        Image newimg = img2.getScaledInstance( 27, 27,  java.awt.Image.SCALE_SMOOTH ) ;
                        img = new ImageIcon( newimg );
                        chessBoard.add(chessBoardSquares[jj][ii]);
                        if(mchar[ii][jj]!='-') {
                            chessBoardSquares[jj][ii].setIcon(img);
                        }
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

        JFrame f = new JFrame("Preview");
        f.setPreferredSize(new Dimension(300,300));
        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setResizable(false);
        f.setLocation(600,320);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

}
