package jdbc;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectService {

    private DBConnectService() {
    }

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //hostname
                    append("3306/").                //port
                    append("sys?").                 //db name
                    append("user=tully&").          //login
                    append("password=tully");       //password

            return DriverManager.getConnection(url.toString());

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getPostgresqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:postgresql://").    //db type
                    append("localhost:").            //hostname
                    append("5432/").                 //port
                    append("db_example?").           //db name
                    append("user=webapp&").          //login
                    append("password=12345");        //password

            return DriverManager.getConnection(url.toString());

        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
