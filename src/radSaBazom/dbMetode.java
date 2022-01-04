package radSaBazom;

import tabele.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbMetode extends dbConn{

    public static void kreirajPristupnePodatke (){
        String QUERY = "SELECT korisnicko_ime, sifra, email FROM pristupni_podaci";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {     //pogledati ovo za sifru jos!!!
                new PristupniPodaci(rs.getString("korisnicko_ime"), rs.getString("email"), rs.getString("korisnicko_ime")+"123");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajPristupnePodatke (String email, String korisnickoIme, String sifra){ //zajednicko za prof i ucenike
        String QUERY = "INSERT INTO pristupni_podaci VALUES (DEFAULT, '"+korisnickoIme+"', '"+email+"', '"+sifra+"')";
        try {
            stmt.executeUpdate(QUERY);
            new PristupniPodaci(korisnickoIme, email, sifra);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void azurirajPristupnePodatke (int id, String sifra){ //ne uzimati id?
        String QUERY = "UPDATE pristupni_podaci SET sifra = '"+ sifra + "' WHERE id = "+id;
        try {
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void kreirajProfesore (){
        String QUERY = "SELECT ime, prezime, pol FROM profesor";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Profesor();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static void dodajProfesora (String ime, String prezime, int pol){
        int id = PristupniPodaci.getSviPodaci().size()-1;
        String QUERY = "INSERT INTO profesor VALUES (DEFAULT, '"+ime+"', '"+prezime+"', '"+pol+"')";
        try {
            stmt.executeUpdate(QUERY);
            new Profesor(PristupniPodaci.getSviPodaci().get(id), ime, prezime, pol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
