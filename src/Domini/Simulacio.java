package src.Domini;

import src.Controladors.CtrlDomini;

import java.util.ArrayList;

public class Simulacio {

    private ArrayList<Problema> problemas;
    private Jugador atacant;
    private Jugador defensor;

    /**
     * Creadora de la classe Simulació
     * @param atac Objecte de la classe Jugador que representa l'atacant
     * @param def Objecte de la classe Jugador que representa el defensor
     * @param probs Problemes que es jugaràn durant la simulació
     */
    public Simulacio(Jugador atac, Jugador def, ArrayList<Problema> probs){
        problemas = probs;
        atacant = atac;
        defensor = def;
    }

    /**
     * Funció que representa la simulació
     * @return Retorna un vector de bools (tants bools com problemes s'han simulat). Si el bool del problema es true es que ha guanyat l'atacant, si es false es que ha perdut
     * @throws IncorrectFENException
     */
    public ArrayList<Boolean> simular() throws IncorrectFENException {
        ArrayList<Boolean> resultat = new ArrayList<>();
        CtrlDomini c = CtrlDomini.getInstance();
        for (Problema problema : problemas) {
            atacant.setColor(problema.getAtacant());
            int x = problema.getAtacant();
            if(x == 1) x = 0;
            else x = 1;
            defensor.setColor(x);
            Partida p = new Partida(problema, atacant, defensor);
            Boolean b = p.jugarPartida();
            resultat.add(b);

        }
        return resultat;
    }
}
