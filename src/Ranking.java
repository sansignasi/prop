package src;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ranking {
    HashMap<String,Integer> rk = new HashMap<String,Integer>();

    public Ranking(){
    }

    public void introRank(String s, Integer i){
        boolean b = rk.containsKey(s);
        if(b){
            if(rk.get(s)>i){
                rk.put(s,i);
            }
        }
        else{
            rk.put(s,i);
        }
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

    public static void main(String[] args) {
        Problema p = new Problema();
    }
}
