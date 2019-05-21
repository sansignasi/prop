package src;

import lib.Pair;

import java.util.Scanner;

public class Usuari extends Jugador{

    //ATRIBUTS
    private String nomuser;
    private String contraseña;

    //CONSTRUCTORES
    public Usuari(int color, int rol) {
        super(color,rol);
    }

    public Usuari(String user, String psw){
        super();
        nomuser = user;
        contraseña = psw;
    }

    public String getNom(){
        return this.nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNomuser() {
        return nomuser;
    }

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
