package tabele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Izostanci {
    //private static ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
    private static Map<Integer, Izostanci> sviIzostanci = new Hashtable<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private int id;
    private LocalDate datum;

    public Izostanci (Ucenik ucenik, PredmetUskoli predmet, String datum, int id){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.datum = LocalDate.parse(datum);
        this.id = id;

        sviIzostanci.put(id,this);
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public PredmetUskoli getPredmet() {
        return predmet;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public static Map<Integer, Izostanci> getSviIzostanci() {
        return sviIzostanci;
    }
}
