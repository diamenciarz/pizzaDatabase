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
            QuerySender.SingleValue.insertOrder(order);
        }

        public static Order getOrder(int orderID) {
            return QuerySender.SingleValue.selectOrder(orderID);
        }
        // TODO: handle codes

        /**
         * @param orderID
         * @return true, if the cancel succeded and false, if the cancel was set more
         *         than 5 minutes ago
         */
        public static boolean cancelOrder(int orderID, int clientID) {
            Order order = QuerySender.SingleValue.selectOrder(orderID);
            if (clientID == order.clientID) {
                if (HelperMethods.isCancelPossible(order)) {
                    System.out.println("Cancel succeeded");
                    return QuerySender.SingleValue.deleteOrder(orderID);
                }
            }
            System.out.println("Cancel not succeeded");
            return false;
        }

        public static int getPizzaCount(int clientID) {
            return QuerySender.SingleValue.getPizzaCount(clientID);
        }

        public static void addClient(Client client) {
            QuerySender.SingleValue.insertClient(client);
        }

        public static String getDeliveryStatus(int orderID) {
            return QuerySender.SingleValue.selectDeliveryStatus(orderID);
        }
    }

    public static class AdminMethods {
        public static Client getClientInfo(int clientID) {
            return QuerySender.SingleValue.selectClient(clientID);
        }

        public static Order[] getCurrentOrders() {
            Order[] orders = new Order[0];
            return QuerySender.List.selectCurrentOrders().toArray(orders);
        }

        public static void recalculateMenuItems() {
            QuerySender.SingleValue.recalculateMenuItems();
        }

        public static void addCourier(Courier courier) {
            QuerySender.SingleValue.insertCourier(courier);
        }

        public static void addMenuItem(MenuItem menuItem) {
            QuerySender.SingleValue.insertMenuItem(menuItem);
        }

        public static void addIngredient(Ingredient ingredient) {
            QuerySender.SingleValue.insertIngredient(ingredient);
        }
    }

    public static class DelivererMethods {
        public static void setAvailableForDelivery(int delivererID) {
            QuerySender.SingleValue.updateCourierAvailability(delivererID, true);
        }

        public static String getClientAddress(int clientID) {
            Client client = QuerySender.SingleValue.selectClient(clientID);
            return client.adress;
        }

        public static void setDeliveryState(int delivererID, int orderID) {
            QuerySender.SingleValue.updateCourierAvailability(delivererID, false);
            QuerySender.SingleValue.updateOrderState(orderID, Order.Status.DELIVERING);

        }
        // We are going to work on verification if the program works and we have time
    }

}
