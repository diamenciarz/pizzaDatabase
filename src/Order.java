import java.sql.Date;
import java.util.ArrayList;

public class Order {
    public ArrayList<MenuItem> menuItems;
    public float price;
    public int orderID;
    public int clientID;
    public int courierID;
    public Status status;
    public Date orderDate;

    public enum Status {
        ORDER_SENT,
        PREAPERING,
        DELIVERING,
        DELIVERED
    }

    public Order() {

    }

    public Order(ArrayList<MenuItem> menuItems, float price, int orderID, int clientID, int courierID,
            Status status, Date orderDate) {
        this.menuItems = menuItems;
        this.price = price;
        this.orderID = orderID;
        this.clientID = clientID;
        this.courierID = courierID;
        this.orderDate = orderDate;
        this.status = Status.ORDER_SENT;// is it right to assume that on order creation it is already sent to the
                                        // kitchen, which is represented by ORDER_SENT
    }
}
