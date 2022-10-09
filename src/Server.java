import java.util.ArrayList;

public class Server {

    public static class UserMethods {

        public static ArrayList<MenuItem> getMenu() {
            // re-written into a transaction?
            ArrayList<String> foodName = QuerySender.List.selectStrings("Food_name", "Food_items");
            ArrayList<Boolean> vegetarian = QuerySender.List.selectBooleans("vegetarian", "Food_items");
            ArrayList<Integer> price = QuerySender.List.selectInts("price", "Food_items");
            for (int i = 0; i < foodName.size(); i++) {
                System.out.println(foodName.get(i) + "| " + "Vegetarian?: " + vegetarian.get(i) + "| " + price.get(i)
                        + "€" + "\n" + "-------------------------");
            }

            return menu;
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
