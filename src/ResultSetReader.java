import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class ResultSetReader {
    public static ArrayList<String> readString(String selectColumn, ResultSet resultSet) throws ConnectException {
        try {
            ArrayList<String> tableContents = new ArrayList<String>();
            int i = 0;
            
            while (resultSet.next()) {
                tableContents.add( resultSet.getString(selectColumn));
                System.out.println(tableContents.get(i)+ " ");
                i++;
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

    public static ArrayList<Integer> readInt(String selectColumn, ResultSet resultSet) throws ConnectException {
        try {
            ArrayList<Integer> tableContents = new ArrayList<Integer>();
            int i = 0;
           
            
          //  System.out.println("imhere");
            while (resultSet.next()) {
                
                tableContents.add(resultSet.getInt(selectColumn));
              
                i++;
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

    public static ArrayList<Boolean> readBool(String selectColumn, ResultSet resultSet) throws ConnectException {
        try {
           ArrayList<Boolean> tableContents = new ArrayList<Boolean>();
            int i = 0;
            
            while (resultSet.next()) {
                tableContents.add(resultSet.getBoolean(selectColumn));
                System.out.println(tableContents.get(i) + " ");
                i++;

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
