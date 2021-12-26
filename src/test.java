import radSaBazom.dbConn;
import radSaBazom.dbMetode;
import tabele.PristupniPodaci;

public class test {
    public static void main(String[] args) {
        dbConn konekcija = new dbConn();    //Preko objekta ili preko staticke met?

        dbMetode.kreirajPristupnePodatke(); //pozvati iz maina?
        //tabele.PristupniPodaci.kreirajDbObjekte(); //moze staticka metoda iako nema objekta iste klase

        System.out.println(PristupniPodaci.getSviPodaci()); //db pull
        //tabele.PristupniPodaci test = new tabele.PristupniPodaci("bojan.mitrovic", "bojan.mitrovic@gmail.com");

        //radSaBazom.dbMetode.dodajPristupnePodatke(test.getEmail(), test.getKorisnickoIme(), test.getSifra()); //db push
        //System.out.println(tabele.PristupniPodaci.getSviPodaci()); //db pull

        //radSaBazom.dbMetode.azurirajPristupnePodatke(13, "bojan.mitrovic1234");
    }
}
