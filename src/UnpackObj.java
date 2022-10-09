import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Order> unpackOrders(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInt(DatabaseNames.Order.orderID, resultSet);
            ArrayList<Integer> clientIDs = ResultSetReader.List.readInt(DatabaseNames.Order.clientID, resultSet);
            ArrayList<Integer> courierIDs = ResultSetReader.List.readInt(DatabaseNames.Order.courierID, resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloat(DatabaseNames.Order.price, resultSet);

            ArrayList<Order> orders = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(iDs.get(i));

                // TODO: Add date
                // TODO: Add orderStatus
                orders.set(i, new Order(menuItems, prices.get(i), iDs.get(i), clientIDs.get(i), courierIDs.get(i),
                        Order.Status.ORDER_SENT, null));
            }
            return orders;
        }

        public static ArrayList<MenuItem> unpackMenuItems(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInt(DatabaseNames.MenuItemsKeys.menuItemID, resultSet);
            ArrayList<String> names = ResultSetReader.List.readString(DatabaseNames.MenuItemsKeys.foodName, resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloat(DatabaseNames.MenuItemsKeys.price, resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List
                    .readBoolean(DatabaseNames.MenuItemsKeys.isVegetarian, resultSet);

            ArrayList<MenuItem> menuItems = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(iDs.get(i));
                menuItems.set(i,
                        new MenuItem(iDs.get(i), names.get(i), prices.get(i), areVegetarian.get(i), ingredients));
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
                    iDs = ResultSetReader.List.readInt(DatabaseNames.IngredientKeys.ingredientID, resultSet);
                    names = ResultSetReader.List.readString(DatabaseNames.IngredientKeys.ingredientName, resultSet);
                    prices = ResultSetReader.List.readFloat(DatabaseNames.IngredientKeys.price, resultSet);
                    areVegetarian = ResultSetReader.List.readBoolean(DatabaseNames.IngredientKeys.isVegetarian,
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
