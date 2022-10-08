public class Server {

    enum Status {
        PREPARED,
        COOKING,
        DELIVERING
    }


    public class UserMethods {

        public static String[] getMenu() {
            return 
        }

        public static void getPizzaProgress(int orderID) {

        }

        public static Order getOrderInfo(int orderID) {
            
            QuerySender.execute(null, null)
            Order o  = new Order(); // Get the data from MySQL
            o.id =  orderID;
            o.price = 
        }

        public static void placeOrder(Order order, User user) {

        }

        public static void cancel(Order order) {
        }

        public static void getPizzaCount() {

        }

        public static User addUser() {

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
