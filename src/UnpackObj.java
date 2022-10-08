import java.sql.ResultSet;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Ingredient> unpackOrders(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("orderID", resultSet);
            ArrayList<String> name = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans("isVegetarian", resultSet);

            ArrayList<Ingredient> menuItems = new ArrayList<>(iDs.size());
            for (int i = 0; i < menuItems.size(); i++) {
                menuItems.set(i, new Ingredient(d));
            }
            return menuItems;
        }
        public static ArrayList<Ingredient> unpackMenuItems(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("menuItemID", resultSet);
            ArrayList<String> name = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans("isVegetarian", resultSet);

            ArrayList<Ingredient> ingredients = new ArrayList<>(iDs.size());
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, new Ingredient(d));
            }
            return ingredients;
        }
        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("ingredientID", resultSet);
            ArrayList<String> name = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBooleans("isVegetarian", resultSet);

            ArrayList<Ingredient> ingredients = new ArrayList<>(iDs.size());
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, new Ingredient(d));
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
            return new Order(d);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.SingleValue.readInt("menuItemID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(id);

            return new MenuItem(d);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer iDs = ResultSetReader.SingleValue.readInt("ingredientID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);
            return new Ingredient(d);
        }
    }

}
