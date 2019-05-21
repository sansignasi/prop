package src;
import java.util.ArrayList;
import java.util.Scanner;

public class CtrlPersistencia {


    private static CtrlPersistencia singletonObject;

    public static CtrlPersistencia getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlPersistencia() {
            };
        return singletonObject;
    }

    public BaseDeProblemes loadBProblemes(){
        return null;

    }

    public BaseUsuaris loadBUsers(){
        return null;
    }


}
