import java.net.ConnectException;
import java.sql.*;

public class Startup {
    public static void startup() throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            Statement statement = conn.createStatement();


            statement.executeQuery("DROP DATABASE restaurant IF EXISTS;");
            statement.executeQuery("CREATE DATABASE restaurant;");
            // statement.executeQuery("CREATE TABLE client");

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
        }
    }

    private static void handleSQLException(SQLException ex) throws ConnectException {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        throw new ConnectException("Connection with the database couldn't be established");
    }
}
