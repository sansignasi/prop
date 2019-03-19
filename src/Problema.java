package src;

public class Problema {
    //ATRIBUTS
    //string nomprob (nom del problema)
    //string FEM (codificacio en FEM del problema)
    //string dificultad (dificultad asociada al problema)
    //pair(int,int) primer int -> num maximo de jugadas para el jaquemate
    //              segundo int -> jugador que ataca 0 blancas 1 negras
    //bool validat (validat o no)
    //string creador (nombre del usuario creador del problema)
    //Taulell T (representa el objeto taulell, matriz de piezas, que tendra el estado inicial del problema)
    //METODES
    //calcularDificultad()
        //PRE:
        //POST: calcula la dificultad asociada al problema
    //crearProblema()
        //PRE: el problema identificado como nomprob no existe
        //POST: crea el problema nomprob
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
