import java.sql.ResultSet;
import java.util.ArrayList;

public class UnpackObj {

    // SELECT * FROM orders;
    public static ArrayList<Order> unpackOrders(ResultSet resultSet){
        ArrayList<Integer> iDs = ResultSetReader.readInt("orderID", resultSet);
        ArrayList<Float> prices = ResultSetReader.readFloat("price", resultSet);
        ArrayList<Order> orders = new ArrayList<>(iDs.size());

        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).id = iDs.get(i);
            orders.get(i).price = prices.get(i);
            QuerySender.selectMenuItems()
        }
        return orders;
    }

    public static ArrayList<Order> unpackIngredients(ResultSet resultSet){
        ArrayList<Integer> iDs = ResultSetReader.readInt("ingredientID", resultSet);
        ArrayList<Float> prices = ResultSetReader.readFloat("price", resultSet);
        ArrayList<Order> orders = new ArrayList<>(iDs.size());

        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).id = iDs.get(i);
            orders.get(i).price = prices.get(i);
            QuerySender.selectIngredients();
        }
        return orders;
    }
}
