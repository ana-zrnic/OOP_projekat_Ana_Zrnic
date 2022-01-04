package tabele;

import java.util.ArrayList;

public class PristupniPodaci {
    //private static radSaBazom.dbTest konekcija = new radSaBazom.dbTest();
    private static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
    private final String korisnickoIme, email;  //final?
    private String sifra;

    public PristupniPodaci (String korisnickoIme, String email, String sifra){
        this.korisnickoIme = korisnickoIme; //neka korisnicko ime u obrascu prilikom kreiranja automatski bude ime.prezime
        this.email = email;
        this.sifra = sifra;
        if(postojiNalog(this))  //poslati samo atribute ili citav objekat?
            System.out.println("postoji nalog sa ovim podacima"); //exception??
        else {
            sviPristupniPodaci.add(this);
        }
    }

    public static ArrayList<PristupniPodaci> getSviPodaci (){
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

    public String toString (){
        return "korisnik: "+this.korisnickoIme +", "+ this.sifra+", " + this.email;
    }

    public static void kreirajDbObjekte(){
        //konekcija.kreirajPristupnePodatke();
    }

    private boolean postojiNalog(PristupniPodaci podaci){
        if(sviPristupniPodaci!=null)
            for(PristupniPodaci p : sviPristupniPodaci)
                if(p.korisnickoIme.equals(podaci.korisnickoIme) && p.email.equals(podaci.email))
                    return true;
        return false;
    }
}
