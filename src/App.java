import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Startup.startup();

       ArrayList<Ingredient> TMP =Server.UserMethods.getMenu();
       for (int i = 0; i < TMP.size(); i++) {
        System.out.println(TMP.get(i).ingredientID+" "+TMP.get(i).name+" "+TMP.get(i).price);
       }



    }

    // Getters
    public void DispalyOrderInfo(int order_ID){
        Order order=Server.UserMethods.getOrderInfo(order_ID);
        System.out.println("Client_ID "+order.clientID);
            System.out.println("Courier_ID "+order.courierID);
            System.out.println("Order_ID "+order.orderID);
            System.out.println("price "+order.price+"Eur");
            System.out.println("Your order "+order.menuItems);
            System.out.println("Order Date "+order.orderDate);
            System.out.println("Order status"+order.status);
    }
    private static void getHelp() {
        System.out.println("You can call methods:");
        System.out.println("getMenu");
        System.out.println("addItem <id>");


        // if the scanner receives "getMenu"
        //Then it asks for menu from the server
        // You can order:
        // Cola - <id> = 1
        // Tea - <id> = 2
        // Pizza pepperoni - <id> = 3
        // To finish call "send"

        //User says "addItem 2"
    }

    // Mutators
    private static void placeOrder() {
        //Server.placeOrder(null, null);
    }

    private static void cancelOrder() {

    }
}
