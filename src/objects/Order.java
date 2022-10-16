package objects;
import java.sql.*;
import java.util.ArrayList;

public class Order {
    public ArrayList<MenuItem> menuItems;
    public float price;
    public int orderID;
    public int clientID;
    /**
     * Not necessary from user. -1 means it was not sent yet
     */
    public int courierID;
    /**
     * Not necessary from user
     */
    public Status status;
    //enum('ORDER_SENT','PREPARING','DELIVERING','DELIVERED','CANCELLED')
    /**
     * Not necessary from user
     */
    public Timestamp orderTimestamp;
    public String code;

    public enum Status {
        ORDER_SENT,
        PREPARING,
        DELIVERING,
        DELIVERED,
        CANCELLED
    }

    public Order() {

    }

    public Order(ArrayList<MenuItem> menuItems, float price, int orderID, int clientID, int courierID,
            Status status, Timestamp orderTimestamp) {
        this.menuItems = menuItems;
        this.price = price;
        this.orderID = orderID;
        this.clientID = clientID;
        this.courierID = courierID;
        this.orderTimestamp = orderTimestamp;
        this.status = status;// is it right to assume that on order creation it is already sent to the
                                        // kitchen, which is represented by ORDER_SENT
    }
}
