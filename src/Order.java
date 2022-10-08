import java.sql.Date;
import java.util.ArrayList;

public class Order {
    public ArrayList<FoodItem> foodItems;
    public float price;
    public int Order_ID;
    public int Client_ID;
    public int Courier_ID;
    public Status status;
    public enum Status{
        ORDER_SENT,
        PREAPERING,
        DELIVERING,
        DELIVERED
    }
    public Date orderDate;
    public Order(ArrayList<FoodItem> foodItems, float price,int Order_ID, int Client_ID,int Courier_ID, Status status){
        this.foodItems=foodItems;
        this.price=price;
        this.Order_ID=Order_ID;
        this.Client_ID=Client_ID;
        this.Courier_ID=Courier_ID;
        this.status=status.ORDER_SENT;//is it right to assume that on order creation it is already sent to the kitchen, which is represented by ORDER_SENT
    }
}
