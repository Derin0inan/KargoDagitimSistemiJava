/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab11;

//import com.postgresqltutorial.Yazlab11;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class conn {

    public Connection conn = null;
    private Statement st;

    public Connection connectDb() {
        String url = "jdbc:postgresql://34.68.27.80:5432/yazlab11";
        String user = "postgres";
        String password = "root";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException classNotFoundException) {

            System.out.println(classNotFoundException.getMessage());
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("basarili");
            st = conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM public.musteri");
            if (!res.next()) {
                System.out.println("Connected to the PostgreSQL server successfully.");

            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return conn;
    }
}
