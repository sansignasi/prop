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


}
