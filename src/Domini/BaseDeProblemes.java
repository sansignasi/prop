package src.Domini;

import java.util.*;

/** Representa la Base de Problemes
 */
public class BaseDeProblemes {//hola

    private TreeMap<String,Problema> cjtproblemes = new TreeMap<>();

    private static BaseDeProblemes singletonObject;

    /**
     * Retorna una instància de la classe Base de Problemes, com que és un Singleton només n'hi haurà una
     * @return una instància de la classe BaseDeProblemes
     */
    public static BaseDeProblemes getInstance() {

        if (singletonObject == null)
            singletonObject = new BaseDeProblemes() {
            };
        return singletonObject;
    }

    /**
     * Getter de cjtproblemes
     * @return Retorna el TreeMap cjtproblemes
     */
    public TreeMap getMap(){
        return cjtproblemes;
    }

    /**
     * Setter de cjtproblemes
     * @param m TreeMap que assignarem a cjtproblemes
     */
    public void setMap (TreeMap<String,Problema> m){
        cjtproblemes = m;
    }

    /**
     * Afegeix un problema a la Base de Problemes
     * @param p Afegeix el problema p al TreeMap cjtproblemes
     */
    public void afegirProblema(Problema p){

        cjtproblemes.put(p.getNomprob(),p);
    }

    /**
     * Precondicio: el problema p existeix a la Base de Problemes
     * Elimina un problema p de la Base de Problemes
     * @param p Elimina el problema p del TreeMap cjtproblemes
     */
    public void eliminarProblema(Problema p){

        cjtproblemes.remove(p.getNomprob());
    }

    /**
     * Retorna un boolea que indica si existeix un problema amb nom s a la Base de Problemes
     * @param s Buscarem al TreeMap cjtproblemes un Problema amb key s
     * @return Retorna true si l'ha trobat, false en cas contrari
     */
    public boolean existeixProblema(String s){

        return cjtproblemes.containsKey(s);
    }

    /**
     * Precondicio: el problema amb nom S existeix
     * Retorna un problema amb nom s
     * @param s key que buscarem en el TreeMap cjtproblemes
     * @return Retorna el problema p
     */
    public Problema buscarProblema(String s){

        return cjtproblemes.get(s);
    }

    /**
     * Retorna un set amb tots els noms dels problemes de la Base de Problemes
     * @return retorna el keyset associat al TreeMap cjtproblemes
     */
    public Set<String> getNomProblemes(){
        return cjtproblemes.keySet();
    }

    /**
     * Getter dels noms dels problemes que ha jugat un usuari
     * @param currentuser Usuari que consultarem
     * @return Retorna un vector de Strings amb els noms dels problemes
     */

    public  ArrayList<String> getNomProblemesUsuari(String currentuser) {
        ArrayList<String> probs = new ArrayList<>();
        Set keys = cjtproblemes.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            Problema value = cjtproblemes.get(key);
            if(value.getCreador().equals(currentuser)) probs.add(key);
        }
        return probs;

    }

    /**
     * Consultara que retorna un boolèa en funció de si existeix un problema com el del input
     * @param fen Fen que consultarem
     * @param nmovs Moviments que consultarem
     * @return Retorna true si existeix un problema amb FEN fen i moviments nmovs
     */
    public boolean existeixFENambNmovs(String fen, int nmovs) {
        Set keys = cjtproblemes.keySet();
        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            Problema value = cjtproblemes.get(key);
            if(value.getFEN().equals(fen) && value.getMoviments()==nmovs) return true;
        }
        return false;
    }
}
