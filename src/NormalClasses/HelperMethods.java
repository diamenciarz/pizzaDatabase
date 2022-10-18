package NormalClasses;

import objects.*;
import objects.Order.Status;
import java.sql.*;
import java.util.ArrayList;

public class HelperMethods {
    public static String parseBoolean(boolean bool) {
        if (bool == true) {
            return "1";
        }
        return "0";
    }

    public static Courier findAvailableCourier(Order order) {
        Client client = QuerySender.SingleValue.selectClient(order.clientID);
        ArrayList<Courier> allCouriers = QuerySender.List.selectCouriersWithCode(client.postCode);
        System.out.println("All couriers: " + allCouriers.size());
        System.out.println("Is available: " + allCouriers.get(0).isAvailable);
        ArrayList<Courier> availableCouriers = new ArrayList<>();

        for (Courier courier : allCouriers) {
            if (courier.isAvailable) {
                availableCouriers.add(courier);
            }
        }
        System.out.println("Available couriers: " + availableCouriers.size());

        if (availableCouriers.size() == 0) {
            return null;
        }
        return availableCouriers.get(0);
    }

    public static int getPizzaNumber(Order order) {
        ArrayList<MenuItem> menuItems = order.menuItems;
        int number = 0;
        for (MenuItem menuItem : menuItems) {
            for (Ingredient ingredient : menuItem.ingredients) {
                boolean isPizza = ingredient.ingredientID == 2;
                if (isPizza) {
                    number++;
                    break;
                }
            }
        }
        return number;
    }

    public static boolean isOrderBeingPrepared(Order order) {
        long orderDate = order.orderTimestamp.getTime();
        long dateNow = System.currentTimeMillis();
        long delay = dateNow - orderDate;
        // final int MAX_DELAY = 300000; // 5 minutes
        final int MAX_DELAY = 3000; // 6 seconds

        if (delay > MAX_DELAY) {
            return true;
        }
        return false;
    }

    public static boolean isOrderFinished(Order order) {
        long orderDate = order.orderTimestamp.getTime();
        long dateNow = System.currentTimeMillis();
        long delay = dateNow - orderDate;
        // final int MAX_DELAY = 900000; // 15 minutes
        final int MAX_DELAY = 10000; // 10 seconds

        if (delay > MAX_DELAY) {
            return true;
        }
        return false;
    }

    public static boolean isOrderDelivered(Order order) {
        if (order.courierID == -1) {
            return false;
        }
        Courier courier = QuerySender.SingleValue.selectCourier(order.courierID);
        long dateNow = System.currentTimeMillis();
        long delay = dateNow - Updater.deliveryStartTimes.get(courier.Courier_ID - 1);
        // final int MAX_DELAY = 1200000; // 20 minutes
        final int MAX_DELAY = 10000; // 10 seconds

        if (delay > MAX_DELAY) {
            return true;
        }
        return false;
    }

    public static Order.Status translateToStatus(int i) {
        if (i == 0) {
            return Status.ORDER_SENT;
        }
        if (i == 1) {
            return Status.PREPARING;
        }
        if (i == 2) {
            return Status.DELIVERING;
        }
        if (i == 3) {
            return Status.DELIVERED;
        }
        return Status.CANCELLED;
    }

    public static int translateFromStatus(Order.Status status) {
        if (status == Status.ORDER_SENT) {
            return 0;
        }
        if (status == Status.PREPARING) {
            return 1;
        }
        if (status == Status.DELIVERING) {
            return 2;
        }
        if (status == Status.DELIVERED) {
            return 3;
        }
        return 4;
    }

    public static String generateCode() {
        long time = System.currentTimeMillis();
        long time2 = System.currentTimeMillis() % 100000;
        time *= time2;
        String hash = Long.toString(time);
        char[] code = new char[15];

        for (int i = 0; i < 15; i++) {
            code[i] = getChar(hash.charAt(i));
        }
        String finalCode = "";
        for (char c : code) {
            finalCode = finalCode + c;
        }
        return finalCode;
    }

    private static char getChar(char s) {
        if (s == '0') {
            return 'S';
        }
        if (s == '1') {
            return 'Z';
        }
        if (s == '2') {
            return 'P';
        }
        if (s == '3') {
            return 'X';
        }
        if (s == '4') {
            return 'G';
        }
        if (s == '5') {
            return 'H';
        }
        if (s == '6') {
            return 'J';
        }
        if (s == '7') {
            return 'K';
        }
        if (s == '8') {
            return 'L';
        }
        return 'A';
    }
}
