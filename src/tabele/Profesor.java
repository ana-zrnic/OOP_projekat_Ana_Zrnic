package tabele;

import java.util.Hashtable;
import java.util.Map;

public class Profesor {
    //private static ArrayList<Profesor> sviProfesori = new ArrayList<>();
    private static Map<Integer, Profesor> sviProfesori = new Hashtable<>();
    private final PristupniPodaci pristupniPodaci; //?final
    private String ime, prezime;
    private int pol, id;

    public Profesor (PristupniPodaci pristupniPodaci, String ime, String prezime, int pol, int id){
        this.ime = ime;
        this.pol = pol;
        this.prezime = prezime;
        this.id = id;
        this.pristupniPodaci = pristupniPodaci;

        //posto se prvo kreira pristupni podaci i oni su jedinstveni za svakog prof ili ucenika onda nije potrebno provjeravati da li ima isti prof?
        sviProfesori.put(id, this);
    }

    public static Map<Integer, Profesor> getSviProfesori(){
        return sviProfesori;
    }

    public int getId() {
        return id;
    }

    public int getPol() {
        return pol;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public PristupniPodaci getPristupniPodaci(){
        return pristupniPodaci;
    }
}


