package src.Domini;

import lib.Pair;

public class Jugador {

    //ATRIBUTS
    private int color;//0 blancas 1 negras
    private int rol; //0 atacas 1 defiendes

    /**
     * Creadora per defecte de la classe Jugador
     */
    //CREADORES
    public Jugador(){
    }

    /**
     * Creadora de la classe
     * @param c Color
     * @param r Rol
     */
    public Jugador(int c, int r){
        super();
        color = c;
        rol = r;
    }

    //GETTERS

    /**
     * Getter del color
     * @return Retorna el color
     */
    public int getColor(){
        return color;
    }

    /**
     * Getter del rol
     * @return Retorna el rol
     */
    public int getRol(){
        return rol;
    }

    /**
     * Setter del color
     * @param color Introdueix el color coma atribut color
     */
    public void setColor(int color) {
        this.color = color;
    }

    //MÈTODES

    /**
     * Funció abstracta
     * @param t Taulell
     * @param jugador Jugador que mourà peça
     * @param prof Profunditat amb la que calcularem el moviment
     * @return Retorna un parell que representa la Piece que ha de moure i la posició on anirà
     */
    public Pair jugarTorn(Taulell t,int jugador,int prof){
        return null;
    }

    /**
     * Getter del nom
     * @return Retorna el nom
     */
    public String getNom() {
        return null;
    }
}
