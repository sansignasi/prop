package src.Controladors;

import src.Domini.BaseDeProblemes;
import src.Domini.BaseUsuaris;

import java.io.*;
import java.util.Vector;
import java.io.IOException;
import java.nio.*;

/** Representa el Controlador de Persistència del projecte
 * @author Pol Garcia Recasens
 * @author polgarciarecasens@gmail.com
 */
public class CtrlPersistencia {

    private static CtrlPersistencia singletonObject;

    protected static File busuaris;
    protected static File bproblemes;

    protected static FileReader frusers;
    protected static FileWriter fwusers;

    protected static FileReader frproblems;
    protected static FileWriter fwproblems;

    /**
     * Retorna la instància de la classe CtrlPersistencia, com que és un Singleton només n'hi haurà una
     * @return
     */
    public static CtrlPersistencia getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlPersistencia() {
            };
        return singletonObject;
    }

    /**
     * Creadora de la classe Controlador de Persistència
     * Els paths no són concrets per tant són extrapolables
     */
    private CtrlPersistencia(){

        busuaris = new File ("BUsers.txt");
        bproblemes = new File ("BProblems.txt");
        System.out.println("s'ha creat bproblemes i el seu path es " + bproblemes.getAbsolutePath());
        frproblems = null;
        frusers = null;
        fwusers = null;
        fwproblems = null;
    }

    /**
     * Crea un nou File per la Base de Problemes amb el paràmetre path com a path
     * @param path
     * @throws IOException
     */
    public void obreBProblemes(String path) throws IOException {
        bproblemes = new File (path);

        boolean b = bproblemes.createNewFile();

    }

    /**
     * Tanca els FileReaders i FileWriters de Base de Problemes
     * @throws IOException
     */
    public  void tancaBProblemes() throws IOException {
        bproblemes = null;
        if (frproblems != null) frproblems.close();
        if (fwproblems != null) fwproblems.close();
    }

    /**
     * Crea un nou File per la Base d'Usuaris amb paràmetre path com a path
     * @param path
     * @throws IOException
     */
    public void obreBUsuaris(String path) throws IOException {
        busuaris = new File (path);
        if (!busuaris.exists()){
            busuaris.createNewFile();
        }

    }

    /**
     * Tanca els FileReaders i FileWriters de Base d'Usuaris
     * @throws IOException
     */
    public  void tancaBUsuaris() throws IOException {
        busuaris = null;
        if (frusers != null) frusers.close();
        if (fwusers != null) fwusers.close();
    }

    /**
     * Carrega la Base de Problemes com un vector de Strings
     * @return Retorna un vector de Strings que després el Controlador de Domini haurà de transformar en objecte Base de Problemes
     * @throws Exception
     */
    public Vector<String> carregaBProblemes() throws Exception {
        if (bproblemes == null) throw new Exception("Arxiu no obert");
        frproblems = new FileReader(bproblemes);
        BufferedReader in = new BufferedReader(frproblems);
        Vector<String> lineas = new Vector<>();
        String linea;
        while ((linea = in.readLine()) != null) {
            lineas.add(linea);
        }
        in.close();
        return lineas;
    }

    /**
     * Carrega la Base d'Usuaris com un vector de Strings
     * @return Retorna un vector de Strings que després el Controlador de Domini haurà de transformar en objecte Base de Problemes
     * @throws Exception
     */
    public Vector<String> carregaBUsers() throws Exception {
        if (busuaris == null) throw new Exception("Arxiu no obert");
        frusers = new FileReader(busuaris);
        BufferedReader in = new BufferedReader(frusers);
        Vector<String> lineas = new Vector<>();
        String linea;
        while ((linea = in.readLine()) != null) {
            lineas.add(linea);
        }
        in.close();
        return lineas;
    }

    /**
     * Esborra el fitxer que representa la Base d'Usuaris
     * @throws Exception
     */
    public void EsborraBusuaris () throws Exception {
        obreBUsuaris("BUsers.txt");
        fwusers = new FileWriter(busuaris);
        BufferedWriter bw = new BufferedWriter(fwusers);
        bw.write("");
        tancaBUsuaris();
    }

    /**
     * Esborra el fitxer que representa la Base de Problemes
     * @throws Exception
     */
    public void EsborraBProblemes () throws Exception {
        obreBProblemes("BProblems.txt");
        fwproblems = new FileWriter(bproblemes);
        BufferedWriter bw = new BufferedWriter(fwproblems);
        bw.write("");
        tancaBUsuaris();
    }

    /**
     * Escriu un Usuari en el fitxer de la Base d'Usuaris
     * @param s
     * @throws Exception
     */
    public void EscriureUsuari(String s) throws Exception {
        if (busuaris == null) throw new Exception("Arxiu no obert");

        fwusers = new FileWriter(busuaris, true);
        BufferedWriter bw = new BufferedWriter(fwusers);
        PrintWriter out = new PrintWriter(bw);
        out.println(s);
        out.close();
    }

    /**
     * Escriu un Problema en el fitxer de la Base de Problemes
     * @param s
     * @throws Exception
     */
    public void EscriureProblema(String s) throws Exception {
        if (bproblemes == null) throw new Exception("Arxiu no obert");

        fwproblems = new FileWriter(bproblemes, true);
        BufferedWriter bw = new BufferedWriter(fwproblems);
        PrintWriter out = new PrintWriter(bw);
        out.println(s);
        out.close();
    }

    /**
     * Escriu el vector d'Strings passat com a paràmetre que representa la Base de Problemes en un fitxer
     * @param s Vector d'Strings que representa la Base de Problemes
     * @throws Exception
     */
    public void EscriureProblemes(String [] s) throws Exception{

        EsborraBProblemes();
        obreBProblemes("BProblems.txt");
        fwproblems = new FileWriter(bproblemes,true);
        BufferedWriter bw = new BufferedWriter(fwproblems);

        for (int i = 0; i < s.length; ++i){
                EscriureProblema(s[i]);
        }
        tancaBProblemes();

    }

    /**
     * Escriu el vector d'Strings passat com a paràmetre que representa la Base d'Usuaris en un fitxer
     * @param s Vector d'Strings que representa la Base d'Usuaris
     * @throws Exception
     */
    public void EscriureUsuaris(String [] s) throws Exception{

        EsborraBusuaris();
        obreBUsuaris("BUsers.txt");
        fwusers = new FileWriter(busuaris,true);
        BufferedWriter bw = new BufferedWriter(fwusers);

        for (int i = 0; i < s.length; ++i){
            EscriureUsuari(s[i]);
        }
        tancaBUsuaris();
    }

    /**
     * Funció que carrega la Base de Problemes des del fitxer amb path Path com un vector d'Strings
     * @param Path path del File on està emmagatzemada la Base de Problemes
     * @return
     * @throws Exception
     */
    public Vector<String> loadBProblems (String Path) throws Exception{

        obreBProblemes(Path);
        Vector<String> v = carregaBProblemes();
        tancaBProblemes();
        return v;
    }

    /**
     * Funció que carrega la Base d'Usuaris des del fitxer amb path Path com un vector d'Strings
     * @param Path path del File on està emmagatzemada la Base d'Usuaris
     * @return
     * @throws Exception
     */
    public Vector<String> loadBUsers (String Path) throws Exception{

        obreBUsuaris(Path);
        Vector<String> v = carregaBUsers();
        tancaBUsuaris();
        return v;
    }
}
