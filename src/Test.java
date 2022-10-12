import java.sql.*;
import java.net.ConnectException;

public class Test {
     public static void main(String[] args) {
          try {
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza?", "pizza", "pizza");

               Statement statement = conn.createStatement();

               // Reset database
               // statement.execute("DROP DATABASE IF EXISTS pizza;");
               // statement.execute("CREATE DATABASE pizza;");
               // statement.execute("USE pizza;");

               // Create tables

               // statement.executeUpdate("CREATE TABLE students (studentId INT NOT NULL
               // AUTO_INCREMENT, "
               // + "name VARCHAR(64), grade INT, PRIMARY KEY (studentId));");
               PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students (name, grade) VALUES (?, ?);");

               pstmt.setString(1, "Maya");
               pstmt.setInt(2, 666);
               pstmt.executeUpdate();

               try {
                    QuerySender.List.selectMenu();
                    System.out.println("Got here");
               } catch (Exception e) {
                    
               }

               System.out.println("Got here");
               statement.close();

          } catch (SQLException ex) {
               // This will result in an exception
               try {
                    handleSQLException(ex);

               } catch (ConnectException e) {
                    // TODO: handle exception
               }
               System.out.println("Coult not connect");
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