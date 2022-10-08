public class Server {

    enum Status{
        PREPARED,
        COOKING,
        DELIVERING        
    }
    enum AllowedMethods{
        INSERT,
        ADD,
        PLUS,
        MORE
    }


    public class UserMethods {

        public static String[] getMenu() {
            return 
        }

        public static void getPizzaProgress() {

        }

        public static Order getOrderInfo(int orderID) {
            
            Order o  = new Order();
            o.menuItems = 
            return 
        }

        public static void placeOrder(Order order, User user) {

        }
        public static void cancel(Order order){}
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
