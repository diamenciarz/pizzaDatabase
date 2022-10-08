import java.sql.Date;
import java.util.ArrayList;

public class Order {
    public ArrayList<MenuItem> menuItems;
    public float price;
    public int Order_ID;
    public int Client_ID;
    public int Courier_ID;
    public Status status;
    public Date orderDate;

    public enum Status {
        ORDER_SENT,
        PREAPERING,
        DELIVERING,
        DELIVERED
    }


    public Order(ArrayList<MenuItem> menuItems, float price, int Order_ID, int Client_ID, int Courier_ID,
            Status status, Date orderDate) {
        this.menuItems = menuItems;
        this.price = price;
        this.Order_ID = Order_ID;
        this.Client_ID = Client_ID;
        this.Courier_ID = Courier_ID;
        this.orderDate = orderDate;
        this.status = Status.ORDER_SENT;// is it right to assume that on order creation it is already sent to the
                                        // kitchen, which is represented by ORDER_SENT
    }
}
