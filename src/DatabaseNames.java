
public class DatabaseNames {
    public static class Tables {
        public static String ingredients = "Ingredients";
        public static String menuItems = "MenuItems";
        public static String orders = "Orders";
        public static String clients = "Clients";
        public static String codes = "Codes";
        public static String couriers = "Couriers";
        public static String FoodIngredients = "FoodIngredients";
        public static String OrderItems = "OrderItems";

    }

    public static class IngredientKeys {
        public static String ingredientID = "IngredientID";
        public static String ingredientName = "Name";
        public static String price = "Price";
        public static String isVegetarian = "Isvegetarian";

    }

    public static class MenuItemsKeys {
        public static String menuItemID = "MenuItemID";
        public static String foodName = "Name";
        public static String price = "Price";
        public static String isVegetarian = "IsVegetarian";
    }

    public static class OrderKeys {
        public static String orderID = "OrderID";
        public static String clientID = "ClientID";
        public static String courierID = "CourierID";
        public static String orderStatus = "OrderStatus";
        public static String orderDate = "OrderDate";
        public static String price = "Price";
    }

    public static class ClientKeys {
        public static String clientID = "ClientID";
        public static String clientName = "Name";
        public static String phoneNumber = "PhoneNumber";
        public static String address = "Address";
        public static String pizzaCount = "PizzaCount";
    }

    public static class CodeKeys {
        public static String clientID = "ClientID";
        public static String discountCode = "DiscountCode";
        public static String isUsed = "IsUsed";
    }

    public static class CourierKeys {
        public static String courierID = "CourierID";
        public static String postCode = "PostCode";
        public static String isAvailable = "IsAvailable";
    }
    public static class MenuItemIngredientList {
        public static String foodID = "MenuItem";
        public static String postCode = "IngredientID";
    }
    public static class OrderItemsList {
        public static String courierID = "CourierID";
        public static String postCode = "PostCode";
        public static String isAvailable = "IsAvailable";
    }

}
