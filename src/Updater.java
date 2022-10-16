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
            System.out.println("Delivered: " + order.orderID);
            return true;
        }
        return false;
    }

    private static boolean deliverOrder(Order order) {
        if (order.status == Order.Status.DELIVERING) {
            return true;
        }
        if (HelperMethods.isOrderFinished(order)) {
            QuerySender.SingleValue.updateOrderState(order.orderID, Order.Status.DELIVERING);
            System.out.println("Delivering: " + order.orderID);
            return true;
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
