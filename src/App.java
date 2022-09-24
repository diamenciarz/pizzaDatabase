import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        String query = "SELECT * FROM pizza";
        ResultSet rs = null;

        try (Statement stmt = conn.createStatement()) {

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                String variable = rs.getString("pizzaId");
                String timestamp =rs.getString("timer");

                System.out.println("----------------");
                System.out.println(variable + " " +timestamp);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                    System.out.println("closed succesfully");
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }
        }
    }
}
