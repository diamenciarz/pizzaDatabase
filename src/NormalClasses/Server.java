package NormalClasses;
import java.util.ArrayList;

import objects.*;
import objects.Order.Status;

public class Server {

    public static class UserMethods {

        public static ArrayList<MenuItem> getMenu() {

            return QuerySender.List.selectMenu();
        }

        public static void placeOrder(Order order) {
            order.price = calculateOrderPrice(order);
            QuerySender.SingleValue.insertOrder(order);
        }

        public static Order getOrder(int orderID) {
            return QuerySender.SingleValue.selectOrder(orderID);
        }
        public static ArrayList<String> getCodes(int clientId){
            ArrayList<String> codes = QuerySender.List.selectDiscountCodes(clientId);
            return codes;
        }

        /**
         * @param orderID
         * @return true, if the cancel succeded and false, if the cancel was set more
         *         than 5 minutes ago
         */
        public static boolean cancelOrder(int orderID, int clientID) {
            Order order = QuerySender.SingleValue.selectOrder(orderID);
            if (clientID == order.clientID) {
                boolean isCancelPossible = !HelperMethods.isOrderBeingPrepared(order);
                if (isCancelPossible) {
                    System.out.println("Cancel succeeded");
                    // The order's status is set to cancelled
                    QuerySender.SingleValue.updateOrderState(orderID, Status.CANCELLED);
                    return true;
                    // return QuerySender.SingleValue.deleteOrder(orderID); // The order is deleted
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

        private static float calculateOrderPrice(Order order) {
            float totalPrice = 0f;
            for (MenuItem menuItem : order.menuItems) {
                totalPrice += menuItem.price;
            }
            // Apply discount
            ArrayList<String> codes = QuerySender.List.selectDiscountCodes(order.clientID);
            if (codes.contains(order.code)) {
                totalPrice *= 0.9f;
                QuerySender.SingleValue.updateCodeAsUsed(order.code);
            }
            return totalPrice;
        }
    }

    public static class AdminMethods {
        public static final float RESTAURANT_MARGIN = 1.4f;

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
            return client.postCode;
        }

        public static void setDeliveryState(int delivererID, int orderID) {
            QuerySender.SingleValue.updateCourierAvailability(delivererID, false);
            QuerySender.SingleValue.updateOrderState(orderID, Order.Status.DELIVERING);

        }
        // We are going to work on verification if the program works and we have time
    }

}