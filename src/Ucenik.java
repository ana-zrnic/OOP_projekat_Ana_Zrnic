import java.util.ArrayList;

public class Ucenik {
    //private dbTest konekcija = new dbTest();
    private static ArrayList<Ucenik> sviUcenici = new ArrayList<>();
    private String ime, prezime;
    private int pol;
    private final PristupniPodaci pristupniPodaci;

    public Ucenik(String ime, String prezime, int pol, PristupniPodaci pristupniPodaci){
        this.ime = ime;
        this.prezime = prezime;
        this.pristupniPodaci = pristupniPodaci;
        this.pol = pol;
    }

}
