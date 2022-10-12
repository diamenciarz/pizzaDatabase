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
                    "CREATE TABLE Ingredients(IngredientID int,Name varchar(20),Price int,IsVegetarian boolean);");
            statement.execute(
                    "CREATE TABLE MenuItems(MenuItemID int,Name varchar(20),Price int, Isvegetarian boolean);");
            statement.execute(
                    "CREATE TABLE Orders(OrderID int, ClientID int, CourierID int, OrderStatus enum('ORDER_SENT','PREPARING','DELIVERING','DELIVERED','CANCELLED'),OrderDate timestamp,Price int);");
            statement.execute(
                    "CREATE TABLE Clients(ClientID int, Name varchar(20),PhoneNumber int,Adress varchar(20), PizzaCount int);");
            statement.execute("CREATE TABLE Codes(ClientID int,DiscountCode varchar(5),IsUsed boolean);");
            statement.execute("CREATE TABLE Couriers(CourierID int,PostCode varchar(7),IsAvailable boolean);");
            statement.execute("CREATE TABLE FoodIngredients(MenuItemID int, IngredientID int);");
            statement.execute("CREATE TABLE OrderItems(OrderID int, MenuItemID int);");

            // Fill tables with examples
            statement.execute("INSERT INTO OrderItems VALUES (1, 0);");
            statement.execute("INSERT INTO OrderItems VALUES (2, 1);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",4);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",11);");
            statement.execute("INSERT INTO Menuitems  VALUES (1, \"piza\",14.7, False);");
            statement.execute("INSERT INTO Menuitems  VALUES (2, \"pizza\",16.7, True);");
            statement.execute("INSERT INTO Menuitems  VALUES (3, \"fake pizza\",1.7, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (0, \"ham\",9.7, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (1, \"pork\",91.7, True);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 0);");

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
