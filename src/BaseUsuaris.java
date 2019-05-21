package src;

import java.util.Map;

public class BaseUsuaris {

    private Map<String,Problema> cjtusuaris;

    private static BaseUsuaris singletonObject;

    public static BaseUsuaris getInstance() {
        if (singletonObject == null)
            singletonObject = new BaseUsuaris() {
            };
        return singletonObject;
    }
}
