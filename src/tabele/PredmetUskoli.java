package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class PredmetUskoli {
    //private static ArrayList<PredmetUskoli> sviPredmetiSkole = new ArrayList<>();
    private static Map<Integer, ArrayList<Object>> sviPredmetiSkole = new Hashtable<>();
    private Predmet predmet;
    private Skola skola;
    private Profesor profesor;
    private int id;

    public PredmetUskoli (Predmet predmet, Skola skola, Profesor profesor, int id){
        this.predmet = predmet;
        this.skola = skola;
        this.profesor = profesor;
        this.id = id;

        sviPredmetiSkole.put(id,new ArrayList<Object>()
            {
                {
                    add(predmet);
                    add(skola);
                    add(profesor);
                }
            }
        );
    }

    public static Map<Integer, ArrayList<Object>> getSviPredmetiSkole() {
        return sviPredmetiSkole;
    }
}
