package tabele;

import java.util.ArrayList;

public class Izostanci {
    private static ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
    private Ucenik ucenik;
    private PredmetUskoli predmet;
    private int id; //???
    private String datum;

    public Izostanci (Ucenik ucenik, PredmetUskoli predmet, String datum){
        this.ucenik = ucenik;
        this.predmet = predmet;
        this.datum = datum;
    }


}
