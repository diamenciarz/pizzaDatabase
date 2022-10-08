import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Startup.startup();

        ArrayList<Integer> elements = QuerySender.selectInts("\"Client_ID\"", "\"Clients\"");
        for (int string : elements) {
            System.out.println("ID: " + string);
        }
        
        Server.getMenu();

        Order info = Server.getOrderInfo();
        System.out.println(info.price);




    }

    // Getters

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
        Server.placeOrder(null, null);
    }

    private static void cancelOrder() {

    }
}
