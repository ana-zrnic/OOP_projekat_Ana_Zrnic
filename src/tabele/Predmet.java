package tabele;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Predmet {
    //private static ArrayList<Predmet> sviPredmeti = new ArrayList<>();
    private static Map<Integer, Predmet> sviPredmeti = new Hashtable<>();
    private String naziv;
    private int razred, id;

    public Predmet (String naziv, int razred, int id){
        this.naziv = naziv;
        this.razred = razred;
        this.id = id;

        if(predmetPostoji(this))
            System.out.println("Predmet postoji u bazi");
        else
            sviPredmeti.put(id, this);
    }

    private boolean predmetPostoji(Predmet predmet){
        if(sviPredmeti!=null)
            for(Predmet p : sviPredmeti.values())
                if(p.naziv.equals(predmet.naziv) && (p.razred == predmet.razred))
                    return true;
        return false;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

    public int getRazred() {
        return razred;
    }

    public static Map<Integer, Predmet> getSviPredmeti() {
        return sviPredmeti;
    }

    @Override
    public String toString() {
        return "Predmet{" +
                "naziv='" + naziv + '\'' +
                ", razred=" + razred +
                ", id=" + id +
                '}';
    }
}
