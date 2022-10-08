import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class QuerySender {
    public static ArrayList<String> selectStrings(String selectColumn, String from) {
        try {
            ResultSet resultSet = execute(selectColumn, from);
            return ResultSetReader.readString(selectColumn, resultSet);

        } catch (ConnectException e) {
            return new ArrayList<String>();
        }
    }

    public static ArrayList<Integer> selectInts(String selectColumn, String from) {
        try {

            ResultSet resultSet = execute(selectColumn, from);

            return ResultSetReader.readInt(selectColumn, resultSet);

        } catch (ConnectException e) {
            return new ArrayList<Integer>();
        }
    }

    public static ArrayList<Boolean> selectBooleans(String selectColumn, String from) {
        try {
            ResultSet resultSet = execute(selectColumn, from);
            return ResultSetReader.readBool(selectColumn, resultSet);

        } catch (ConnectException e) {
            return new ArrayList<Boolean>();
        }
    }
    
    public static ArrayList<Order> selectOrders(String selectColumn, String from) {
        try {
            ResultSet resultSet = execute("order", "orders");
            return UnpackObj.unpackOrders(resultSet);

        } catch (ConnectException e) {
            return new ArrayList<Order>();
        }
    }
    
    public static ArrayList<Order> selectMenuItems(String orderId) {
        try {
            ResultSet resultSet = execute(orderId, "menuItems");
            return UnpackObj.unpackOrders(resultSet);

        } catch (ConnectException e) {
            return new ArrayList<Order>();
        }
    }
    
    public static ArrayList<Order> selectIngredients(String ingredientId) {
        try {
            ResultSet resultSet = execute(ingredientId, "ingredients");
            return UnpackObj.unpackOrders(resultSet);

        } catch (ConnectException e) {
            return new ArrayList<Order>();
        }
    }

    static ResultSet execute(String selectColumn, String from) throws ConnectException {
        selectColumn=sanitize(selectColumn);
        from = sanitize(from);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn
            //enum?
                    .prepareStatement("SELECT " +selectColumn+ " FROM " + from+ ";");
            // TODO sanitazation method

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

    private static String sanitize(String query) {
        System.out.println("input: " +query);
        char c = '"';
        char t= ' ';
        String sanitized=query.replace(c, t);
        //String sanitized = query.replaceAll("\"", "").replaceAll("\\", "");
        System.out.println(sanitized);
        return sanitized;
    }
}
