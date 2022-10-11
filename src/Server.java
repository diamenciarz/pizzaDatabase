import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

import objects.Client;
import objects.Ingredient;
import objects.Order;

public class Server {

    public static class UserMethods {

        public static ArrayList<Ingredient> getMenu() {

            return QuerySender.List.selectIngredients();
        }

        // Requires insert()
        public static void placeOrder(Order order, Client Client) {

        }

        public static Order getOrderInfo(int orderID) {
            return QuerySender.SingleValue.selectOrder(orderID);
        }

        public static void cancelOrder(int orderID) {

        }

        public int getPizzaCount(int clientID) throws ConnectException {
            int pizzaCount = ResultSetReader.SingleValue.readInt(DatabaseNames.ClientKeys.pizzaCount,
                    QuerySender.filter(DatabaseNames.ClientKeys.pizzaCount, DatabaseNames.Tables.clients,
                            DatabaseNames.ClientKeys.clientID, clientID));
            return pizzaCount;
        }

        // Requires insert()
        public static void addClient(Client client) {

        }

        public static String getDeliveryInfo(int orderID) {

        }

    }

    public static class AdminMethods {
        public static Client getClientInfo(int clientID) {

        }

        public static Order[] getCurrentOrders() {
        }

    }

    public static class DelivererMethods {
        public static void setAvailableForDelivery(int delivererID) {

        }

        public static Client getClientInfo(int clientID) {

        }

        public static void setDeliveryState(int orderID) {

        }
        // We are going to work on verification if the program works and we have time
        // public static void verification(String password) {
        // deliveryStatus(AllowedMethods.MORE);
        // }
    }

}
