import java.sql.ResultSet;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Order> unpackOrders(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("orderID", resultSet);
            ArrayList<Integer> clientIDs = ResultSetReader.List.readInts("clientID", resultSet);
            ArrayList<Integer> courierIDs = ResultSetReader.List.readInts("courierID", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);

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
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("menuItemID", resultSet);
            ArrayList<String> names = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans("isVegetarian", resultSet);

            ArrayList<MenuItem> menuItems = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(iDs.get(i));
                menuItems.set(i,
                        new MenuItem(iDs.get(i), names.get(i), prices.get(i), areVegetarian.get(i), ingredients));
            }
            return menuItems;
        }

        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("ingredientID", resultSet);
            ArrayList<String> names = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans("isVegetarian", resultSet);

            ArrayList<Ingredient> ingredients = new ArrayList<>(iDs.size());
            for (int i = 0; i < iDs.size(); i++) {
                ingredients.set(i, new Ingredient(iDs.get(i), names.get(i), prices.get(i), areVegetarian.get(i)));
            }
            return ingredients;
        }
    }

    public static class SingleValue {
        public static Order unpackOrder(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt("orderID", resultSet);
            Integer clientID = ResultSetReader.SingleValue.readInt("clientID", resultSet);
            Integer courierID = ResultSetReader.SingleValue.readInt("courierID", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(id);

            // TODO: read date
            return new Order(menuItems, price, id, clientID, courierID, Order.Status.ORDER_SENT, null);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt("menuItemID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(id);

            return new MenuItem(id, name, price, isVegetarian, ingredients);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt("ingredientID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);

            return new Ingredient(id, name, price, isVegetarian);
        }
    }

}
