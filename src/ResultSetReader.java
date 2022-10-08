import java.sql.*;
import java.util.ArrayList;

public class ResultSetReader {
    public static ArrayList<String> readString(String selectColumn, ResultSet resultSet) {
        try {
            ArrayList<String> tableContents = new ArrayList<String>();
            resultSet.beforeFirst();

            while (resultSet.next()) {
                tableContents.add(resultSet.getString(selectColumn));
                // it is a good idea to release resources in a finally{} block
                // in reverse-order of their creation if they are no-longer needed
            }
            resultSet.close();
            System.out.println("closed successfully");

            return tableContents;

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            resultSet = null;
            return null;
        }
    }

    public static ArrayList<Integer> readInt(String selectColumn, ResultSet resultSet) {
        try {
            ArrayList<Integer> tableContents = new ArrayList<Integer>();
            resultSet.beforeFirst();

            // System.out.println("imhere");
            while (resultSet.next()) {

                tableContents.add(resultSet.getInt(selectColumn));

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

    public static ArrayList<Float> readFloat(String selectColumn, ResultSet resultSet) {
        try {
            ArrayList<Float> tableContents = new ArrayList<Float>();
            resultSet.beforeFirst();

            // System.out.println("imhere");
            while (resultSet.next()) {

                tableContents.add(resultSet.getFloat(selectColumn));

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

    public static ArrayList<Boolean> readBool(String selectColumn, ResultSet resultSet) {
        try {
            ArrayList<Boolean> tableContents = new ArrayList<Boolean>();
            resultSet.beforeFirst();

            while (resultSet.next()) {
                tableContents.add(resultSet.getBoolean(selectColumn));

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

    public static ArrayList<Order> readOrder(String selectColumn, ResultSet resultSet) {
        try {
            ArrayList<Order> tableContents = new ArrayList<Order>();
            resultSet.beforeFirst();

            while (resultSet.next()) {
                tableContents.add(resultSet.getBoolean(selectColumn));

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

    private static void handleSQLException(SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        System.out.println("Connection with the database couldn't be established");
    }
}
