package src.Domini;

import lib.Pair;

public class Jugador {

    //ATRIBUTS
    private int color;//0 blancas 1 negras
    private int rol; //0 atacas 1 defiendes

    //CREADORES
    public Jugador(){
    }

    public Jugador(int c, int r){
        super();
        color = c;
        rol = r;
    }

    //GETTERS
    public int getColor(){
        return color;
    }
    public int getRol(){
        return rol;
    }

    public void setColor(int color) {
        this.color = color;
    }

    //MÈTODES
    public Pair jugarTorn(Taulell t,int jugador,int prof){
        return null;
    }

    public String getNom() {
        return null;
    }
}
