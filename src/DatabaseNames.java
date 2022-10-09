
public class DatabaseNames {
    public static class Tables {
        public static String ingredients = "Ingredients";
        public static String menuItems = "MenuItems";
        public static String orders = "Orders";
        public static String clients = "Clients";
        public static String codes = "Codes";
        public static String couriers = "Couriers";
        public static String FoodIngredients ="FoodIngredients";
        public static String OrderItems ="OrderItems";
    
    }
    public static class IngredientKeys{
        public static String ingredientID = "ingredientID";
        public static String ingredientName= "IngredientName";
        public static String price= "price";
        public static String isvegetarian="isvegetarian"; 

    }
    public static class MenuItemsKeys{
        public static String MenuItemID = "FoodID";
        public static String FoodName="FoodName";
        public static String price ="price";
        public static String isvegetarian="isvegetarian"; 
    }
    public static class OrderKeys{
        public static String OrderID = "OrderID";
        public static String ClientID ="ClientID";
        public static String CourierID ="CourierID";
        public static String OrderStatus ="OrderStatus";
        public static String OrderDate ="OrderDate";
    }
    public static class ClientKeys{
        public static String ClientID = "ClientID";

    }
    public static class CourierKeys{
        public static String CourierID ="CourierID";
    }
    
}
