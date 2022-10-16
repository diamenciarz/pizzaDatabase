import objects.*;
import java.sql.*;

public class HelperMethods {
    public static String parseBoolean(boolean bool) {
        if (bool == true) {
            return "1";
        }
        return "0";
    }

    public static boolean isCancelPossible(Order order) {
        long orderDate = order.orderTimestamp.getTime();
        long dateNow = System.currentTimeMillis();
        long delay = dateNow - orderDate;
        // final int MAX_DELAY = 300000; // 5 minutes
        final int MAX_DELAY = 10000; // 10 seconds

        if (delay > MAX_DELAY) {
            return false;
        }
        return true;
    }

    public static String generateCode() {
        long time = System.currentTimeMillis();
        long cutoff = time % 100000;
        String hash = Long.toString(cutoff);
        char[] code = new char[5];

        for (int i = 0; i < 5; i++) {
            code[i] = hash.charAt(i);
        }
        String finalCode = "";
        for (char c : code) {
            finalCode = finalCode + c;
        }
        return finalCode;
    }
}
