package src.Domini;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Representa la Base d'Usuaris
 */
public class BaseUsuaris {

    private TreeMap<String,Usuari> cjtusuaris = new TreeMap<String,Usuari>();

    private static BaseUsuaris singletonObject;

    /**
        * Retorna una instància de la classe BaseUsuaris, com que és un Singleton només n'hi haurà una
        * @return una instància de la classe BaseUsuaris
     */
    public static BaseUsuaris getInstance() {
        if (singletonObject == null)
            singletonObject = new BaseUsuaris() {
            };
        return singletonObject;
    }

    /**
     * Creadora de la classe
     */
    private BaseUsuaris(){
        this.cjtusuaris = new TreeMap<String,Usuari>();
    }

    /**
     * Getter de cjtusuaris
     * @return Retorna el TreeMap cjtusuaris
     */
    public TreeMap getMap(){
        return cjtusuaris;
    }

    /**
     * Setter de cjtusuaris
     * @param m Assigna el TreeMap m a cjtusuaris
     */
    public void setMap (TreeMap<String,Usuari> m){
        cjtusuaris = m;
    }

    /**
     * Funcio que verifica un usuari amb nom user
     * @param user String que representa el nom de l'usuari
     * @param psw String que representa la contrasenya de l'usuari
     * @return Retorna 0 si existeix un usuari user amb contrasenya psw, 1 si existeix un usuari user però no té contrasenya psw i 2 si no existeix un usuari user
     */
    public int verificarusuari(String user, String psw){//si existe "user" con pswrd "psw" retirna un 0, si esta el user pero mala psw retorna 1, si no existe ese user retorna 2
        if(!cjtusuaris.containsKey(user)) return 2;
        else if (cjtusuaris.get(user).getContraseña().equals(psw)) return 0;
        else return 1;

    }

    /**
     * Precondicio: l'usuari amb nom s ja existeix
     * Funcio que retorna un Usuari amb nom s
     * @param s Representa el nom de l'usuari
     * @return Retorna un Usuari
     */
    public Usuari buscarUsuari(String s){

        return cjtusuaris.get(s);
    }

    /**
     * Afegeix un Usuari u a la Base d'Usuaris
     * @param u Representa l'Usuari u que afegirem al TreeMap cjtusuaris
     */
    public void afegirusuari(Usuari u){
        cjtusuaris.put(u.getNom(),u);
    }
}
