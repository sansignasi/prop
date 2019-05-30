package src.Domini;
import lib.Pair;

import java.util.ArrayList;

/** Representa una Chess IA implementada amb un Minimax amb poda alpha beta i una heurística més complerta
 * @author Pol Garcia Recasens
 * @author polgarciarecasens@gmail.com
 */
public class Maquina2 extends Maquina{ //Minimax amb profunditat limitada

    //CREADORES
    public Maquina2(){super();}
    public Maquina2(int color, int rol) {
        super(color,rol);
    }

    //MÈTODES

    /**
     * Funció que calcula quina Piece i a quina posició anirà segons la Màquina2
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param profunditat Profunditat, tenint en compte atacant i defensor, a la que arribarà el Minimax
     * @return Retorna una Piece i la posició on s'haurà de moure
     */
    public Pair jugarTorn(Taulell t, int jugador, int profunditat){
        Pair p = MiniMax(t,jugador,profunditat);
        return p;
    }

    /**
     * Funció que avalua el taulell en funció de la puntuació de les peces i de la seva mobilitat
     * @param t Taulell a avaluar
     * @param jugador Jugador que ataca (tindrà el valor positiu, l'oponent negatiu)
     * @return Retorna un enter que representa la puntuació de l'heurístic
     */
    private int Heuristic2(Taulell t, int jugador){

        //CONTEM LES PECES PER PUNTUACIÓ

        Piece[][] p = t.getTaulell();

        int ret = 0;

        for (int i=0; i< p.length; ++i) {
            for (int j = 0; j < p[0].length; ++j) {

                Piece p1 = t.getPiece(i,j);
                if(t.tePiece(i,j)){
                    if (p1.getJugador()==jugador) {
                        Piece pC = t.getPiece(i,j);
                        ret += pC.getValor();
                    }
                    else {
                        Piece pC = t.getPiece(i,j);
                        ret -= pC.getValor();
                    }
                }
            }
        }


        ArrayList<Pair> k = calculaMovimentsPosibles(t,jugador);
        ret += 0.5*k.size();

        k = calculaMovimentsPosibles(t,Math.abs(jugador-1));
        ret -= 0.5*k.size();

       return ret;
    }

    /**
     * Funció que calcula els moviments possibles que es poden realitzar amb les peces
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @return Retorna tots els moviments possibles que podrà realitzar el jugador al que supleix la màquina
     */
    private static ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){

        ArrayList<Pair> a = new ArrayList<>();
        Piece[][] m = t.getTaulell();

        for (int i=0; i< m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j){
                if (t.tePiece(i,j) && m[i][j].getJugador() == jugador){
                    ArrayList<Pair> aux = m[i][j].calculaMovimentsPiece(m,i,j);
                    a.addAll(aux);
                }
            }
        }

        return a;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Retorna un boolea que indica si s'ha arribat a un estat terminal o no
     */
    private boolean estatTerminal(Taulell t, int jugador, int prf){
        if (! t.teRei(jugador)) return true;
        else if (! t.teRei(Math.abs(jugador-1))) return true;
        else if (prf <= 0) return true;
        else return false;
    }

    /**
     * Funció Minimax que simula una IA i calcula quina es la millor Piece que s'ha de moure
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param profunditat Indica la profunditat a la que arribarà el Minimax
     * @return Retorna una Piece i la posició on s'haurà de moure
     */
    private Pair MiniMax(Taulell t, int jg, int profunditat){

        int max,cmax; //puntuacio de la heurística
        max = -99999999;
        Pair movret = new Pair(0,0);
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments

        for (int i=0; i<p.size();++i) {
            Pair mov = p.get(i);
            Taulell aux = new Taulell();
            aux.copiaTaulell(t);
            int prf = profunditat;
            Pair po = (Pair) p.get(i).getSecond();
            if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
            else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(aux,jg,prf-1,-100000000,100000000);

            if (cmax > max){
                max = cmax;
                movret = mov;
            }
        }
        return movret;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @param a Indica el valor alpha
     * @param b Indica el valor beta
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la mínima puntuació d'entre totes les subbranques que ha visitat
     */
    private int valorMin(Taulell t, int jg, int prf, int a, int b){

        if (estatTerminal(t,jg,prf)){
            int x = Heuristic2(t,jg);
            return x;
        }
        else{
            int a1,b1;
            a1 = a;
            b1 = b;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1,a1,b1);
                b1 = Math.min(b1,l);
                if (a1 >= b1) return a1;
            }
            return b1;
        }
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @param a Indica el valor alpha
     * @param b Indica el valor beta
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la màxima puntuació d'entre totes les subbranques que ha visitat
     */
    private int valorMax(Taulell t, int jg, int prf, int a, int b){

        if (estatTerminal(t,jg,prf)){
            int x = Heuristic2(t,jg);
            return x;
        }
        else{
            int a1 = a;
            int b1 = b;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg);
            for (int i=0; i<p.size(); ++i){
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1,a1,b1);
                a1 = Math.max(a1,l);
                if (a1 >= b1) return b1;
            }
            return a1;
        }
    }

}
