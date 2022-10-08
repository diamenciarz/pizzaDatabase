import java.sql.Date;
import java.util.ArrayList;

public class Order {
    public int Order_ID;
    public int Client_ID;
    public int Courier_ID;
    public double price;
    public ArrayList<MenuItem> menuItems;

    enum status {
        ORDER_SENT,
        PREAPERING,
        DELIVERING,
        DELIVERED
    }

    public Date orderDate;
}
