package src.Domini;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BaseUsuaris {

    private TreeMap<String,Usuari> cjtusuaris;

    private static BaseUsuaris singletonObject;

    public static BaseUsuaris getInstance() {
        if (singletonObject == null)
            singletonObject = new BaseUsuaris() {
            };
        return singletonObject;
    }

    public TreeMap getMap(){
        return cjtusuaris;
    }

    public void setMap (TreeMap<String,Usuari> m){
        cjtusuaris = m;
    }

    public int verificarusuari(String user, String psw){//si existe "user" con pswrd "psw" retirna un 0, si esta el user pero mala psw retorna 1, si no existe ese user retorna 2
        if(!cjtusuaris.containsKey(user)) return 2;
        else if (cjtusuaris.get(user).getContraseña().equals(psw)) return 0;
        else return 1;

    }

    public void afegirusuari(Usuari u){
        cjtusuaris.put(u.getNom(),u);
    }
}
