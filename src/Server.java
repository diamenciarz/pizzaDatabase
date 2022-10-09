import java.util.ArrayList;

public class Server {

    public static class UserMethods {

        public static ArrayList<MenuItem> getMenu() {
            return QuerySender.List.SelectMenu();
            
        }

        // Requires insert()
        public static void placeOrder(Order order, Client Client) {

        }

        public static void getOrderStatus(int orderID) {
            // TODO

        }

        public static Order getOrderInfo(int orderID) {
            return QuerySender.SingleValue.selectOrder(orderID);
        }

        public static void cancelOrder(int orderID) {

        }

        public int getPizzaCount(int clientID) {
            int pizzaCount = QuerySender.SingleValue.selectInt(, );
            return pizzaCount;
        }

        // Requires insert()
        public static void addClient(Client client) {

        }

        public static String getDeliveryInfo(int orderID) {

        }

        // !!!!!!!!!!!!
        public static void deliveryStatus(Status status) {

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
