package NormalClasses;
import java.sql.*;

public class Startup {
    public static void startup() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            Statement statement = conn.createStatement();

            // Reset database
            statement.execute("DROP DATABASE IF EXISTS pizza;");
            statement.execute("CREATE DATABASE pizza;");
            statement.execute("USE pizza;");

            // Create tables
            statement.execute(
                    "CREATE TABLE Ingredients(IngredientID int NOT NULL AUTO_INCREMENT,Name varchar(20),Price FLOAT,IsVegetarian boolean, PRIMARY KEY (IngredientID));");
            statement.execute(
                    "CREATE TABLE MenuItems(MenuItemID int NOT NULL AUTO_INCREMENT,Name varchar(20),Price FLOAT, IsVegetarian boolean, PRIMARY KEY (MenuItemID));");
            statement.execute(
                    "CREATE TABLE Orders(OrderID int NOT NULL AUTO_INCREMENT, ClientID int, CourierID int, OrderStatus INT,OrderDate timestamp,Price int, PRIMARY KEY (OrderID));");
            statement.execute(
                    "CREATE TABLE Clients(ClientID int NOT NULL AUTO_INCREMENT, Name varchar(20), PhoneNumber int, Address varchar(20), PizzaCount int, PRIMARY KEY (ClientID));");
            statement.execute("CREATE TABLE Codes(ClientID int,DiscountCode varchar(20),IsUsed boolean);");
            statement.execute("CREATE TABLE Couriers(CourierID int NOT NULL AUTO_INCREMENT,PostCode varchar(7),IsAvailable boolean, PRIMARY KEY (CourierID));");
            statement.execute("CREATE TABLE FoodIngredients(MenuItemID int, IngredientID int);");
            statement.execute("CREATE TABLE OrderItems(OrderID int, MenuItemID int);");

            // Fill tables with examples
            
            statement.execute("INSERT INTO Codes (ClientID, DiscountCode, IsUsed) VALUES (2, 'TOPG', False);");
            statement.execute("INSERT INTO Codes (ClientID, DiscountCode, IsUsed) VALUES (2, 'DASGASG', False);");
            statement.execute("INSERT INTO Couriers (PostCode, IsAvailable) VALUES ('3620', True);");
            statement.execute("INSERT INTO Couriers (PostCode, IsAvailable) VALUES ('3621', True);");
            
            statement.execute("INSERT INTO Clients (Name, PhoneNumber, Address, PizzaCount) VALUES ('bruh',666,'3620',4);");
            statement.execute("INSERT INTO Clients (Name, PhoneNumber, Address, PizzaCount) VALUES ('bruh',999,'3621',9);");
            
            //Ingredients
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('cheese',0.7, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('dough',1.7, True); ");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('pizza sauce',0.1, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('pineapples',0.8, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('ham',0.5, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('shrimp',1.3, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('olives',0.2, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('bell pepper',0.2, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('tofu',2, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('champignons ',0.9, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('tomatoes',0.4, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('Prosciutto',2.5, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('mozzarella',1.1, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('basil',2.5, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('San Marzano tomatoes',5.5, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('BBQ sauce',0.6, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('onion',0.1, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('anchovies',2.5, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('capers',1.2, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('peperoni',0.7, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('chicken',1.4, False);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('artichocke',2.5, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('coke',5, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('pepsi',4.9, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('whiskey',100, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('a bottle o water',0.1, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('Tiramisu',7, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('Snickers',2, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('Pudding',3, True);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('Creme Brule',4, True);");

            //Pizzas
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Hawaiian pizza',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Vegetarian pizza',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pizza Prosciutto',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pizza Margherita',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('BBQ chicken pizza',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pizza Neapolitan',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Cheese pizza',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pizza peperoni',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pizza capricciosa',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Stan&Martins pizza',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('coke',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('pepsi',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('whiskey',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('a bottle o water',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Tiramisu',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Snickers',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Pudding',0 ,False );");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('Creme Brule',0 ,False );");
            //matching ingredients to pizza
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 4);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 6);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 7);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 8);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 9);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 10);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 11);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 12);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 13);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 14);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 15);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 16);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 17);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 21);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 13);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 19);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 18);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 3);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 21);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 13);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 5);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 7);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 10);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 22);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 4);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 4);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (10, 4);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (11, 23);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (12, 24);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (13, 25);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (14, 26);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (15, 27);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (16, 28);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (17, 29);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (18, 30);");

            Server.AdminMethods.recalculateMenuItems();
            
            // close statement
            statement.close();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
        }
    }

    private static void handleSQLException(SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }
}
