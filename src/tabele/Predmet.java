package tabele;

import java.util.ArrayList;

public class Predmet {
    private static ArrayList<Predmet> sviPredmeti = new ArrayList<>();
    private String naziv;
    private int razred;

    public Predmet (String naziv, int razred){
        this.naziv = naziv;
        this.razred = razred;
    }
}
