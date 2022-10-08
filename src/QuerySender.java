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

        // Objects
        // ___________________________________________________________________________________
        public static ArrayList<Order> selectOrdersBelongingTo(int clientID) {
            try {
                ResultSet resultSet = execute(Integer.toString(clientID), "orders");
                return UnpackObj.List.unpackOrders(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<Order>();
            }
        }

        public static ArrayList<MenuItem> selectMenuItemsBelongingTo(int orderID) {
            try {
                ResultSet resultSet = execute(Integer.toString(orderID), "menuItems");
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<MenuItem>();
            }
        }

        public static ArrayList<Ingredient> selectIngredientsBelongingTo(int itemID) {
            try {
                ResultSet resultSet = execute(Integer.toString(itemID), "ingredients");
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
        public static Order selectOrder(String orderId) {
            try {
                ResultSet resultSet = execute(orderId, "orders");
                return UnpackObj.SingleValue.unpackOrder(resultSet);

            } catch (ConnectException e) {
                return new Order();
            }
        }

        public static MenuItem selectMenuItem(String menuItemId) {
            try {
                ResultSet resultSet = execute(menuItemId, "menuItems");
                return UnpackObj.SingleValue.unpackMenuItem(resultSet);

            } catch (ConnectException e) {
                return new MenuItem();
            }
        }

        public static Ingredient selectIngredient(String ingredientId) {
            try {
                ResultSet resultSet = execute(ingredientId, "ingredients");
                return UnpackObj.SingleValue.unpackIngredient(resultSet);

            } catch (ConnectException e) {
                return new Ingredient();
            }
        }
    }


    //Helper methods
    static ResultSet execute(String selectColumn, String from) throws ConnectException {
        selectColumn = sanitize(selectColumn);
        from = sanitize(from);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn
                    // enum?
                    .prepareStatement("SELECT " + selectColumn + " FROM " + from + ";");

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
        System.out.println("input: " + query);
        char c = '"';
        char t = ' ';
        String sanitized = query.replace(c, t);
        // String sanitized = query.replaceAll("\"", "").replaceAll("\\", "");
        System.out.println(sanitized);
        return sanitized;
    }
}
