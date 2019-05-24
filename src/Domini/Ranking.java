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

    public Ranking(){
    }

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


    public void mostraRanking(){
        System.out.println(Collections.singletonList(rk));
    }

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
}
