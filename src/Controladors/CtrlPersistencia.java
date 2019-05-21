package src.Controladors;

import src.Domini.BaseDeProblemes;
import src.Domini.BaseUsuaris;

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
