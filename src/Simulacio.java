package src;

import java.util.ArrayList;

public class Simulacio {

    private ArrayList<Problema> problemas;
    private Jugador atacant;
    private Jugador defensor;

    public Simulacio(Jugador atac, Jugador def, ArrayList<Problema> probs){
        problemas = probs;
        atacant = atac;
        defensor = def;
    }
    public ArrayList<Boolean>simular(){ //te devuelve un vec de bools. Tantos bools como problemas, si el bool true ha ganao el atacante, si false ha perdido
        ArrayList<Boolean> resultat = new ArrayList<>();
        for (Problema problema : problemas) {
            atacant.setColor(problema.getAtacant());
            int x = problema.getAtacant();
            if(x == 1) x = 0;
            else x = 1;
            defensor.setColor(x);
            Partida p = new Partida(problema, atacant, defensor);
            resultat.add(p.jugarPartida());
        }
        return resultat;
    }
}
