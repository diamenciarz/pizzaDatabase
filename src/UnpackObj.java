import java.sql.ResultSet;
import java.util.ArrayList;

public class UnpackObj {
    public static class List {
        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {
            ArrayList<Integer> iDs = ResultSetReader.List.readInts("ingredientID", resultSet);
            ArrayList<String> name = ResultSetReader.List.readStrings("name", resultSet);
            ArrayList<Float> prices = ResultSetReader.List.readFloats("price", resultSet);
            ArrayList<Boolean> areVegetarian = ResultSetReader.List.readBools("isVegetarian", resultSet);

            ArrayList<Ingredient> ingredients = new ArrayList<>(iDs.size());
            for (int i = 0; i < ingredients.size(); i++) {
                ingredients.set(i, new Ingredient(d));
            }
            return ingredients;
        }
    }

    public static class SingleValue {
        public static Order unpackOrder(ResultSet resultSet) {
            Integer iDs = ResultSetReader.SingleValue.readInt("ingredientID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);
            return new Order(d);
        }
        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer iDs = ResultSetReader.SingleValue.readInt("ingredientID", resultSet);
            String name = ResultSetReader.SingleValue.readString("name", resultSet);
            Float price = ResultSetReader.SingleValue.readFloat("price", resultSet);
            Boolean isVegetarian = ResultSetReader.SingleValue.readBoolean("isVegetarian", resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredients(name);

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
