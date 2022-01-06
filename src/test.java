import radSaBazom.dbConn;
import radSaBazom.dbMetode;
import tabele.PristupniPodaci;

public class test {

    public static void main(String[] args) {
        dbConn konekcija = new dbConn();    //Preko objekta ili preko staticke met?

        dbMetode.kreirajPristupnePodatke(); //pozvati iz maina?
        //PristupniPodaci.kreirajDbObjekte(); //moze staticka metoda iako nema objekta iste klase

        System.out.println(PristupniPodaci.getSviPodaci()); //db pull

        dbMetode.dodajPristupnePodatke("mail9", "username9", "sifra9");
        System.out.println(PristupniPodaci.getSviPodaci());


        //PristupniPodaci test = new tabele.PristupniPodaci("bojan.mitrovic", "bojan.mitrovic@gmail.com");

        //dbMetode.dodajPristupnePodatke(test.getEmail(), test.getKorisnickoIme(), test.getSifra()); //db push
        //System.out.println(PristupniPodaci.getSviPodaci()); //db pull

        //dbMetode.azurirajPristupnePodatke(13, "bojan.mitrovic123");
    }
}
