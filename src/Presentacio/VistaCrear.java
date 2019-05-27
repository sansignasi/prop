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


public class VistaCrear {

    private static CtrlPresentacion controladorPresentacion;
    private static CtrlDomini ctrlDomini;

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private char[][] mchar = new char[8][8];
    private char piece;
    private JMenuBar menuCrear = new JMenuBar();
    private JMenu menuBlancas = new JMenu("Afegir blanca");
    private JMenu menuNegras = new JMenu("Afegir negra");
    private JButton eliminarPiece = new JButton("Eliminar peça");
    private JButton crearProb = new JButton("Crear problema");
    private JMenuItem peoB = new JMenuItem("Peó");
    private JMenuItem peoN = new JMenuItem("Peó");
    private JMenuItem cavallB = new JMenuItem("Cavall");
    private JMenuItem cavallN = new JMenuItem("Cavall");
    private JMenuItem alfilB = new JMenuItem("Alfil");
    private JMenuItem alfilN = new JMenuItem("Alfil");
    private JMenuItem torreB = new JMenuItem("Torre");
    private JMenuItem torreN = new JMenuItem("Torre");
    private JMenuItem reinaB = new JMenuItem("Reina");
    private JMenuItem reinaN = new JMenuItem("Reina");
    private JMenuItem reiB = new JMenuItem("Rei");
    private JMenuItem reiN = new JMenuItem("Rei");
    private int contp = 0;
    private int contP = 0;
    private int contn = 0;
    private int contN = 0;
    private int contb = 0;
    private int contB = 0;
    private int contr = 0;
    private int contR = 0;
    private int contq = 0;
    private int contQ = 0;
    private int contk = 0;
    private int contK = 0;





    public VistaCrear(CtrlPresentacion c){
        controladorPresentacion = c;
        initializeGui();
    }

    public void actualitzaMchar(char[][] mcharx){
        mchar = mcharx;
    }


    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                mchar[i][j]='-';
            }
        }
        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        asignarListenersComponentes();
        ImageIcon img = new ImageIcon(ChessSprites.SILVER_PAWN);
        Image img2 = img.getImage();
        Image newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        peoB.setIcon(img);
        menuBlancas.add(peoB);
        img = new ImageIcon(ChessSprites.SILVER_KNIGHT);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        cavallB.setIcon(img);
        menuBlancas.add(cavallB);
        img = new ImageIcon(ChessSprites.SILVER_BISHOP);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        alfilB.setIcon(img);
        menuBlancas.add(alfilB);
        img = new ImageIcon(ChessSprites.SILVER_ROOK);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        torreB.setIcon(img);
        menuBlancas.add(torreB);
        img = new ImageIcon(ChessSprites.SILVER_QUEEN);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        reinaB.setIcon(img);
        menuBlancas.add(reinaB);
        img = new ImageIcon(ChessSprites.SILVER_KING);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        reiB.setIcon(img);
        menuBlancas.add(reiB);

        img = new ImageIcon(ChessSprites.GOLD_PAWN);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        peoN.setIcon(img);
        menuNegras.add(peoN);
        img = new ImageIcon(ChessSprites.GOLD_KNIGHT);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        cavallN.setIcon(img);
        menuNegras.add(cavallN);
        img = new ImageIcon(ChessSprites.GOLD_BISHOP);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        alfilN.setIcon(img);
        menuNegras.add(alfilN);
        img = new ImageIcon(ChessSprites.GOLD_ROOK);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        torreN.setIcon(img);
        menuNegras.add(torreN);
        img = new ImageIcon(ChessSprites.GOLD_QUEEN);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        reinaN.setIcon(img);
        menuNegras.add(reinaN);
        img = new ImageIcon(ChessSprites.GOLD_KING);
        img2 = img.getImage();
        newimg = img2.getScaledInstance(23, 23, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        reiN.setIcon(img);
        menuNegras.add(reiN);

        menuCrear.add(menuBlancas);
        menuCrear.add(menuNegras);
        menuCrear.add(eliminarPiece);
        menuCrear.add(crearProb);
        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
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
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                        int finalJj = jj;
                        int finalIi = ii;
                        chessBoardSquares[ii][jj].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mchar[finalIi][finalJj] = piece;
                                ImageIcon img3 = new ImageIcon(ChessSprites.ImatgeDePiece(mchar[finalIi][finalJj]));
                                if(mchar[finalIi][finalJj]!='-'&& pieceMax(piece)) {
                                    chessBoardSquares[finalIi][finalJj].setIcon(img3);
                                }
                                else if(mchar[finalIi][finalJj]=='-'){
                                    ImageIcon icon = new ImageIcon(
                                            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                                    chessBoardSquares[finalIi][finalJj].setIcon(icon);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"No pots posar més peces d'aquest tipus");
                                }
                                System.out.println(finalIi + ", " + finalJj + ": " + mchar[finalIi][finalJj]);
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

    private void asignarListenersComponentes(){
        peoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'P';
            }
        });
        peoN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'p';
            }
        });
        cavallB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'N';
            }
        });
        cavallN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'n';
            }
        });
        alfilB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'B';
            }
        });
        alfilN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'b';
            }
        });
        torreB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'R';
            }
        });
        torreN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'r';
            }
        });
        reinaB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'Q';
            }
        });
        reinaN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'q';
            }
        });
        reiB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'K';
            }
        });
        reiN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piece = 'k';
            }
        });
        eliminarPiece.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(piece=='p'){
                    contp--;
                }
                else if(piece=='P'){
                    contP--;
                }
                else if(piece=='N'){
                    contN--;
                }
                else if(piece=='n'){
                    contn--;
                }
                else if(piece=='b'){
                    contb--;
                }
                else if(piece=='B'){
                    contB--;
                }
                else if(piece=='r'){
                    contr--;
                }
                else if(piece=='R'){
                    contR--;
                }
                else if(piece=='q'){
                    contq--;
                }
                else if(piece=='Q'){
                    contQ--;
                }
                else if(piece=='k'){
                    contk--;
                }
                else if(piece=='K'){
                    contK--;
                }
                piece = '-';
            }
        });
    }

    private boolean pieceMax(char piece){
        boolean res = false;
        if(piece=='p'&&contp<8){
            contp++;
            return true;
        }
        else if(piece=='P'&&contP>7){
            return true;
        }
        else if(piece=='N'&&contN>1){
            return true;
        }
        else if(piece=='n'&&contN>1){
            return true;
        }
        else if(piece=='b'&&contb>1){
            return true;
        }
        else if(piece=='B'&&contB>1){
            return true;
        }
        else if(piece=='r'&&contr>1){
            return true;
        }
        else if(piece=='R'&&contR>1){
            return true;
        }
        else if(piece=='q'&&contq>0){
            return true;
        }
        else if(piece=='Q'&&contQ>0){
            return true;
        }
        else if(piece=='k'&&contK>0){
            return true;
        }
        else if(piece=='K'&&contK>0){
            return true;
        }
        return res;
    }

    public void hacerVisible() {

        JFrame f = new JFrame("Jugar Problema");
        f.setPreferredSize(new Dimension(600, 600));
        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setJMenuBar(menuCrear);
        piece = '-';
        resetConts();
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

    private void resetConts() {
        contp = 0;
        contP = 0;
        contn = 0;
        contN = 0;
        contb = 0;
        contB = 0;
        contr = 0;
        contR = 0;
        contq = 0;
        contQ = 0;
        contk = 0;
        contK = 0;
    }

}
