package src;

public class Peça {
    //ATRIBUTS
    private pair<char,int> id;
        //Pair que conté la informació que identifica a una Peça. El seu tipus (id.first) i un número que
        //diferencia les peces del mateix tipus
    private pair<int,int> pos;
        //Pair que representa la posició i,j on es troba la peça al taulell
    //MÈTODES
    bool posValida(int i,int j){return false};
        //PRE:
        //POST: retorna true si la Peça pot desplaçar-se a la posició i,j, false altrament.

}
