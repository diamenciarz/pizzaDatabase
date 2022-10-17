import java.util.ArrayList;
import java.sql.*;
import objects.*;

public class App {
    public static void main(String[] args) {
        // Startup.startup();
       
        String tmp = "Button_4";
        int i =Integer.valueOf(tmp.replaceFirst("Button_", ""));
        System.out.println(i);
      
      
        
      
    }

    public static void placeOrder() {
        ArrayList<MenuItem> TMP = Server.UserMethods.getMenu();

        Order order = new Order();
        order.clientID = 1;
        order.price = 10;

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(TMP.get(0));
        menuItems.add(TMP.get(1));
        order.menuItems = menuItems;

        Server.UserMethods.placeOrder(order);
    }

    private static void cancelOrder() {
        Server.UserMethods.cancelOrder(1, 1);
    }

    // Getters
    public void DispalyOrderInfo(int order_ID) {
        Order order = Server.UserMethods.getOrder(order_ID);
        System.out.println("Client_ID " + order.clientID);
        System.out.println("Courier_ID " + order.courierID);
        System.out.println("Order_ID " + order.orderID);
        System.out.println("price " + order.price + "Eur");
        System.out.println("Your order " + order.menuItems);
        System.out.println("Order Date " + order.orderTimestamp);
        System.out.println("Order status" + order.status);
    }

    private static void getHelp() {
        System.out.println("You can call methods:");
        System.out.println("getMenu");
        System.out.println("addItem <id>");

        // if the scanner receives "getMenu"
        // Then it asks for menu from the server
        // You can order:
        // Cola - <id> = 1
        // Tea - <id> = 2
        // Pizza pepperoni - <id> = 3
        // To finish call "send"

        // User says "addItem 2"
    }
}