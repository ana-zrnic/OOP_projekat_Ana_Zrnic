package radSaBazom;

import tabele.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbMetode extends dbConn{

    public static void kreirajPristupnePodatke (){
        String QUERY = "SELECT korisnicko_ime, sifra, email FROM pristupni_podaci";
        try {
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                new PristupniPodaci(rs.getString("korisnicko_ime"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dodajPristupnePodatke (String email, String korisnickoIme, String sifra){
        String QUERY = "INSERT INTO pristupni_podaci VALUES (DEFAULT, '"+korisnickoIme+"', '"+email+"', '"+sifra+"')";
        try {
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void azurirajPristupnePodatke (int id, String sifra){
        String QUERY = "UPDATE pristupni_podaci SET sifra = '"+ sifra + "' WHERE id = "+id;
        try {
            stmt.executeUpdate(QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
