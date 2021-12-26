package tabele;

import java.util.ArrayList;

public class PredmetUskoli {
    private static ArrayList<PredmetUskoli> sviPredmetiSkole = new ArrayList<>();
    private Predmet predmet;
    private Skola skola;
    private Profesor profesor;

    public PredmetUskoli (Predmet predmet, Skola skola, Profesor profesor){
        this.predmet = predmet;
        this.skola = skola;
        this.profesor = profesor;
    }

}
