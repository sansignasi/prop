package src.Domini;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.text.*;
import java.util.Set;

public class Ranking {
    HashMap<String,Double> rk = new HashMap<String,Double>();

    /**
     * Creadora per defecte de la classe Ranking
     */
    public Ranking(){
    }

    /**
     * Funció que introdueix una nova entrada al Ranking
     * @param s String que representa un usuari que ha jugat el problema
     * @param i Double que representa la puntuació que ha fet l'usurari
     */
    public void introRank(String s, Double i){
        boolean b = rk.containsKey(s);
        double tsecs = (double) i / 1_000_000_000;
        tsecs = (double)Math.round(tsecs * 100d) / 100d;
        if(b){
            if(rk.get(s)>tsecs){
                rk.put(s,tsecs);
            }
        }
        else{
            rk.put(s,tsecs);
        }
    }

    /**
     * Funció estàtica que retorna el HashMap  rebut com a paràmetre ordenat per el temps (valor del map)
     * @param map Map que volem ordenar
     * @return Retorna un Hashmap equivalent al map rebut com a paràmetre ordenat per el segon valor
     */
    private static HashMap sortPerValors(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public void eliminarRanking(){
        rk.clear();
    }

    /**
     * Getter del st
     * @return
     */
    public String getStringRanking() {
        HashMap h = sortPerValors(rk);
        StringBuilder sb = new StringBuilder();
        Iterator iter = h.entrySet().iterator();
        int cont = 1;
        while (iter.hasNext()){
            HashMap.Entry entry = (HashMap.Entry) iter.next();
            sb.append(cont);
            sb.append(". ");
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append(" segons\n");
            cont++;
        }
        return sb.toString();
    }
}
