import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import radSaBazom.dbConn;
import radSaBazom.dbMetode;
import tabele.PredmetUskoli;
import tabele.PristupniPodaci;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javatests.TestSupport.assertEquals;
import static javatests.TestSupport.assertThat;

public class test {

    public static void main(String[] args) throws Exception {
        /*dbConn konekcija = new dbConn();    //Preko objekta ili preko staticke met?

        dbMetode.kreirajPristupnePodatke(); //pozvati iz maina?
        dbMetode.kreirajProfesore();
        dbMetode.kreirajUcenike();
        dbMetode.kreirajPredmete();
        dbMetode.kreirajSkole();
        dbMetode.kreirajPredmeteUSkoli();*/
        //PristupniPodaci.kreirajDbObjekte(); //moze staticka metoda iako nema objekta iste klase

        //System.out.println(PristupniPodaci.getSviPodaci()); //db pull
        //System.out.println(PredmetUskoli.getSviPredmetiSkole()); //db pull

        //dbMetode.dodajPristupnePodatke("mail9", "username9", "sifra9");
        //System.out.println(PristupniPodaci.getSviPodaci());

        //PristupniPodaci test = new tabele.PristupniPodaci("bojan.mitrovic", "bojan.mitrovic@gmail.com");

        //dbMetode.dodajPristupnePodatke(test.getEmail(), test.getKorisnickoIme(), test.getSifra()); //db push
        //System.out.println(PristupniPodaci.getSviPodaci()); //db pull

        //dbMetode.azurirajPristupnePodatke(13, "bojan.mitrovic123");

        /*for (int i = 13; i<=18; i++)
            dbMetode.azurirajPristupnePodatke(i,PristupniPodaci.getSviPodaci().get(i).getSifra());
        */
        //dbMetode.azurirajPristupnePodatke(19,PristupniPodaci.getSviPodaci().get(19).getSifra());

        /*LocalDate d1 = LocalDate.parse("2020-10-21");
        LocalDate d2 = LocalDate.parse("2020-10-28");
        LocalDate d3 = LocalDate.parse("2020-11-21");
        LocalDate d4 = LocalDate.parse("2021-10-21");
        ArrayList<LocalDate> lista = new ArrayList<>();
        lista.add(d1);
        lista.add(d2);
        lista.add(d3);
        lista.add(d4);
        lista.sort(null);
        System.out.println(lista.toString());*/

        ProcessBuilder processBuilder = new ProcessBuilder("python",
                "C:\\Users\\WIN10\\Desktop\\Algodat\\programming exercise 1\\mail.py",
                "iz jave username", "iz jave sifra", "ana.zrnic10@gmail.com");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        System.out.println(process.toString());





    }
}
