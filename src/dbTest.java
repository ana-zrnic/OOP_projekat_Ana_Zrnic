import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class dbTest {
    private final String DB_URL = "jdbc:mysql://localhost:3306/ors1_opp_2021_2022";
    private final String USER = "root";
    private final String PASS = "";

    public Map<Integer, String> queryTest(String idKol, String kolona, String tabela) {
        Map<Integer, String> temp = new HashMap<>();
        String QUERY = "SELECT " + idKol + ", " + kolona + " FROM " + tabela;

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            while (rs.next()) {
                temp.put(rs.getInt(idKol), rs.getString(kolona));
            }
        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return temp;
    }

}
