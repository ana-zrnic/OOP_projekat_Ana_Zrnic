import java.util.ArrayList;

public class PristupniPodaci {
    private static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
    private final String korisnickoIme, email;
    private String sifra;

    public PristupniPodaci (String korisnickoIme, String email){
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.sifra = korisnickoIme+"123";
    }
}
