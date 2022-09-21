import java.net.ConnectException;
import java.sql.*;

public class ResultSetReader {
    public static String[] readColumn(ResultSet resultSet, String selectColumn) throws ConnectException {
        try {
            String[] tableContents = new String[resultSet.getFetchSize()];
            int i = 0;
            
            while (resultSet.next()) {
                tableContents[i] = resultSet.getString(selectColumn);
                i++;

                System.out.println(tableContents[i] + " ");

                // it is a good idea to release resources in a finally{} block
                // in reverse-order of their creation if they are no-longer needed
            }
            resultSet.close();
            System.out.println("closed succesfully");

            return tableContents;

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            resultSet = null;
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
