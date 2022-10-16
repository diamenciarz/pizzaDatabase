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
                    "CREATE TABLE Clients(ClientID int, Name varchar(20), PhoneNumber int, Adress varchar(20), PizzaCount int);");
            statement.execute("CREATE TABLE Codes(ClientID int,DiscountCode varchar(5),IsUsed boolean);");
            statement.execute("CREATE TABLE Couriers(CourierID int,PostCode varchar(7),IsAvailable boolean);");
            statement.execute("CREATE TABLE FoodIngredients(MenuItemID int, IngredientID int);");
            statement.execute("CREATE TABLE OrderItems(OrderID int, MenuItemID int);");

            // Fill tables with examples
            statement.execute("INSERT INTO OrderItems VALUES (1, 0);");
            statement.execute("INSERT INTO OrderItems VALUES (2, 1);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",4);");
            statement.execute("INSERT INTO Clients VALUES (2, \"bruh\",1,\"da moon\",11);");
           
            //Ingredients
            statement.execute("INSERT INTO Ingredients  VALUES (0, \"cheese\",0.7, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (1, \"dough\",1.7, True); ");
            statement.execute("INSERT INTO Ingredients  VALUES (2, \"pizza sauce\",0.1, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (3,\"pineapples\",0.8, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (4,\"ham\",0.5, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (5,\"shrimp\",1.3, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (6,\"olives\",0.2, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (7,\"bell pepper\",0.2, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (8,\"tofu\",2, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (9,\"champignons \",0.9, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (10,\"tomatoes\",0.4, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (11,\"Prosciutto\",2.5, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (12,\"mozzarella\",1.1, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (13,\"basil\",2.5, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (14,\"San Marzano tomatoes\",5.5, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (15,\"BBQ sauce\",0.6, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (16,\"onion\",0.1, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (17,\"anchovies\",2.5, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (18,\"capers\",1.2, True);");
            statement.execute("INSERT INTO Ingredients  VALUES (19,\"peperoni\",0.7, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (20,\"chicken\",1.4, False);");
            statement.execute("INSERT INTO Ingredients  VALUES (21,\"artichocke\",2.5, True);");

            //Pizzas
            statement.execute("INSERT INTO Menuitems  VALUES (0, \"Hawaiian pizza\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (1, \"Vegetarian pizza\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (2, \"Pizza Prosciutto\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (3, \"Pizza Margherita\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (4, \"BBQ chicken pizza\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (5, \"Pizza Neapolitan\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (6, \"Cheese pizza\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (7, \"Pizza peperoni\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (8, \"Pizza capricciosa\",0 ,False );");
            statement.execute("INSERT INTO Menuitems  VALUES (9, \"Stan&Martins pizza\",0 ,False );");
            //matching ingredients to pizza
            statement.execute("INSERT INTO FoodIngredients  VALUES (0, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (0, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (0, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (0, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (0, 5);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 6);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 7);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 8);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 9);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (1, 10);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (2, 11);");

            
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 12);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 13);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (3, 14);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 15);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 16);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (4, 20);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 12);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 18);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (5, 17);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (6, 2);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (7, 20);");
            
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 12);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 4);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 6);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 9);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (8, 21);");

            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 0);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 1);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 2);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 3);");
            statement.execute("INSERT INTO FoodIngredients  VALUES (9, 3);");


            


            
           















            
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
