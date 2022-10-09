import java.util.ArrayList;

public class Server {

   


    public class UserMethods {

        public static void getMenu() {
            //re-written into a transaction?
            ArrayList<String> foodName =QuerySender.List.selectStrings("Food_name", "Food_items");
            ArrayList<Boolean> vegetarian=QuerySender.List.selectBooleans("vegetarian", "Food_items");
            ArrayList<Integer> price=QuerySender.List.selectInts("price", "Food_items");
            for (int i = 0; i < foodName.size(); i++) {
                System.out.println(foodName.get(i)+"| "+"Vegetarian?: "+vegetarian.get(i)+"| "+price.get(i)+"€"+"\n"+"-------------------------");
            }
        }

        public static void getOrderStatus(int orderID) {


        }

        public static Order getOrderInfo(int orderID) {
            
            
        }

        public static void placeOrder(Order order, Client Client) {

        }

        public static void cancel(Order order) {
        }

        public static void getPizzaCount() {

        }

        public static Client addClient() {

        }

        public static String deliveryInfo() {

        }

        public static void deliveryStatus(Status status) {

        }

    }

    public class AdminMethods {
        public static Order getDeliveryStatus() {
            Order o = new Order();
            return o;
        }

        public static Order[] getCurrentOrders() {
            return null;
        }

    }

    public class DeliveryMethods {
        public static String getUserInfo(User user) {

        }

        public static void verification(String password) {
            deliveryStatus(AllowedMethods.MORE);
        }

        public static void deliveryStatus(AllowedMethods status) {
            System.out.println(status);
            if (status == AllowedMethods.INSERT) {

            }
        }
    }

}
