package NormalClasses;

public class DatabaseNames {
    public static class Tables {
        public static String ingredients = "Ingredients";
        public static String menuItems = "MenuItems";
        public static String orders = "Orders";
        public static String clients = "Clients";
        public static String codes = "Codes";
        public static String couriers = "Couriers";
        public static String foodIngredients = "FoodIngredients";
        public static String orderItems = "OrderItems";

    }

    public static class Ingredient {
        public static String ingredientID = "IngredientID";
        public static String ingredientName = "Name";
        public static String price = "Price";
        public static String isVegetarian = "IsVegetarian";

    }

    public static class MenuItem {
        public static String menuItemID = "MenuItemID";
        public static String foodName = "Name";
        public static String price = "Price";
        public static String isVegetarian = "IsVegetarian";
    }

    public static class Order {
        public static String orderID = "OrderID";
        public static String clientID = "ClientID";
        public static String courierID = "CourierID";
        public static String orderStatus = "OrderStatus";
        public static String orderDate = "OrderDate";
        public static String price = "Price";
    }

    public static class Client {
        public static String clientID = "ClientID";
        public static String clientName = "Name";
        public static String phoneNumber = "PhoneNumber";
        public static String address = "Address";
        public static String pizzaCount = "PizzaCount";
    }

    public static class Code {
        public static String clientID = "ClientID";
        public static String discountCode = "DiscountCode";
        public static String isUsed = "IsUsed";
    }

    public static class Courier {
        public static String courierID = "CourierID";
        public static String postCode = "PostCode";
        public static String isAvailable = "IsAvailable";
    }
    public static class MenuItemIngredient {
        public static String menuItemID = "MenuItemID";
        public static String ingredientID = "IngredientID";
    }
    public static class OrderItems {
        public static String orderID = "OrderID";
        public static String menuItemID = "MenuItemID";
    }

}