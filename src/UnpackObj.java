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
                    ArrayList<MenuItem> menuItems = QuerySender.selectMenuItemsBelongingTo(iDs);
                    iDs = ResultSetReader.readInts(DatabaseNames.Order.orderID, resultSet);
                    clientIDs = ResultSetReader.readInts(DatabaseNames.Order.clientID, resultSet);
                    courierIDs = ResultSetReader.readInts(DatabaseNames.Order.courierID, resultSet);
                    prices = ResultSetReader.readFloats(DatabaseNames.Order.price, resultSet);
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
                    iDs = ResultSetReader.readInts(DatabaseNames.MenuItems.menuItemID, resultSet);
                    names = ResultSetReader.readStrings(DatabaseNames.MenuItems.foodName, resultSet);
                    prices = ResultSetReader.readFloats(DatabaseNames.MenuItems.price, resultSet);
                    areVegetarian = ResultSetReader.readBooleans(DatabaseNames.MenuItems.isVegetarian, resultSet);
                    ArrayList<Ingredient> ingredients = QuerySender.selectIngredientsBelongingTo(iDs);
                    
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
                    iDs = ResultSetReader.readInt(DatabaseNames.Ingredient.ingredientID, resultSet);
                    names = ResultSetReader.readString(DatabaseNames.Ingredient.ingredientName, resultSet);
                    prices = ResultSetReader.readFloat(DatabaseNames.Ingredient.price, resultSet);
                    areVegetarian = ResultSetReader.readBoolean(DatabaseNames.Ingredient.isVegetarian,
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
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.Order.orderID, resultSet);
            Integer clientID = ResultSetReader.SingleValue.readInt(DatabaseNames.Order.clientID, resultSet);
            Integer courierID = ResultSetReader.SingleValue.readInt(DatabaseNames.Order.courierID, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.Order.price, resultSet);
            Date date = ResultSetReader.SingleValue.readDate("orderDate", resultSet);
            ArrayList<MenuItem> menuItems = QuerySender.selectMenuItemsBelongingTo(id);

            // TODO: read date
            return new Order(menuItems, price, id, clientID, courierID, Order.Status.ORDER_SENT, date);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.MenuItems.menuItemID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.MenuItems.foodName, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.MenuItems.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.MenuItems.isVegetarian,
                    resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.selectIngredientsBelongingTo(id);

            return new MenuItem(id, name, price, isVegetarian, ingredients);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.Ingredient.ingredientID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.Ingredient.ingredientName,
                    resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.Ingredient.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.Ingredient.isVegetarian,
                    resultSet);

            return new Ingredient(id, name, price, isVegetarian);
        }
    }

}
