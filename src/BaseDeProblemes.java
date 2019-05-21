package src;

import java.util.Map;
import java.util.HashMap;


public class BaseDeProblemes {

    private Map<String,Problema> cjtproblemes;

    private static BaseDeProblemes singletonObject;

    public static BaseDeProblemes getInstance() {
        if (singletonObject == null)
            singletonObject = new BaseDeProblemes() {
            };
        return singletonObject;
    }

    private BaseDeProblemes() {
        this.cjtproblemes = new HashMap<String,Problema>();
    }
    public void afegirProblema(Problema p){

        cjtproblemes.put(p.getNomprob(),p);
    }
    public void eliminarProblema(Problema p){

        cjtproblemes.remove(p.getNomprob());
    }

    public boolean existeixProblema(String s){
        return cjtproblemes.containsKey(s);
    }
    public Problema buscarProblema(String s){

        return cjtproblemes.get(s);
    }
}
