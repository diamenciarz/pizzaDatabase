import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Startup.startup();

        ArrayList<Integer> elements = QuerySender.selectInt("Client_ID", "Clients");
        for (int string : elements) {
            System.out.println("ID: " + string);
        }
    }
}
