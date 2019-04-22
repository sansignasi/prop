package src;

import lib.Pair;

public class Jugador {
    private int color;//0 blancas 1 negras
    private int rol; //0 atacas 1 defiendes

    public Jugador(){
        super();
    }
    public Jugador(int c, int r){
        super();
        color = c;
        rol = r;
    }

    public int getColor(){
        return color;
    }
    public int getRol(){
        return rol;
    }

    public Pair jugarTorn(Taulell t,int jugador,int prof){
        return null;
    }
}
