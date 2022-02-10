package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class OcjenaPredmeta {
    //private static ArrayList<OcjenaPredmeta> sveOcjenePredmeta = new ArrayList<>();
    private static Map<Integer, OcjenaPredmeta> sveOcjenePredmeta = new Hashtable<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private Pitanje pitanje;
    private int ocjena, id;

    public OcjenaPredmeta (Ucenik ucenik, PredmetUskoli predmet, Pitanje pitanje, int ocjena, int id){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.pitanje = pitanje;
        this.ocjena = ocjena;
        this.id = id;

        sveOcjenePredmeta.put(id,this);
    }

    public int getId() {
        return id;
    }

    public PredmetUskoli getPredmet() {
        return predmet;
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public int getOcjena() {
        return ocjena;
    }

    public static Map<Integer, OcjenaPredmeta> getSveOcjenePredmeta() {
        return sveOcjenePredmeta;
    }

    public Pitanje getPitanje() {
        return pitanje;
    }
}
