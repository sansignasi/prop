package src;

import java.util.Map;

public class BaseDeProblemas {
    private Map<String,Problema> cjtproblemas; //string = nombreproblema

    public BaseDeProblemas(){};

    public void afegirProblema(Problema p){
        cjtproblemas.put(p.getNomprob(),p);
    }
    public void eliminarProblema(Problema p){
        cjtproblemas.remove(p.getNomprob());
    }
    public Problema buscarProblema(String s){
        return cjtproblemas.get(s);
    }
}
