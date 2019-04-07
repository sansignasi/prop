package src;
import lib.Pair;

public class Problema {
    //ATRIBUTS
    private String nomprob; //nom del problema
    private String FEN; //codificacio en FEN del problema
    private String dificultad; //dificultad asociada al problema
    private int nMax; // num maximo de jugadas para el jaquemate
    private int atacant; // jugador que comença i ataca, b black, w white
    private Boolean validat; //validat o no
    private String creador; //nombre del usuario creador del problema
   private Taulell T; //(representa el objeto taulell, matriz de piezas, que tendra el estado inicial del problema)
    //METODES


    public Problema(String s, int n, String nom) throws IncorrectFENException {
        FEN = s;
        nMax = n;
        T = new Taulell(s);
        nomprob = nom;
        calcularDificultad();
    }

    public void calcularatacant() {
        String[] fenArray = FEN.split(" ");
        if (fenArray[1].equals("w")) atacant = 0;
        else atacant = 1;
    }

    public void visualitzaProblema(){
        System.out.println(nomprob);
        System.out.println();
        System.out.println("Dificultad: "+dificultad);
        System.out.println();
        System.out.println();
        T.mostrarTaulell();
    }

    public void calcularDificultad() {
        //PRE:
        //POST: calcula la dificultad asociada al problema
        this.dificultad = "Mas dificil que el final de BD";
    }

    public int getMoviments(){
        return nMax;
    }
    public int getAtacant(){
        return atacant;
    }
    public Taulell getTaulell(){
        return T;
    }
    /*// Jugador que comença a moure (atacant)
        if (fenArray[1].equals("b")) {
        Problema.setAtacant('b');
    } else if (fenArray[1].equals("w")) {
        Problema.setAtacant('w');
    } else {
        throw new IncorrectFENException("Color del jugador atacant incorrecte.");
    }*/

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
