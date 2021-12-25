import java.sql.*;
import java.util.ArrayList;

public class dbTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ors1_opp_2021_2022";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection conn = null;
    private static Statement stmt;

    public dbTest() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
    }
    private static void closeConn (){
        try {
            if (conn != null) { //da li ostaviti ovaj if ili izbaciti null na pocetku
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
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
        closeConn();
    }

}
