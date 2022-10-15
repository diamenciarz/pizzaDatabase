import java.net.ConnectException;
import java.util.ArrayList;

import objects.*;

public class Server {

    public static class UserMethods {

        public static ArrayList<MenuItem> getMenu() {

            return QuerySender.List.selectMenu();
        }

        // Requires insert()
        public static void placeOrder(Order order) {
            QuerySender.SingleValue.insertClient(order);
        }

        public static Order getOrderInfo(int orderID) {
            return QuerySender.SingleValue.selectOrder(orderID);
        }

        public static void cancelOrder(int orderID) {

        }

        public int getPizzaCount(int clientID) throws ConnectException {
            int pizzaCount = ResultSetReader.readInt(DatabaseNames.Client.pizzaCount,
                    QuerySender.filter(DatabaseNames.Client.pizzaCount, DatabaseNames.Tables.clients,
                            DatabaseNames.Client.clientID, clientID));
            return pizzaCount;
        }

        // Requires insert()
        public static void addClient(Client client) {
            QuerySender.SingleValue.insertClient(client);
        }

        public static String getDeliveryInfo(int orderID) {

        }

    }

    public static class AdminMethods {
        public static Client getClientInfo(int clientID) {
            return QuerySender.SingleValue.selectClient(clientID);
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
