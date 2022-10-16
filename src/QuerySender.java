import java.net.ConnectException;
import java.sql.*;
import java.sql.Timestamp;

import java.util.ArrayList;

import objects.*;

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
                        DatabaseNames.Code.clientID, Integer.toString(clientID));
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
                ResultSet resultSet = filter("*", DatabaseNames.Tables.orders, DatabaseNames.Order.clientID,
                        Integer.toString(clientID));
                return UnpackObj.List.unpackOrders(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order list belonging to Client selection failed");
                return new ArrayList<Order>();
            }
        }

        public static ArrayList<MenuItem> selectMenuItemsBelongingTo(int orderID) {
            try {
                ResultSet resultSet = join("*", DatabaseNames.Tables.orderItems, DatabaseNames.Tables.menuItems,
                        DatabaseNames.MenuItem.menuItemID, DatabaseNames.Order.orderID, orderID);
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem list belonging to order selection failed");
                return new ArrayList<MenuItem>();
            }
        }

        public static ArrayList<Ingredient> selectIngredientsBelongingTo(int itemID) {
            try {
                ResultSet resultSet = join("*", DatabaseNames.Tables.foodIngredients, DatabaseNames.Tables.ingredients,
                        DatabaseNames.Ingredient.ingredientID, DatabaseNames.MenuItem.menuItemID, itemID);
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

        public static ArrayList<MenuItem> selectMenu() {
            try {
                ResultSet resultSet = execute("*", DatabaseNames.Tables.menuItems);
                return UnpackObj.List.unpackMenuItems(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem list selection failed");
                return new ArrayList<MenuItem>();
            }
        }

        public static ArrayList<Order> selectCurrentOrders() {
            try {
                ResultSet resultSet = execute("*", DatabaseNames.Tables.orders);
                return UnpackObj.List.unpackOrders(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem list selection failed");
                return new ArrayList<Order>();
            }
        }
    }

    public static class SingleValue {
        // region select primitives
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
        // endregion

        // region select Objects
        // ___________________________________________________________________________________
        /**
         * @param orderID
         * @return "Order status <status> cancel possible <boolean>"
         */
        public static String selectDeliveryStatus(int orderId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.orders, DatabaseNames.Order.orderID,
                        Integer.toString(orderId));
                Order order = UnpackObj.SingleValue.unpackOrder(resultSet);

                String status = order.status.toString();
                String isPossible = "";
                if (!HelperMethods.isCancelPossible(order)) {
                    isPossible = "not";
                }

                return "Order status: " + status + ". Cancel " + isPossible + " possible.";

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order selection failed");
                return null;
            }
        }

        public static Order selectOrder(int orderId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.orders, DatabaseNames.Order.orderID,
                        Integer.toString(orderId));
                return UnpackObj.SingleValue.unpackOrder(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order selection failed");
                return null;
            }
        }

        public static MenuItem selectMenuItem(int menuItemId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.menuItems, DatabaseNames.MenuItem.menuItemID,
                        Integer.toString(menuItemId));
                return UnpackObj.SingleValue.unpackMenuItem(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("MenuItem selection failed");
                return null;
            }
        }

        public static Ingredient selectIngredient(int ingredientId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.ingredients,
                        DatabaseNames.Ingredient.ingredientID, Integer.toString(ingredientId));
                return UnpackObj.SingleValue.unpackIngredient(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient selection failed");
                return null;
            }
        }

        public static Client selectClient(int clientId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.clients,
                        DatabaseNames.Client.clientID, Integer.toString(clientId));
                return UnpackObj.SingleValue.unpackClient(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient selection failed");
                return null;
            }
        }

        public static Courier selectCourier(int courierId) {
            try {
                ResultSet resultSet = filter("*", DatabaseNames.Tables.couriers,
                        DatabaseNames.Courier.courierID, Integer.toString(courierId));
                return UnpackObj.SingleValue.unpackCourier(resultSet);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient selection failed");
                return null;
            }
        }

        public static int getPizzaCount(int clientId) {
            try {
                ResultSet resultSet = QuerySender.filter(DatabaseNames.Client.pizzaCount, DatabaseNames.Tables.clients,
                        DatabaseNames.Client.clientID, Integer.toString(clientId));
                return UnpackObj.SingleValue.unpackPizzaCount(resultSet);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Pizza count selection failed");
                return 0;
            }
        }
        // endregion

        // region Calculate
        // ___________________________________________________________________________________
        public static float calculateMenuItemPrice(int menuItemId) {
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(menuItemId);

            float totalPrice = 0f;
            for (Ingredient ingredient : ingredients) {
                totalPrice += ingredient.price;
            }
            return totalPrice;
        }

        public static boolean calculateMenuItemIsVegetarian(int menuItemId) {
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(menuItemId);

            for (Ingredient ingredient : ingredients) {
                if (!ingredient.isVegetarian) {
                    return false;
                }
            }
            return true;
        }

        // endregion

        // region Insert
        // ___________________________________________________________________________________
        public static void insertOrderItemPair(int orderId, int menuItemId) {
            String[] names = { DatabaseNames.OrderItems.orderID, DatabaseNames.OrderItems.menuItemID };
            String[] values = { Integer.toString(orderId), Integer.toString(menuItemId) };

            try {
                insert(DatabaseNames.Tables.orderItems, names, values);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order item pair insertion failed");
            }
        }

        public static void insertOrder(Order order) {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            String[] names = { DatabaseNames.Order.courierID, DatabaseNames.Order.orderDate, DatabaseNames.Order.price,
                    DatabaseNames.Order.orderStatus, DatabaseNames.Order.clientID };
            String[] values = { Integer.toString(-1), date.toString(), Float.toString(order.price),
                    Order.Status.ORDER_SENT.toString(), Integer.toString(order.clientID) };

            try {
                insert(DatabaseNames.Tables.orders, names, values);
                for (MenuItem menuItem : order.menuItems) {
                    insertOrderItemPair(order.orderID, menuItem.menuItemID);
                }
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order insertion failed");
            }
        }

        public static void insertMenuIngredientPair(int menuItemId, int ingredientId) {
            String[] names = { DatabaseNames.MenuItemIngredient.menuItemID,
                    DatabaseNames.MenuItemIngredient.ingredientID };
            String[] values = { Integer.toString(menuItemId), Integer.toString(ingredientId) };

            try {
                insert(DatabaseNames.Tables.foodIngredients, names, values);
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Menu ingredient pair insertion failed");
            }
        }

        public static void insertMenuItem(MenuItem menuItem) {
            String[] names = { DatabaseNames.MenuItem.foodName, DatabaseNames.MenuItem.isVegetarian,
                    DatabaseNames.MenuItem.price };
            String[] values = { menuItem.name, HelperMethods.parseBoolean(menuItem.isVegetarian),
                    Float.toString(menuItem.price) };

            try {
                insert(DatabaseNames.Tables.menuItems, names, values);
                for (Ingredient ingredient : menuItem.ingredients) {
                    insertMenuIngredientPair(menuItem.menuItemID, ingredient.ingredientID);
                }
            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient insertion failed");
            }
        }

        public static void insertIngredient(Ingredient ingredient) {
            String[] names = { DatabaseNames.Ingredient.ingredientName,
                    DatabaseNames.Ingredient.isVegetarian, DatabaseNames.Ingredient.price };
            String[] values = { ingredient.name, HelperMethods.parseBoolean(ingredient.isVegetarian),
                    Float.toString(ingredient.price) };

            try {
                insert(DatabaseNames.Tables.ingredients, names, values);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Ingredient insertion failed");
            }
        }

        public static void insertClient(Client client) {
            String[] names = { DatabaseNames.Client.clientName,
                    DatabaseNames.Client.phoneNumber, DatabaseNames.Client.address, DatabaseNames.Client.pizzaCount };
            String[] values = { client.name,
                    Integer.toString(client.phoneNumber), client.adress, "0" };

            try {
                insert(DatabaseNames.Tables.clients, names, values);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Client insertion failed");
            }
        }

        public static void insertCourier(Courier courier) {
            String[] names = { DatabaseNames.Courier.isAvailable,
                    DatabaseNames.Courier.postCode };
            String[] values = { HelperMethods.parseBoolean(courier.isAvailable), courier.postCode };

            try {
                insert(DatabaseNames.Tables.clients, names, values);

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Courier insertion failed");
            }
        }
        // endregion

        // region Delete
        // ___________________________________________________________________________________
        public static boolean deleteOrder(int orderId) {
            try {
                return delete(DatabaseNames.Tables.orders, DatabaseNames.Order.orderID, Integer.toString(orderId));

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order deletion failed");
                return false;
            }
        }
        // endregion

        // region Update
        // ___________________________________________________________________________________
        public static void updateCourierAvailability(int courierId, boolean isAvailable) {
            String[] names = { DatabaseNames.Courier.isAvailable };
            String[] values = { HelperMethods.parseBoolean(isAvailable) };

            try {
                update(DatabaseNames.Tables.couriers, names, values, DatabaseNames.Courier.courierID,
                        Integer.toString(courierId));

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Courier availability update failed");
            }
        }

        public static void updateOrderState(int orderId, Order.Status status) {
            String[] names = { DatabaseNames.Order.orderStatus };
            String[] values = { status.toString() };

            try {
                update(DatabaseNames.Tables.orders, names, values, DatabaseNames.Order.orderID,
                        Integer.toString(orderId));

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Order state update failed");
            }
        }

        public static void updateMenuItemPrice(int menuItemId, float newPrice) {
            String[] names = { DatabaseNames.MenuItem.price };
            String[] values = { Float.toString(newPrice) };

            try {
                update(DatabaseNames.Tables.menuItems, names, values, DatabaseNames.MenuItem.menuItemID,
                        Integer.toString(menuItemId));

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Menu item price update failed");
            }
        }

        public static void updateMenuItemIsVegetarian(int menuItemId, boolean isVegetarian) {
            String[] names = { DatabaseNames.MenuItem.isVegetarian };
            String[] values = { HelperMethods.parseBoolean(isVegetarian) };

            try {
                update(DatabaseNames.Tables.menuItems, names, values, DatabaseNames.MenuItem.menuItemID,
                        Integer.toString(menuItemId));

            } catch (ConnectException e) {
                e.printStackTrace();
                System.out.println("Menu item isVegetarian update failed");
            }
        }

        public static void recalculateMenuItems() {
            ArrayList<MenuItem> menu = QuerySender.List.selectMenu();
            for (MenuItem menuItem : menu) {
                int id = menuItem.menuItemID;
                float price = QuerySender.SingleValue.calculateMenuItemPrice(id);
                boolean isVegetarian = QuerySender.SingleValue.calculateMenuItemIsVegetarian(id);

                QuerySender.SingleValue.updateMenuItemPrice(id, price);
                QuerySender.SingleValue.updateMenuItemIsVegetarian(id, isVegetarian);
            }
        }

        // endregion
    }

    // region Helper methods
    static void update(String to, String[] names, String[] values, String filterName, String filterValue)
            throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn
                    .prepareStatement(prepareUpdateCommand(names, values, to, filterName, filterValue));

            prepStatement.execute();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
        }
    }

    private static String prepareUpdateCommand(String[] names, String[] values, String to, String filterName,
            String filterValue) {
        if (names.length != values.length) {
            System.out.println("ERROR: The count of names and values must be the same!");
            return "";
        }
        filterName = sanitize(filterName);
        filterValue = sanitize(filterValue);

        String command = "UPDATE " + sanitize(to) + " SET ";
        for (int i = 0; i < names.length; i++) {
            command = command + sanitize(names[i]) + " = '" + sanitize(values[i]) + "'";
            if (i != names.length - 1) {
                command = command + ", ";
            }
        }
        command = command + " WHERE " + filterName + " = " + filterValue + ";";
        return command;
    }

    static boolean delete(String from, String filterName, String filterValue) throws ConnectException {
        from = sanitize(from);
        filterName = sanitize(filterName);
        filterValue = sanitize(filterValue);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn
                    .prepareStatement("DELETE FROM " + from + " WHERE " + filterName + " = " + filterValue + ";");

            prepStatement.execute();
            return true;

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
            return false;
        }
    }

    static void insert(String to, String[] names, String[] values) throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement(prepareInsertCommand(names, values, to));

            prepStatement.execute();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
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
        command = command + ") VALUES ('";

        for (int i = 0; i < values.length; i++) {
            command = command + sanitize(values[i]) + "'";
            if (i != values.length - 1) {
                command = command + ", '";
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

    static ResultSet filter(String selectColumn, String from, String filterName, String filterValue)
            throws ConnectException {
        selectColumn = sanitize(selectColumn);
        from = sanitize(from);
        filterName = sanitize(filterName);
        filterValue = sanitize(filterValue);
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

    static ResultSet join(String selectColumn, String from, String join, String using, String filterName,
            int filterValue)
            throws ConnectException {
        selectColumn = sanitize(selectColumn);
        from = sanitize(from);
        filterName = sanitize(filterName);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            PreparedStatement prepStatement = conn.prepareStatement("SELECT " + selectColumn + " FROM " + from
                    + " JOIN " + join + " USING (" + using + ") WHERE " + filterName + " = " + filterValue + ";");

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

    static String sanitize(String query) {
        // System.out.println("input: " + query);
        char remove = '"';
        char remove2 = '\\';
        char remove3 = ',';
        char insert = ' ';
        String sanitized = query.replace(remove, insert).replace(remove2, insert).replace(remove3, insert);
        // System.out.println("output: " + sanitized);
        return sanitized;
    }
    // endregion
}
