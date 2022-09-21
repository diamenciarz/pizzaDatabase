import java.net.ConnectException;
import java.sql.*;

public class QuerySender {
    public static String[] select(String selectColumn, String from) throws ConnectException {
        ResultSet resultSet = execute(selectColumn, from);
        return ResultSetReader.readColumn(resultSet, selectColumn);
    }

    private static ResultSet execute(String selectColumn, String from) throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement("SELECT ? FROM ?;");
            prepStatement.setString(1, selectColumn);
            prepStatement.setString(2, from);

            return prepStatement.executeQuery();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            return null;
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
