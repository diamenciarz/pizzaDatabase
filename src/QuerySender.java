import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class QuerySender {
    public static ArrayList<String> selectString(String selectColumn, String from) {
        try {
            ResultSet resultSet = execute(selectColumn, from);
            return ResultSetReader.readString(selectColumn, resultSet);
            
        } catch (ConnectException e) {
            return new ArrayList<String>();
        }
    }
    public static ArrayList<Integer> selectInt(String selectColumn, String from) {
        try {
           
            ResultSet resultSet = execute(selectColumn, from);
            
            return ResultSetReader.readInt(selectColumn, resultSet);
            
        } catch (ConnectException e) {
            return new ArrayList<Integer>();
        }
    }
    public static ArrayList<Boolean> selectBoolean(String selectColumn, String from) {
        try {
            ResultSet resultSet = execute(selectColumn, from);
            return ResultSetReader.readBool(selectColumn, resultSet);
            
        } catch (ConnectException e) {
            return new ArrayList<Boolean>();
        }
    }

    private static ResultSet execute(String selectColumn, String from) throws ConnectException {
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement("SELECT \" "+ selectColumn+" FROM "+from+";");
//TODO sanitazation method


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
