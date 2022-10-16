import java.net.ConnectException;
import java.sql.*;

public class Startup {
    public static void startup() throws ConnectException {
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
                    "CREATE TABLE Orders(OrderID int NOT NULL AUTO_INCREMENT, ClientID int, CourierID int, OrderStatus enum('ORDER_SENT','PREPARING','DELIVERING','DELIVERED','CANCELLED'),OrderDate timestamp,Price int, PRIMARY KEY (OrderID));");
            statement.execute(
                    "CREATE TABLE Clients(ClientID int NOT NULL AUTO_INCREMENT, Name varchar(20), PhoneNumber int, Address varchar(20), PizzaCount int, PRIMARY KEY (ClientID));");
            statement.execute("CREATE TABLE Codes(ClientID int,DiscountCode varchar(5),IsUsed boolean);");
            statement.execute("CREATE TABLE Couriers(CourierID int NOT NULL AUTO_INCREMENT,PostCode varchar(7),IsAvailable boolean, PRIMARY KEY (CourierID));");
            statement.execute("CREATE TABLE FoodIngredients(MenuItemID int, IngredientID int);");
            statement.execute("CREATE TABLE OrderItems(OrderID int, MenuItemID int);");

            // Fill tables with examples
            statement.execute("INSERT INTO Clients (Name, PhoneNumber, Address, PizzaCount) VALUES ('bruh',666,'da moon',4);");
            statement.execute("INSERT INTO Clients (Name, PhoneNumber, Address, PizzaCount) VALUES ('bruh',999,'da moon',9);");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('piza',14.7, 0);");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('pizza',16.7, 0);");
            statement.execute("INSERT INTO Menuitems (Name, Price, IsVegetarian) VALUES ('fake pizza',1.7, 0);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('ham',9.7, 0);");
            statement.execute("INSERT INTO Ingredients (Name, Price, IsVegetarian) VALUES ('pork',91.7, 1);");
            statement.execute("INSERT INTO Couriers (PostCode, IsAvailable) VALUES ('3620', True);");
            statement.execute("INSERT INTO Couriers (PostCode, IsAvailable) VALUES ('3621', True);");
            
            statement.execute("INSERT INTO OrderItems VALUES (1, 0);");
            statement.execute("INSERT INTO OrderItems VALUES (2, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 2);");
            
            
            // statement.execute("UPDATE MenuItems SET IsVegetarian = '1' WHERE MenuItemID = 2;");
            // statement.execute("UPDATE MenuItems SET Price = '1000' WHERE MenuItemID = 1;");
            QuerySender.SingleValue.recalculateMenuItems();
            // close statement
            statement.close();

        } catch (SQLException ex) {
            // This will result in an exception
            handleSQLException(ex);
        }
    }

    private static void handleSQLException(SQLException ex) throws ConnectException {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        throw new ConnectException("Connection with the database couldn't be established");
    }
}
