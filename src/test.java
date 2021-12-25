import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        dbTest konekcija = new dbTest();    //Preko objekta ili preko staticke met?
        dbTest.kreirajPristupnePodatke(); //pozvati iz maina?
        //PristupniPodaci.kreirajDbObjekte(); //moze staticka metoda iako nema objekta iste klase
        PristupniPodaci test = new PristupniPodaci("ana", "anamail");

        System.out.println(PristupniPodaci.getSviPodaci()); //uspjesan db pull

    }
}
