import java.util.ArrayList;
import objects.*;
import java.sql.*;

public class Updater {
    static long time;

    public static void main(String[] args) {
        Startup.startup();
        App.placeOrder();
        time = System.currentTimeMillis();
        while (true) {
            if (shouldUpdate()) {
                time = System.currentTimeMillis();
                ArrayList<Order> orders = QuerySender.List.selectCurrentOrders();
                for (Order order : orders) {
                    if (order.status == Order.Status.CANCELLED || order.status == Order.Status.DELIVERED) {
                        continue;
                    }
                    if (!deliveredOrder(order)) {
                        if (!deliverOrder(order)) {
                            prepareOrder(order);
                        }
                    }
                }
            }
        }
    }

    private static boolean deliveredOrder(Order order) {
        if (order.status == Order.Status.DELIVERED) {
            return true;
        }
        if (HelperMethods.isOrderDelivered(order)) {
            QuerySender.SingleValue.updateOrderState(order.orderID, Order.Status.DELIVERED);
            Courier courier = QuerySender.SingleValue.findCourierForOrder(order.orderID);
            System.out.println("Delivered: " + order.orderID);

            QuerySender.SingleValue.addPizzaCount(order);
            QuerySender.SingleValue.updateCourierAvailability(courier.Courier_ID, true);
                QuerySender.SingleValue.updateOrderCourier(order, -1);
            return true;
        }
        return false;
    }

    private static boolean deliverOrder(Order order) {
        if (order.status == Order.Status.DELIVERING) {
            return true;
        }
        if (HelperMethods.isOrderFinished(order)) {

            Courier availableCourier = HelperMethods.findAvailableCourier(order);
            if (availableCourier != null) {
                QuerySender.SingleValue.updateOrderState(order.orderID, Order.Status.DELIVERING);
                System.out.println("Delivering: " + order.orderID);

                QuerySender.SingleValue.updateCourierAvailability(availableCourier.Courier_ID, false);
                QuerySender.SingleValue.updateOrderCourier(order, availableCourier.Courier_ID);
                return true;
            }
        }
        return false;
    }

    private static boolean prepareOrder(Order order) {
        if (order.status == Order.Status.PREPARING) {
            return true;
        }
        if (HelperMethods.isOrderBeingPrepared(order)) {
            QuerySender.SingleValue.updateOrderState(order.orderID, Order.Status.PREPARING);
            System.out.println("Preparing: " + order.orderID);
            return true;
        }
        return false;
    }

    private static boolean shouldUpdate() {
        long currentTime = System.currentTimeMillis();
        long deltaTime = currentTime - time;
        final long DELAY = 3000; // 3 second delay

        return deltaTime > DELAY;
    }
}
