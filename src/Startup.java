import java.net.ConnectException;
import java.sql.*;

public class Startup {
    public static void startup() throws ConnectException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

            Statement statement = conn.createStatement();

            // Reset database
            statement.executeQuery("DROP DATABASE IF EXISTS restaurant;");
            statement.executeQuery("CREATE DATABASE restaurant;");

            // Create tables
            statement.executeQuery(
                    "CREATE TABLE Orders(Order_ID int,Client_ID int,Courier_ID int,Order_status enum('preparing', 'delivering','delivered','cancelled'),Order_date timestamp,price int);");
            statement.executeQuery(
                    "CREATE TABLE Food_items(Food_ID int,Food_name varchar(20),price int,vegetarian boolean);");
            statement.executeQuery(
                    "CREATE TABLE Ingredients(Ingredient_ID int,Ingredient_name varchar(20),price int,vegetarian boolean);");
            statement.executeQuery("CREATE TABLE Food_Ingredients(Food_ID int,Ingredient_ID int);");
            statement.executeQuery("CREATE TABLE Order_items(Order_ID int,Food_ID int);");
            statement.executeQuery(
                    "CREATE TABLE Clients(Client_ID int,Client_name varchar(20),Phone_number int,Adress varchar(20),Pizza_Count int(10));");
            statement.executeQuery("CREATE TABLE Codes(Client_ID int,Discount_code varchar(5),Is_used boolean);");
            statement.executeQuery("CREATE TABLE Courier(Courier_ID int,Post_code varchar(7),Is_delivering boolean);");

            //Fill tables with examples
            

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
