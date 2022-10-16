import java.sql.*;

public class ResultSetReader {
    public static String readString(String selectColumn, ResultSet resultSet) {
        try {
            return resultSet.getString(selectColumn);
        } catch (SQLException ex) {
            handleSQLException(ex);
            return null;
        }
    }

    public static Integer readInt(String selectColumn, ResultSet resultSet) {
        try {
            return resultSet.getInt(selectColumn);
        } catch (SQLException ex) {
            handleSQLException(ex);
            return null;
        }
    }

    public static Float readFloat(String selectColumn, ResultSet resultSet) {
        try {
            return resultSet.getFloat(selectColumn);
        } catch (SQLException ex) {
            handleSQLException(ex);
            return null;
        }
    }

    public static Boolean readBoolean(String selectColumn, ResultSet resultSet) {
        try {
            return resultSet.getBoolean(selectColumn);
        } catch (SQLException ex) {
            handleSQLException(ex);
            return null;
        }
    }

    public static Timestamp readTimestamp(String selectColumn, ResultSet resultSet) {
        try {
            return resultSet.getTimestamp(selectColumn);
        } catch (SQLException ex) {
            handleSQLException(ex);
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
