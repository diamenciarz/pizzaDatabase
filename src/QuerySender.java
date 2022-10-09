import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

public class QuerySender {

    public static class List {
        public static ArrayList<String> selectStrings(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.List.readStrings(selectColumn, resultSet);
            } catch (ConnectException e) {
                return new ArrayList<String>();
            }
        }

        public static ArrayList<Integer> selectInts(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.List.readInts(selectColumn, resultSet);
            } catch (ConnectException e) {
                return new ArrayList<Integer>();
            }
        }

        public static ArrayList<Float> selectFloats(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.List.readFloats(selectColumn, resultSet);
            } catch (ConnectException e) {
                return new ArrayList<Float>();
            }
        }

        public static ArrayList<Boolean> selectBooleans(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.List.readBooleans(selectColumn, resultSet);
            } catch (ConnectException e) {
                return new ArrayList<Boolean>();
            }
        }

        public static ArrayList<String> getDiscountCodes(int Client_ID) {
            try {
                ResultSet resultSet = QuerySender.filter(DatabaseNames.CodeKeys.ClientID, DatabaseNames.Tables.codes, "Client_ID", Client_ID);
                ArrayList<String> Codes = ResultSetReader.List.readStrings("Discount_code", resultSet);
                return Codes;

            } catch (ConnectException e) {
                System.out.println("Value not found");
                return new ArrayList<>();
                // TODO: handle exception
            }
        }

        // Objects
        // ___________________________________________________________________________________
        public static ArrayList<Order> selectOrdersBelongingTo(int clientID) {
            try {
                ResultSet resultSet = filter(Integer.toString(clientID), "orders", "clientID", clientID);
                return UnpackObj.List.unpackOrders(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<Order>();
            }
        }

        public static ArrayList<MenuItem> selectMenuItemsBelongingTo(int orderID) {
            try {
                ResultSet resultSet = filter(Integer.toString(orderID), "menuItems", "orderID", orderID);
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<MenuItem>();
            }
        }

        public static ArrayList<Ingredient> selectIngredientsBelongingTo(int itemID) {
            try {
                ResultSet resultSet = filter(Integer.toString(itemID), "ingredients", "menuItemID", itemID);
                return UnpackObj.List.unpackIngredients(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<Ingredient>();
            }
        }
    }

    public static class SingleValue {
        public static String selectString(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.SingleValue.readString(selectColumn, resultSet);
            } catch (ConnectException e) {
                return "";
            }
        }

        public static Integer selectInt(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.SingleValue.readInt(selectColumn, resultSet);
            } catch (ConnectException e) {
                return 0;
            }
        }

        public static Float selectFloat(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.SingleValue.readFloat(selectColumn, resultSet);
            } catch (ConnectException e) {
                return 0f;
            }
        }

        public static Boolean selectBoolean(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.SingleValue.readBoolean(selectColumn, resultSet);
            } catch (ConnectException e) {
                return false;
            }
        }

        // Objects
        // ___________________________________________________________________________________
        public static Order selectOrder(int orderId) {
            try {
                ResultSet resultSet = execute(Integer.toString(orderId), "orders");
                return UnpackObj.SingleValue.unpackOrder(resultSet);

            } catch (ConnectException e) {
                return new Order();
            }
        }

        public static MenuItem selectMenuItem(int menuItemId) {
            try {
                ResultSet resultSet = execute(Integer.toString(menuItemId), "menuItems");
                return UnpackObj.SingleValue.unpackMenuItem(resultSet);

            } catch (ConnectException e) {
                return new MenuItem();
            }
        }

        public static Ingredient selectIngredient(int ingredientId) {
            try {
                ResultSet resultSet = execute(Integer.toString(ingredientId), "ingredients");
                return UnpackObj.SingleValue.unpackIngredient(resultSet);

            } catch (ConnectException e) {
                return new Ingredient();
            }
        }

        // Insert
        // ___________________________________________________________________________________
        public static void insertIngredient(Ingredient ingredient){
            insert(null, null, "ingredients");
        }
    }

    // Helper methods
    static ResultSet insert(String[] names, String[] values, String to) throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement(prepareInsertCommand(names, values, to));

            return prepStatement.executeQuery();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            return null;
        }
    }

    private static String prepareInsertCommand(String[] names, String[] values, String to) {
        if (names.length != values.length) {
            System.out.println("ERROR: The count of names and values must be the same!");
            return "";
        }

        String command = "INSERT INTO " + sanitize(to) + " (";
        for (int i = 0; i < names.length; i++) {
            command = command + sanitize(names[i]);
            if (i != names.length - 1) {
                command = command + ", ";
            }
        }
        command = command + ") VALUES (";

        for (int i = 0; i < values.length; i++) {
            command = command + sanitize(values[i]);
            if (i != values.length - 1) {
                command = command + ", ";
            }
        }
        command = command + ");";
        return command;
    }

    static ResultSet execute(String selectColumn, String from) throws ConnectException {
        selectColumn = sanitize(selectColumn);
        from = sanitize(from);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement("SELECT " + selectColumn + " FROM " + from + ";");

            return prepStatement.executeQuery();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            return null;
        }
    }

    static ResultSet filter(String selectColumn, String from, String filterName, int filterValue)
            throws ConnectException {
        selectColumn = sanitize(selectColumn);
        from = sanitize(from);
        filterName = sanitize(filterName);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement("SELECT " + selectColumn + " FROM " + from +
                    " WHERE " + filterName + " = " + filterValue + ";");

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
        // System.out.println("input: " + query);
        char remove = '"';
        char remove2 = '\\';
        char remove3 = ',';
        char insert = ' ';
        String sanitized = query.replace(remove, insert).replace(remove2, insert).replace(remove3, insert);
        // System.out.println("output: " + sanitized);
        return sanitized;
    }
}
