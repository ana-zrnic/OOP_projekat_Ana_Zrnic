package tabele;

import java.util.ArrayList;

public class Ocjena {
    private static ArrayList<Ocjena> sveOcjene = new ArrayList<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private int ocjena;
    private String datum;

    public Ocjena(Ucenik ucenik, PredmetUskoli predmet, int ocjena, String datum){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.ocjena = ocjena;
        this.datum = datum;
    }
}
