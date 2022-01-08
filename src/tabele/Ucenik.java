package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Ucenik {
    //private radSaBazom.dbTest konekcija = new radSaBazom.dbTest();
    //private static ArrayList<Ucenik> sviUcenici = new ArrayList<>();
    private static Map<Integer, Ucenik> sviUcenici = new Hashtable<>();
    private String ime, prezime;
    private int pol, id;
    private final PristupniPodaci pristupniPodaci;

    public Ucenik(PristupniPodaci pristupniPodaci, String ime, String prezime, int pol, int id){
        this.ime = ime;
        this.prezime = prezime;
        this.pristupniPodaci = pristupniPodaci;
        this.pol = pol;
        this.id = id;
        //posto se prvo kreira pristupni podaci i oni su jedinstveni za svakog prof ili ucenika onda nije potrebno provjeravati da li ima isti prof?
        sviUcenici.put(id, this);
    }

    public static Map<Integer, Ucenik> getSviUcenici(){
        return sviUcenici;
    }
}
