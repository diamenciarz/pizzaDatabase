import java.sql.Date;
import java.util.ArrayList;

public class Order {
    public ArrayList<FoodItem> foodtems;
    public double price;
    public int Order_ID;
    public int Client_ID;
    public int Courier_ID;
    enum status{
        ORDER_SENT,
        PREAPERING,
        DELIVERING,
        DELIVERED
    }
    public Date orderDate;
}
