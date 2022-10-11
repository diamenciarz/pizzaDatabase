import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

import objects.Ingredient;
import objects.MenuItem;
import objects.Order;

public class QuerySender {

    public static class List {
        public static ArrayList<String> selectStrings(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                ArrayList<String> strings = new ArrayList<>();

                while (resultSet.next()) {
                    strings.add(ResultSetReader.readString(selectColumn, resultSet));
                }
                return strings;
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("String list selection failed");
                return new ArrayList<String>();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("String list was empty");
                return new ArrayList<String>();
            }
        }

        public static ArrayList<Integer> selectInts(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                ArrayList<Integer> ints = new ArrayList<>();

                while (resultSet.next()) {
                    ints.add(ResultSetReader.readInt(selectColumn, resultSet));
                }
                return ints;
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Int list selection failed");
                return new ArrayList<Integer>();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Int list was empty");
                return new ArrayList<Integer>();
            }
        }

        public static ArrayList<Float> selectFloats(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                ArrayList<Float> floats = new ArrayList<>();

                while (resultSet.next()) {
                    floats.add(ResultSetReader.readFloat(selectColumn, resultSet));
                }
                return floats;
            } catch (ConnectException e) {
                return new ArrayList<Float>();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Float list was empty");
                return new ArrayList<Float>();
            }
        }

        public static ArrayList<Boolean> selectBooleans(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                ArrayList<Boolean> booleans = new ArrayList<>();

                while (resultSet.next()) {
                    booleans.add(ResultSetReader.readBoolean(selectColumn, resultSet));
                }
                return booleans;
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Float list selection failed");
                return new ArrayList<Boolean>();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Boolean list was empty");
                return new ArrayList<Boolean>();
            }
        }

        public static ArrayList<String> selectDiscountCodes(int clientID) {
            try {
                ResultSet resultSet = QuerySender.filter(DatabaseNames.Code.discountCode, DatabaseNames.Tables.codes,
                        DatabaseNames.Code.clientID, clientID);
                ArrayList<String> codes = new ArrayList<>();

                while (resultSet.next()) {
                    codes.add(ResultSetReader.readString(DatabaseNames.Code.discountCode, resultSet));
                }
                return codes;
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Discount code list selection failed");
                return new ArrayList<String>();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Discount code list was empty");
                return new ArrayList<String>();
            }
        }

        // Objects
        // ___________________________________________________________________________________
        public static ArrayList<Order> selectOrdersBelongingTo(int clientID) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.orderItems, DatabaseNames.Order.clientID,
                        clientID);
                return UnpackObj.List.unpackOrders(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order list belonging to Client selection failed");
                return new ArrayList<Order>();
            }
        }

        public static ArrayList<MenuItem> selectMenuItemsBelongingTo(int orderID) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.menuItems, DatabaseNames.Order.orderID, orderID);
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem list belonging to order selection failed");
                return new ArrayList<MenuItem>();
            }
        }

        public static ArrayList<Ingredient> selectIngredientsBelongingTo(int itemID) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.foodIngredients,
                        DatabaseNames.MenuItem.menuItemID, itemID);
                return UnpackObj.List.unpackIngredients(resultSet);

            } catch (ConnectException e) {
                return new ArrayList<Ingredient>();
            }
        }

        public static ArrayList<Ingredient> selectIngredients() {
            try {
                ResultSet resultSet = execute("*", DatabaseNames.Tables.ingredients);
                return UnpackObj.List.unpackIngredients(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient list selection failed");
                return new ArrayList<Ingredient>();
            }
        }

        public static ArrayList<MenuItem> SelectMenu() {
            try {
                ResultSet resultSet = execute("*", DatabaseNames.Tables.menuItems);
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem list selection failed");
                return new ArrayList<MenuItem>();
            }
        }
    }

    public static class SingleValue {
        public static String selectString(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.readString(selectColumn, resultSet);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("String selection failed");
                return "";
            }
        }

        public static Integer selectInt(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.readInt(selectColumn, resultSet);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Int selection failed");
                return 0;
            }
        }

        public static Float selectFloat(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.readFloat(selectColumn, resultSet);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Float selection failed");
                return 0f;
            }
        }

        public static Boolean selectBoolean(String selectColumn, String from) {
            try {
                ResultSet resultSet = execute(selectColumn, from);
                return ResultSetReader.readBoolean(selectColumn, resultSet);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Boolean selection failed");
                return false;
            }
        }

        // Objects
        // ___________________________________________________________________________________
        public static Order selectOrder(int orderId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.orders, DatabaseNames.Order.orderID, orderId);
                return UnpackObj.SingleValue.unpackOrder(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order selection failed");
                return new Order();
            }
        }

        public static MenuItem selectMenuItem(int menuItemId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.menuItems, DatabaseNames.MenuItem.menuItemID, menuItemId);
                return UnpackObj.SingleValue.unpackMenuItem(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem selection failed");
                return new MenuItem();
            }
        }

        public static Ingredient selectIngredient(int ingredientId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.ingredients, DatabaseNames.Ingredient.ingredientID, ingredientId);
                return UnpackObj.SingleValue.unpackIngredient(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient selection failed");
                return new Ingredient();
            }
        }

        // Insert
        // ___________________________________________________________________________________
        public static void insertIngredient(Ingredient ingredient) {
            String[] names = { DatabaseNames.Ingredient.ingredientID, DatabaseNames.Ingredient.ingredientName,
                    DatabaseNames.Ingredient.isVegetarian, DatabaseNames.Ingredient.price };
            String[] values = { DatabaseNames.Ingredient.ingredientID, DatabaseNames.Ingredient.ingredientName,
                    DatabaseNames.Ingredient.isVegetarian, DatabaseNames.Ingredient.price };

            try {
                insert(DatabaseNames.Tables.ingredients, names, values);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient insertion failed");
            }
        }
    }

    // Helper methods
    static ResultSet insert(String to, String[] names, String[] values) throws ConnectException {
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
