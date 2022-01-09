package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class PredmetUskoli {
    //private static ArrayList<PredmetUskoli> sviPredmetiSkole = new ArrayList<>();
    private static Map<Integer, PredmetUskoli> sviPredmetiSkole = new Hashtable<>();
    private Predmet predmet;
    private Skola skola;
    private Profesor profesor;
    private int id;

    public PredmetUskoli (Predmet predmet, Skola skola, Profesor profesor, int id){
        this.predmet = predmet;
        this.skola = skola;
        this.profesor = profesor;
        this.id = id;

        sviPredmetiSkole.put(id, this);
    }

    public static Map<Integer, PredmetUskoli> getSviPredmetiSkole() {
        return sviPredmetiSkole;
    }

    public int getId() {
        return id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public Skola getSkola() {
        return skola;
    }
}
