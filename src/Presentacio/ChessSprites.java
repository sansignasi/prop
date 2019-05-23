package src.Presentacio;
import java.awt.image.*;
import javax.imageio.*;
import java.net.*;
import java.io.*;
import java.util.*;

public final class ChessSprites {
    private ChessSprites() {}
    public static final int SIZE = 64;
    public static final BufferedImage SHEET;
    static {
        try {
            // see https://stackoverflow.com/a/19209651/2891664
            SHEET = ImageIO.read(new URL("https://i.stack.imgur.com/memI0.png"));
        } catch (IOException x) {
            throw new UncheckedIOException(x);
        }
    }
    public static final BufferedImage GOLD_QUEEN    = SHEET.getSubimage(0 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_QUEEN  = SHEET.getSubimage(0 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_KING     = SHEET.getSubimage(1 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_KING   = SHEET.getSubimage(1 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_ROOK     = SHEET.getSubimage(2 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_ROOK   = SHEET.getSubimage(2 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_KNIGHT   = SHEET.getSubimage(3 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_KNIGHT = SHEET.getSubimage(3 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_BISHOP   = SHEET.getSubimage(4 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_BISHOP = SHEET.getSubimage(4 * SIZE, SIZE, SIZE, SIZE);
    public static final BufferedImage GOLD_PAWN     = SHEET.getSubimage(5 * SIZE, 0,    SIZE, SIZE);
    public static final BufferedImage SILVER_PAWN   = SHEET.getSubimage(5 * SIZE, SIZE, SIZE, SIZE);
    public static final List<BufferedImage> SPRITES =
            Collections.unmodifiableList(Arrays.asList(GOLD_QUEEN,  SILVER_QUEEN,
                    GOLD_KING,   SILVER_KING,
                    GOLD_ROOK,   SILVER_ROOK,
                    GOLD_KNIGHT, SILVER_KNIGHT,
                    GOLD_BISHOP, SILVER_BISHOP,
                    GOLD_PAWN,   SILVER_PAWN));
    public static final BufferedImage ImatgeDePiece(char c){
        switch (c){
            case 'k':
                return GOLD_KING;
            case 'K':
                return SILVER_KING;
            case 'q':
                return GOLD_QUEEN;
            case 'Q':
                return SILVER_QUEEN;
            case 'r':
                return GOLD_ROOK;
            case 'R':
                return SILVER_ROOK;
            case 'b':
                return GOLD_BISHOP;
            case 'B':
                return SILVER_BISHOP;
            case 'n':
                return GOLD_KNIGHT;
            case 'N':
                return SILVER_KNIGHT;
            case 'p':
                return GOLD_PAWN;
            case 'P':
                return SILVER_PAWN;
            default: return SILVER_PAWN;
        }
    }
}