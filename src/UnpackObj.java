import java.sql.ResultSet;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Order> unpackOrders(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.OrderID, resultSet);
            ArrayList<Integer> clientIDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.ClientID, resultSet);
            ArrayList<Integer> courierIDs = ResultSetReader.List.readInts(DatabaseNames.OrderKeys.CourierID, resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats(DatabaseNames.OrderKeys.price, resultSet);

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
            ArrayList<Integer> iDs = ResultSetReader.List.readInts(DatabaseNames.MenuItemsKeys.MenuItemID, resultSet);
            ArrayList<String> names = ResultSetReader.List.readStrings(DatabaseNames.MenuItemsKeys.FoodName, resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats(DatabaseNames.MenuItemsKeys.price, resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans(DatabaseNames.MenuItemsKeys.isvegetarian, resultSet);

            ArrayList<MenuItem> menuItems = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(iDs.get(i));
                menuItems.set(i,
                        new MenuItem(iDs.get(i), names.get(i), prices.get(i), areVegetarian.get(i), ingredients));
            }
            return menuItems;
        }

        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts(DatabaseNames.IngredientKeys.ingredientID, resultSet);
            ArrayList<String> names = ResultSetReader.List.readStrings(DatabaseNames.IngredientKeys.ingredientName, resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats(DatabaseNames.IngredientKeys.price, resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans(DatabaseNames.IngredientKeys.isvegetarian, resultSet);

            ArrayList<Ingredient> ingredients = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ingredients.set(i, new Ingredient(iDs.get(i), names.get(i), prices.get(i), areVegetarian.get(i)));
            }
            return ingredients;
        }
    }

    public static class SingleValue {
        public static Order unpackOrder(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.OrderID, resultSet);
            Integer clientID = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.ClientID, resultSet);
            Integer courierID = ResultSetReader.SingleValue.readInt(DatabaseNames.OrderKeys.CourierID, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.OrderKeys.price, resultSet);
            ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(id);

            // TODO: read date
            return new Order(menuItems, price, id, clientID, courierID, Order.Status.ORDER_SENT, null);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.MenuItemsKeys.MenuItemID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.MenuItemsKeys.FoodName, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.MenuItemsKeys.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.MenuItemsKeys.isvegetarian, resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(id);

            return new MenuItem(id, name, price, isVegetarian, ingredients);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt(DatabaseNames.IngredientKeys.ingredientID, resultSet);
            String name = ResultSetReader.SingleValue.readString(DatabaseNames.IngredientKeys.ingredientName, resultSet);
            Float price = ResultSetReader.SingleValue.readFloat(DatabaseNames.IngredientKeys.price, resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean(DatabaseNames.IngredientKeys.isvegetarian, resultSet);

            return new Ingredient(id, name, price, isVegetarian);
        }
    }

}
