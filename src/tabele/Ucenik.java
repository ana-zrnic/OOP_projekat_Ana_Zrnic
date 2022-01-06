package tabele;

import java.util.ArrayList;

public class Ucenik {
    //private radSaBazom.dbTest konekcija = new radSaBazom.dbTest();
    private static ArrayList<Ucenik> sviUcenici = new ArrayList<>();
    private String ime, prezime;
    private int pol;
    private final PristupniPodaci pristupniPodaci;

    public Ucenik(PristupniPodaci pristupniPodaci, String ime, String prezime, int pol){
        this.ime = ime;
        this.prezime = prezime;
        this.pristupniPodaci = pristupniPodaci;
        this.pol = pol;
    }

}
