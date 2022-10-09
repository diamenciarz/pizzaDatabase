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
                    "CREATE TABLE Orders(OrderID int,ClientID int,CourierID int,OrderStatus enum('preparing', 'delivering','delivered','cancelled'),OrderDate timestamp,price int);");
            statement.execute(
                    "CREATE TABLE MenuItems(FoodID int,FoodName varchar(20),price int,isvegetarian boolean);");
            statement.execute(
                    "CREATE TABLE Ingredients(IngredientID int,IngredientName varchar(20),price int,isvegetarian boolean);");
            statement.execute("CREATE TABLE FoodIngredients(FoodID int,IngredientID int);");
            statement.execute("CREATE TABLE OrderItems(OrderID int, FoodID int);");
            statement.execute(
                    "CREATE TABLE Clients(ClientID int,ClientName varchar(20),PhoneNumber int,Adress varchar(20), PizzaCount int);");
            statement.execute("CREATE TABLE Codes(ClientID int,DiscountCode varchar(5),IsUsed boolean);");
            statement.execute("CREATE TABLE Couriers(CourierID int,PostCode varchar(7),IsDelivering boolean);");
            
            //Fill tables with examples
            statement.execute("INSERT INTO OrderItems VALUES (1, 10);");
            statement.execute("INSERT INTO OrderItems VALUES (2, 11);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",4);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",11);");
            statement.execute("INSERT INTO FoodIngredients VALUES (1, 10);");

            

            //close statement
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
