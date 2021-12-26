package tabele;

import java.util.ArrayList;

public class Profesor {
    private static ArrayList<Profesor> sviProfesori = new ArrayList<>();
    private final PristupniPodaci pristupniPodaci; //?final
    private String ime, prezime;
    private int pol;

    public Profesor (PristupniPodaci pristupniPodaci, String ime, String prezime, int pol){
        this.ime = ime;
        this.pol = pol;
        this.prezime = prezime;
        this.pristupniPodaci = pristupniPodaci;
    }
}

