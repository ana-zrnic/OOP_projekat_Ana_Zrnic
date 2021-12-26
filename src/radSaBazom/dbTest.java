import java.sql.*;
/**
 * testna klasa
 */

public class dbTest {
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/ors1_opp_2021_2022";
    protected static final String USER = "root";
    protected static final String PASS = "";
    protected static Connection conn = null;
    protected static Statement stmt;

    public dbTest() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new Error("Problem", e);
        }
    }
    protected static void closeConn (){
        try {
            if (conn != null) { //da li ostaviti ovaj if ili izbaciti null na pocetku
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
