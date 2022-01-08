package tabele;

import java.util.Hashtable;
import java.util.Map;

public class PristupniPodaci {
    //private static radSaBazom.dbTest konekcija = new radSaBazom.dbTest();
    //private static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
    private static Map<Integer, PristupniPodaci> sviPristupniPodaci = new Hashtable<>();
    private final String korisnickoIme, email;  //final?
    private final int id;
    private String sifra;


    public PristupniPodaci (String korisnickoIme, String email, String sifra, int id){
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.sifra = sifra;
        this.id = id;
        if(postojiNalog(this))  //poslati samo atribute ili citav objekat?
            System.out.println("postoji nalog sa ovim podacima"); //exception??
        else {
            sviPristupniPodaci.put(id, this);
        }
    }

    public static Map<Integer, PristupniPodaci> getSviPodaci (){
        return sviPristupniPodaci;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public String getSifra() {
        return sifra;
    }

    public int getId() {
        return id;
    }

    public String toString (){
        return "[korisnik: "+this.korisnickoIme +", "+ this.sifra+", " + this.email+"]";
    }

    public static void kreirajDbObjekte(){
        //konekcija.kreirajPristupnePodatke();
    }

    private boolean postojiNalog(PristupniPodaci podaci){
        if(sviPristupniPodaci!=null)
            for(PristupniPodaci p : sviPristupniPodaci.values())
                if(p.korisnickoIme.equals(podaci.korisnickoIme) && p.email.equals(podaci.email))
                    return true;
        return false;
    }


}
