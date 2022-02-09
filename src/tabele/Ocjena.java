package tabele;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class Ocjena {
    //private static ArrayList<Ocjena> sveOcjene = new ArrayList<>();
    private static Map<Integer, Ocjena> sveOcjene = new Hashtable<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private int ocjena, id;
    private LocalDate datum;

    public Ocjena(Ucenik ucenik, PredmetUskoli predmet, int ocjena, String datum, int id){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.ocjena = ocjena;
        this.datum = LocalDate.parse(datum);
        this.id = id;

        sveOcjene.put(id,this);
    }

    public int getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public int getOcjena() {
        return ocjena;
    }

    public static Map<Integer, Ocjena> getSveOcjene() {
        return sveOcjene;
    }

    public PredmetUskoli getPredmet() {
        return predmet;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }
}
