package src.Domini;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;


public class BaseDeProblemes {

    private TreeMap<String,Problema> cjtproblemes = new TreeMap<String,Problema>();

    private static BaseDeProblemes singletonObject;

    public static BaseDeProblemes getInstance() {

        if (singletonObject == null)
            singletonObject = new BaseDeProblemes() {
            };
        return singletonObject;
    }

    private BaseDeProblemes() {

        this.cjtproblemes = new TreeMap<String,Problema>();
    }

    public TreeMap getMap(){
        return cjtproblemes;
    }

    public void setMap (TreeMap<String,Problema> m){
        cjtproblemes = m;
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
