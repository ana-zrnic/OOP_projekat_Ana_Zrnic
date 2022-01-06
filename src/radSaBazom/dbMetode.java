package radSaBazom;

import tabele.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbMetode extends dbConn{

    public static void kreirajPristupnePodatke (){
        String QUERY = "SELECT korisnicko_ime, sifra, email, id FROM pristupni_podaci";
        //String QUERY = "SELECT email3, sifra3, username, id FROM baza2";

        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {     //pogledati ovo za sifru jos!!! md5-kod?
                new PristupniPodaci(rs.getString("korisnicko_ime"), rs.getString("email"), rs.getString("korisnicko_ime")+"123", rs.getInt("id"));
                //new PristupniPodaci(rs.getString("username"), rs.getString("email3"), rs.getString("sifra3")+"123", rs.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int dodajPristupnePodatke (String email, String korisnickoIme, String sifra){ //zajednicko za prof i ucenike
        String QUERY = "INSERT INTO pristupni_podaci VALUES (DEFAULT, '"+korisnickoIme+"', '"+email+"', '"+sifra+"')";
        //String QUERY = "INSERT INTO baza2 VALUES (DEFAULT, '"+email+"', '"+korisnickoIme+"', '"+sifra+"')";
        int id = -1;
        try {
            stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            new PristupniPodaci(korisnickoIme, email, sifra, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public static void azurirajPristupnePodatke (int id, String sifra){ //ne uzimati id?
        String QUERY = "UPDATE pristupni_podaci SET sifra = '"+ sifra + "' WHERE id = "+id;
        try {
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void kreirajProfesore (){ //preload prilikom ucitavanja?
        String QUERY = "SELECT ime, prezime, pol, pristupni_podaci_id FROM profesor";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Profesor(PristupniPodaci.getSviPodaci().get(rs.getInt("pristupni_podaci_id")),rs.getString("ime"), rs.getString("prezime"),rs.getInt("pol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dodajProfesora (String ime, String prezime, int pol, int id){
        String QUERY = "INSERT INTO profesor VALUES (DEFAULT, '"+ime+"', '"+prezime+"', '"+pol+"', '"+id+"')";
        try {
            stmt.executeUpdate(QUERY);
            new Profesor(PristupniPodaci.getSviPodaci().get(id), ime, prezime, pol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void kreirajUcenike (){
        String QUERY = "SELECT ime, prezime, pol, pristupni_podaci_id FROM ucenik";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new Ucenik(PristupniPodaci.getSviPodaci().get(rs.getInt("pristupni_podaci_id")),rs.getString("ime"), rs.getString("prezime"),rs.getInt("pol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajUcenika (String ime, String prezime, int pol, int id){
        String QUERY = "INSERT INTO ucenik VALUES (DEFAULT, '"+ime+"', '"+prezime+"', '"+pol+"', '"+id+"')";
        try {
            stmt.executeUpdate(QUERY);
            new Ucenik(PristupniPodaci.getSviPodaci().get(id), ime, prezime, pol);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
