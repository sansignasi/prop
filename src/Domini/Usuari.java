package src.Domini;

import lib.Pair;

import java.util.Scanner;

public class Usuari extends Jugador {

    //ATRIBUTS
    private String nomuser;
    private String contraseña;

    //CONSTRUCTORES

    /**
     * Creadora per defecte
     * @param color
     * @param rol
     */
    public Usuari(int color, int rol) {
        super(color,rol);
    }

    /**
     * Creadora de la classe Usuari que crea un Usuari inicialitzant els paràmetres rebuts
     * @param user Represent el nom de l'Usuari
     * @param psw Representa la contrasenya de l'usuari
     */
    public Usuari(String user, String psw){
        super();
        nomuser = user;
        contraseña = psw;
    }

    /**
     * Getter del nom del Jugador
     * @return Retorna un string que representa el nom
     */
    public String getNom(){
        return this.nomuser;
    }

    /**
     * Setter del nom del Jugador
     * @param nomuser S'introdueix el string rebut com a paràmetre com a nom del Jugador
     */
    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    /**
     * Setter de la contrasenya
     * @param contraseña S'introdueix com a contrasenya l'string rebut com a paràmetre
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Getter de la contrasenya
     * @return Retorna un string que representa la contrasenya
     */

    public String getContraseña() {
        return contraseña;
    }

    //MÈTODES

    public Pair jugarTorn(Taulell t,int jugador,int prof){
        boolean ok = false;
        int py = 0;
        int px = 0;
        Scanner sc;
        while(!ok) {
            System.out.println("Escriu quina peça vols moure(ex a1):");
            sc = new Scanner(System.in);
            String inPiece = sc.nextLine();
            py = (int) inPiece.charAt(0) - 'a'; //COLUMNES
            px = Math.abs(((int)inPiece.charAt(1) - '1')-7); //FILES
            if (!(px < 0 || px > 7 || py < 0 || py > 7)) ok = true;
            else System.out.println("Posició incorrecta, torna a intentar-ho");
        }
        if(t.tePiece(px,py)) {
            Piece p = t.getPiece(px, py);
            if (p.getJugador() != super.getColor()) {
                System.out.println("Aquesta peça no es teva");
                Pair posf = new Pair(-1, -1);
                return new Pair(p, posf);
            }
            System.out.println("Escriu on la vols moure(ex a1):");
            sc = new Scanner(System.in);
            String inPos = sc.nextLine();
            int fy = (int) inPos.charAt(0) - 'a'; //COLUMNES
            int fx = Math.abs(((int) inPos.charAt(1) - '1')-7); //FILES
            Pair posf = new Pair(fx, fy);
            return new Pair(p, posf);
        }
        else return null;
    }
}
