package src.Domini;
import lib.Pair;

import java.util.ArrayList;

/** Representa una Chess IA bàsica implementada amb un Minimax amb profunditat limitada i una heurística simple
 * @author Pol Garcia Recasens
 * @author polgarciarecasens@gmail.com
 */
public class Maquina1 extends Maquina{ //Minimax amb profunditat limitada

    //CREADORES
    public Maquina1(){super(); }
    public Maquina1(int color, int rol) {
        super(color,rol);
    }
    public int infinit = 9000000;



    //MÈTODES

    /**
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
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @return Retorna un enter que representa la puntuació amb la que l'Heuristic avalua l'estat del taulell
     */
    public int Heuristic1(Taulell t, int jugador){

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

        //if (t.jaquemate(jugador)) { ret -= 9999; }
        //if (t.jaquemate(Math.abs(jugador-1))) ret += 9999;

        return ret;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jugador Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @return Retorna tots els moviments possibles que podrà realitzar el jugador al que supleix la màquina
     */
    public ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){

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
    public boolean estatTerminal(Taulell t, int jugador, int prf){
        if (! t.teRei(jugador)) return true;
        //if (t.jaquemate(Math.abs(jugador-1))) return true;
        else if (! t.teRei(Math.abs(jugador-1))) return true;
        //else if (t.jaquemate(jugador)) return true;
        else if (prf <= 0) return true;
        else return false;
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param profunditat Indica la profunditat a la que arribarà el Minimax
     * @return Retorna una Piece i la posició on s'haurà de moure
     */
    public Pair MiniMax(Taulell t, int jg, int profunditat){

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
            cmax = valorMin(aux,jg,prf-1);
            if (cmax == infinit) return mov;
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
     * @param jg  Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la màxima puntuació d'entre totes les subbranques que ha visitat
     */
    public int valorMax(Taulell t, int jg, int prf){
        int vmax;

        if (estatTerminal(t,jg,prf)){
            if (!t.teRei(Math.abs(jg-1))) return infinit;
            if (!t.teRei(Math.abs(jg))) return -infinit;
            int x = Heuristic1(t,jg);
            return x;
        }
        else{
            vmax = -99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments
            for (int i=0; i<p.size(); ++i){

                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMin(aux,jg, prf-1);
                vmax = Math.max(vmax,l);
                if (vmax == infinit) return vmax;
            }
            return vmax;
        }
    }

    /**
     *
     * @param t El taulell que representa un taulell d'escacs 8*8 amb les respectives peces
     * @param jg  Indica quin és el jugador al que supleix la màquina (0 blanques, 1 negres)
     * @param prf Indica la profunditat de la iteració actual del Minimax
     * @return Si es un estat terminal retorna la puntuació que li assigna l'heurístic
     * @return Si no es un estat terminal retorna la mínima puntuació d'entre totes les subbranques que ha visitat
     */
    public int valorMin(Taulell t, int jg, int prf){
        int vmin;

        if (estatTerminal(t,jg,prf)){
            if (!t.teRei(Math.abs(jg-1))) return infinit;
            if (!t.teRei(Math.abs(jg))) return -infinit;
            int x = Heuristic1(t,jg);
            return x;
        }
        else{
            vmin = 99999999;
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()) instanceof King && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1);
                vmin = Math.min(vmin,l);
                if (vmin == -infinit) return vmin;

            }
            return vmin;
        }
    }
}
