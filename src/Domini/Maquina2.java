package src.Domini;
import lib.Pair;

import java.util.ArrayList;


public class Maquina2 extends Maquina{ //Minimax amb profunditat limitada

    //CREADORES
    public Maquina2(){super();}
    public Maquina2(int color, int rol) {
        super(color,rol);
    }

    //MÈTODES
    public Pair jugarTorn(Taulell t, int jugador, int profunditat){
        Pair p = MiniMax(t,jugador,profunditat);
        return p;
    }

    private int Heuristic1(Taulell t, int jugador){

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
        return ret;
    }

    public int Heuristic2(Taulell t, int jugador){

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

        ArrayList<Pair> v1 = calculaMovimentsPosibles(t,jugador);
        int ret1 = 0;
        for (int i = 0; i < v1.size(); ++i){
            Pair aux = (Pair)v1.get(i).getSecond();
            if (t.tePiece((int)aux.getFirst(),(int)aux.getSecond())){
                Piece enemic = t.getPiece((int)aux.getFirst(),(int)aux.getSecond());
                ret1 += enemic.getValor();

            }
        }
        ArrayList<Pair> v2 = calculaMovimentsPosibles(t,Math.abs(jugador-1));
        for (int i = 0; i < v2.size(); ++i){
            Pair aux = (Pair)v2.get(i).getSecond();
            if (t.tePiece((int)aux.getFirst(),(int)aux.getSecond())){
                Piece enemic = t.getPiece((int)aux.getFirst(),(int)aux.getSecond());
                ret1 -= enemic.getValor();

            }
        }

        return ret+ret1;
    }

    private ArrayList<Pair> calculaMovimentsPosibles(Taulell t, int jugador){

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

    private boolean estatTerminal(Taulell t, int jugador, int prf){
        if (! t.teRei(jugador)) return true;
        else if (! t.teRei(Math.abs(jugador-1))) return true;
        else if (prf <= 0) return true;
        else return false;
    }

    private Pair MiniMax(Taulell t, int jg, int profunditat){

        int max,cmax; //puntuacio de la heurística
        max = -99999999;
        Pair movret = new Pair(0,0);
        ArrayList<Pair> p = calculaMovimentsPosibles(t,jg); //no retorna un enter, retorna un conjunt de moviments

        for (int i=0; i<p.size();++i) {
            //System.out.println("estem dins de minimax i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
            Pair mov = p.get(i);
            Taulell aux = new Taulell();
            aux.copiaTaulell(t);
            int prf = profunditat;
            Pair po = (Pair) p.get(i).getSecond();
            if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
            else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
            aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
            cmax = valorMin(aux,jg,prf-1,-100000000,100000000);

            if (cmax > max){
                max = cmax;
                movret = mov;
            }
        }
        return movret;
    }

    private int valorMin(Taulell t, int jg, int prf, int a, int b){

        if (estatTerminal(t,jg,prf)){
            int x = Heuristic1(t,jg);
            //System.out.println("El valor del heuristic es " + x );
            return x;
        }
        else{
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                //System.out.println("estem dins de valormin i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1,a,b);
                b = Math.min(b,l);
                if (a >= b) return a;
            }
            return b;
        }
    }

    private int valorMax(Taulell t, int jg, int prf, int a, int b){

        if (estatTerminal(t,jg,prf)){
            int x = Heuristic1(t,jg);
            //System.out.println("El valor del heuristic es " + x );
            return x;
        }
        else{
            ArrayList<Pair> p = calculaMovimentsPosibles(t,Math.abs(jg-1));
            for (int i=0; i<p.size(); ++i){
                //System.out.println("estem dins de valormin i el moviment es " + p.get(i).getFirst() + " " + p.get(i).getSecond());
                Taulell aux = new Taulell();
                aux.copiaTaulell(t);
                Pair po = (Pair) p.get(i).getSecond();
                if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'w') aux.setRei(false,0);
                else if (t.tePiece((int)po.getFirst(),(int)po.getSecond()) && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getTipus() == "King" && t.getPiece((int)po.getFirst(),(int)po.getSecond()).getColor() == 'b') aux.setRei(false,1);
                aux.actualitzarTaulell((Piece)p.get(i).getFirst(),(Pair)p.get(i).getSecond());
                int l = valorMax(aux,jg, prf-1,a,b);
                a = Math.max(a,l);
                if (a >= b) return b;
            }
            return b;
        }
    }

}
