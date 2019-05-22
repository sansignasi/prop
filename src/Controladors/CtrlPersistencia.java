package src.Controladors;

import src.Domini.BaseDeProblemes;
import src.Domini.BaseUsuaris;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Vector;

public class CtrlPersistencia {

    private static CtrlPersistencia singletonObject;

    protected static File busuaris;
    protected static File bproblemes;

    protected static FileReader frusers;
    protected static FileWriter fwusers;

    protected static FileReader frproblems;
    protected static FileWriter fwproblems;

    public static CtrlPersistencia getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlPersistencia() {
            };
        return singletonObject;
    }

    private CtrlPersistencia(){

        busuaris = new File (".dades/BUsers.txt");
        bproblemes = new File (".dades/BProblemes.txt");
        frproblems = null;
        frusers = null;
        fwusers = null;
        fwproblems = null;
    }

    public void obreBProblemes(String path) throws IOException {
        bproblemes = new File (path);
        if (!bproblemes.exists()){
            bproblemes.createNewFile();
        }

    }

    public  void tancaBProblemes() throws IOException {
        bproblemes = null;
        if (frproblems != null) frproblems.close();
        if (fwproblems != null) fwproblems.close();
    }

    public void obreBUsuaris(String path) throws IOException {
        busuaris = new File (path);
        if (!busuaris.exists()){
            busuaris.createNewFile();
        }

    }

    public  void tancaBUsuaris() throws IOException {
        busuaris = null;
        if (frusers != null) frusers.close();
        if (fwusers != null) fwusers.close();
    }

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

    public void EsborraBusuaris () throws Exception {
        obreBUsuaris("dades/BUsers.txt");
        fwusers = new FileWriter(busuaris);
        BufferedWriter bw = new BufferedWriter(fwusers);
        bw.write("");
        tancaBUsuaris();
    }

    public void EsborraBProblemes () throws Exception {
        obreBProblemes("dades/BProblems.txt");
        fwproblems = new FileWriter(bproblemes);
        BufferedWriter bw = new BufferedWriter(fwproblems);
        bw.write("");
        tancaBUsuaris();
    }

    public void EscriureUsuari(String s) throws Exception {
        if (busuaris == null) throw new Exception("Arxiu no obert");

        fwusers = new FileWriter(busuaris, true);
        BufferedWriter bw = new BufferedWriter(fwusers);
        PrintWriter out = new PrintWriter(bw);
        out.println(s);
        out.close();
    }

    public void EscriureProblema(String s) throws Exception {
        if (busuaris == null) throw new Exception("Arxiu no obert");

        fwusers = new FileWriter(busuaris, true);
        BufferedWriter bw = new BufferedWriter(fwusers);
        PrintWriter out = new PrintWriter(bw);
        out.println(s);
        out.close();
    }

    public void EscriureProblemes(String [] s) throws Exception{

        EsborraBProblemes();
        obreBProblemes("dades/BProblems.txt");
        fwproblems = new FileWriter(bproblemes,true);
        BufferedWriter bw = new BufferedWriter(fwproblems);

        for (int i = 0; i < s.length; ++i){
                EscriureProblema(s[i]);
        }
        tancaBProblemes();

    }

    public void EscriureUsuaris(String [] s) throws Exception{

        EsborraBusuaris();
        obreBUsuaris("dades/BUsers.txt");
        fwusers = new FileWriter(busuaris,true);
        BufferedWriter bw = new BufferedWriter(fwusers);

        for (int i = 0; i < s.length; ++i){
            EscriureUsuari(s[i]);
        }
        tancaBUsuaris();

    }

    public Vector<String> loadBProblems (String Path) throws Exception{

        obreBProblemes(Path);
        Vector<String> v = carregaBProblemes();
        tancaBProblemes();
        return v;
    }

    public Vector<String> loadBUsers (String Path) throws Exception{

        obreBUsuaris(Path);
        Vector<String> v = carregaBUsers();
        tancaBUsuaris();
        return v;
    }
}
