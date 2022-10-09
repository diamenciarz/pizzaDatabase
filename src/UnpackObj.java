import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Order> unpackOrders(ResultSet resultSet) {
            Integer iDs =0;//this is very balssy
            Integer clientIDs ;
            Integer courierIDs;
            Float prices;

            ArrayList<Order> orders = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(iDs);
                    iDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.orderID, resultSet);
                    clientIDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.clientID, resultSet);
                    courierIDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.courierID, resultSet);
                    prices = ResultSetReader.List.readFloats(DatabaseNames.OrderKeys.price, resultSet);
                    // TODO: Add date
                    // TODO: Add orderStatus
                    orders.add( new Order(menuItems, prices, iDs, clientIDs, courierIDs,
                            Order.Status.ORDER_SENT, null));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return orders;
        }

        public static ArrayList<MenuItem> unpackMenuItems(ResultSet resultSet) {
            Integer iDs ;
            String names ;
           Float prices;
            Boolean areVegetarian;
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            try {
                while(resultSet.next()) {
                    iDs = ResultSetReader.List.readInts(DatabaseNames.MenuItemsKeys.menuItemID, resultSet);
                    names = ResultSetReader.List.readStrings(DatabaseNames.MenuItemsKeys.foodName, resultSet);
                    prices = ResultSetReader.List.readFloats(DatabaseNames.MenuItemsKeys.price, resultSet);
                    areVegetarian = ResultSetReader.List.readBooleans(DatabaseNames.MenuItemsKeys.isVegetarian, resultSet);
                    ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(iDs);
                    
                    menuItems.add(
                            new MenuItem(iDs, names, prices, areVegetarian, ingredients));
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return menuItems;
        }

        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {

            String names;
            Integer iDs;
            Float prices;
            Boolean areVegetarian;

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    iDs = ResultSetReader.List.readInts(DatabaseNames.IngredientKeys.ingredientID, resultSet);
                    names = ResultSetReader.List.readStrings(DatabaseNames.IngredientKeys.ingredientName, resultSet);
                    prices = ResultSetReader.List.readFloats(DatabaseNames.IngredientKeys.price, resultSet);
                    areVegetarian = ResultSetReader.List.readBooleans(DatabaseNames.IngredientKeys.isVegetarian,
                            resultSet);
                    ingredients.add(new Ingredient(iDs, names, prices, areVegetarian));

                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ingredients;
        }
    }

    public static class SingleValue {
        public static Order unpackOrder(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.orderID, resultSet);
            Integer clientID = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.clientID, resultSet);
            Integer courierID = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.courierID, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.OrderKeys.price, resultSet);
            Date date = ResultSetReader.SingleValue.readDate("orderDate", resultSet);
            ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(id);

            // TODO: read date
            return new Order(menuItems, price, id, clientID, courierID, Order.Status.ORDER_SENT, date);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.MenuItemsKeys.menuItemID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.MenuItemsKeys.foodName, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.MenuItemsKeys.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.MenuItemsKeys.isVegetarian,
                    resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(id);

            return new MenuItem(id, name, price, isVegetarian, ingredients);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.IngredientKeys.ingredientID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.IngredientKeys.ingredientName,
                    resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.IngredientKeys.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.IngredientKeys.isVegetarian,
                    resultSet);

            return new Ingredient(id, name, price, isVegetarian);
        }
    }

}
