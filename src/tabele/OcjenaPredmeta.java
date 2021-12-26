package tabele;

import java.util.ArrayList;

public class OcjenaPredmeta {
    private static ArrayList<OcjenaPredmeta> sveOcjenePredmeta = new ArrayList<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private Pitanje pitanje;
    private int ocjena;

    public OcjenaPredmeta (Ucenik ucenik, PredmetUskoli predmet, Pitanje pitanje, int ocjena){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.pitanje = pitanje;
        this.ocjena = ocjena;
    }

}
