package src;
import lib.Pair;

public class Problema {
    //ATRIBUTS
    private String nomprob; //nom del problema
    private String FEM; //codificacio en FEM del problema
    private String dificultad; //dificultad asociada al problema
    private Pair<Integer,Integer> tema; /*primer int -> num maximo de jugadas para el jaquemate
                                         segundo int -> jugador que ataca 0 blancas 1 negras*/
    private Boolean validat; //validat o no
    private String creador; //nombre del usuario creador del problema
    Taulell T; //(representa el objeto taulell, matriz de piezas, que tendra el estado inicial del problema)
    //METODES
    public void calcularDificultad() {
        //PRE:
        //POST: calcula la dificultad asociada al problema
    }
    //borrarProblema()
        //PRE: el problema identificado como nomprob existe
        //POST: elimina el problema nomprob de la base de problemas y su ranking asociado
    //modificarProblema()
        //PRE: el problema identificado como nomprob existe
        //POST: modifica la posicion de las piezas, el numero de piezas y el tema
    //validarProblema()
        //PRE: el problema esta siendo creado o modificado
        //POST: verifica que el problema con un determinado tablero, un numero de movimientos N y quien hace el jaque
        // mate tiene solución
    //cargarProblema()
        //PRE: el problema nomprob tiene una partida guardada
        //POST: carga la partida guardada previamente del problema nomprob
}
