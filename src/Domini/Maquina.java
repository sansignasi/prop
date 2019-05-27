package src.Domini;

import lib.Pair;

public class Maquina extends Jugador {

    /**
     * Creadpra per defecte de la Classe Màquina
     */
    public Maquina(){super();}

    /**
     * Creadora
     * @param color Color
     * @param rol Rol
     */
    public Maquina(int color, int rol) {
        super(color,rol);
    }

    /**
     * Funció abstracta que es redefinirà a les subclasses
     * @param t Taulell
     * @param jugador Jugador que mourà peça
     * @param profunditat a la que arribarà l'algorisme
     * @return Retorna un parell que indica la Piece que es moure i la posició on anirà
     */
    public Pair jugarTorn(Taulell t, int jugador, int profunditat){ return null;}

}
