import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.*;

public class UnpackObj {
    public static class List {
        public static ArrayList<Order> unpackOrders(ResultSet resultSet) {
            Integer iDs = 0;// this is very balssy
            Integer clientIDs;
            Integer courierIDs;
            Float prices;

            ArrayList<Order> orders = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(iDs);
                    iDs = ResultSetReader.readInt(DatabaseNames.Order.orderID, resultSet);
                    clientIDs = ResultSetReader.readInt(DatabaseNames.Order.clientID, resultSet);
                    courierIDs = ResultSetReader.readInt(DatabaseNames.Order.courierID, resultSet);
                    prices = ResultSetReader.readFloat(DatabaseNames.Order.price, resultSet);
                    // TODO: Add date
                    // TODO: Add orderStatus
                    orders.add(new Order(menuItems, prices, iDs, clientIDs, courierIDs,
                            Order.Status.ORDER_SENT, null));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Orders failed to unpack");
            }
            return orders;
        }

        public static ArrayList<MenuItem> unpackMenuItems(ResultSet resultSet) {
            Integer iDs;
            String names;
            Float prices;
            Boolean areVegetarian;
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    iDs = ResultSetReader.readInt(DatabaseNames.MenuItem.menuItemID, resultSet);
                    names = ResultSetReader.readString(DatabaseNames.MenuItem.foodName, resultSet);
                    prices = ResultSetReader.readFloat(DatabaseNames.MenuItem.price, resultSet);
                    areVegetarian = ResultSetReader.readBoolean(DatabaseNames.MenuItem.isVegetarian, resultSet);
                    ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(iDs);

                    menuItems.add(
                            new MenuItem(iDs, names, prices, areVegetarian, ingredients));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Menu Items failed to unpack");
            }
            return menuItems;
        }

        public static ArrayList<Ingredient> unpackIngredients(ResultSet resultSet) {
            String names;
            Integer iDs;
            Float prices;
            Boolean areVegetarian;
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            try {
                while (resultSet.next()) {
                    iDs = ResultSetReader.readInt(DatabaseNames.Ingredient.ingredientID, resultSet);
                    names = ResultSetReader.readString(DatabaseNames.Ingredient.ingredientName, resultSet);
                    prices = ResultSetReader.readFloat(DatabaseNames.Ingredient.price, resultSet);
                    areVegetarian = ResultSetReader.readBoolean(DatabaseNames.Ingredient.isVegetarian,
                            resultSet);
                    ingredients.add(new Ingredient(iDs, names, prices, areVegetarian));

                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ingredients failed to unpack");
            }
            return ingredients;
        }

        public static ArrayList<Client> unpackClients(ResultSet resultSet) {
            Integer iDs;
            String names;
            Integer phoneNumbers;
            String addresses;
            Integer pizzaCounts;
            ArrayList<Client> clients = new ArrayList<>();

            try {
                while (resultSet.next()) {
                    iDs = ResultSetReader.readInt(DatabaseNames.Client.clientID, resultSet);
                    names = ResultSetReader.readString(DatabaseNames.Client.clientName, resultSet);
                    phoneNumbers = ResultSetReader.readInt(DatabaseNames.Client.phoneNumber, resultSet);
                    addresses = ResultSetReader.readString(DatabaseNames.Client.address,
                            resultSet);
                    pizzaCounts = ResultSetReader.readInt(DatabaseNames.Client.pizzaCount, resultSet);
                    // id, name, phoneNumber, address, pizzaCount
                    clients.add(new Client(iDs, names, phoneNumbers, addresses, pizzaCounts));

                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ingredients failed to unpack");
            }
            return clients;
        }

        public static ArrayList<Courier> unpackCouriers(ResultSet resultSet) {
            Integer iDs;
            String postCodes;
            Boolean areAvailable;
            ArrayList<Courier> ingredients = new ArrayList<>();

            try {
                while (resultSet.next()) {
                    iDs = ResultSetReader.readInt(DatabaseNames.Courier.courierID, resultSet);
                    postCodes = ResultSetReader.readString(DatabaseNames.Courier.postCode, resultSet);
                    areAvailable = ResultSetReader.readBoolean(DatabaseNames.Courier.isAvailable,
                            resultSet);
                    ingredients.add(new Courier(iDs, postCodes, areAvailable));

                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ingredients failed to unpack");
            }
            return ingredients;
        }
    }

    public static class SingleValue {
        public static Order unpackOrder(ResultSet resultSet) {
            Integer id = ResultSetReader.readInt(DatabaseNames.Order.orderID, resultSet);
            Integer clientID = ResultSetReader.readInt(DatabaseNames.Order.clientID, resultSet);
            Integer courierID = ResultSetReader.readInt(DatabaseNames.Order.courierID, resultSet);
            Float price = ResultSetReader.readFloat(DatabaseNames.Order.price, resultSet);
            Date date = ResultSetReader.readDate(DatabaseNames.Order.orderDate, resultSet);
            ArrayList<MenuItem> menuItems = QuerySender.List.selectMenuItemsBelongingTo(id);

            // TODO: read date
            return new Order(menuItems, price, id, clientID, courierID, Order.Status.ORDER_SENT, date);
        }

        public static MenuItem unpackMenuItem(ResultSet resultSet) {
            Integer id = ResultSetReader.readInt(DatabaseNames.MenuItem.menuItemID, resultSet);
            String name = ResultSetReader.readString(DatabaseNames.MenuItem.foodName, resultSet);
            Float price = ResultSetReader.readFloat(DatabaseNames.MenuItem.price, resultSet);
            Boolean isVegetarian = ResultSetReader.readBoolean(DatabaseNames.MenuItem.isVegetarian,
                    resultSet);
            ArrayList<Ingredient> ingredients = QuerySender.List.selectIngredientsBelongingTo(id);

            return new MenuItem(id, name, price, isVegetarian, ingredients);
        }

        public static Ingredient unpackIngredient(ResultSet resultSet) {
            Integer id = ResultSetReader.readInt(DatabaseNames.Ingredient.ingredientID, resultSet);
            String name = ResultSetReader.readString(DatabaseNames.Ingredient.ingredientName,
                    resultSet);
            Float price = ResultSetReader.readFloat(DatabaseNames.Ingredient.price, resultSet);
            Boolean isVegetarian = ResultSetReader.readBoolean(DatabaseNames.Ingredient.isVegetarian,
                    resultSet);

            return new Ingredient(id, name, price, isVegetarian);
        }

        public static Client unpackClient(ResultSet resultSet) {
            Integer id = ResultSetReader.readInt(DatabaseNames.Client.clientID, resultSet);
            String name = ResultSetReader.readString(DatabaseNames.Client.clientName,
                    resultSet);
            Integer phoneNumber = ResultSetReader.readInt(DatabaseNames.Client.phoneNumber,
                    resultSet);
            String address = ResultSetReader.readString(DatabaseNames.Client.address, resultSet);
            Integer pizzaCount = ResultSetReader.readInt(DatabaseNames.Client.pizzaCount,
                    resultSet);

            return new Client(id, name, phoneNumber, address, pizzaCount);
        }

        public static Courier unpackCourier(ResultSet resultSet) {
            Integer id = ResultSetReader.readInt(DatabaseNames.Courier.courierID, resultSet);
            String postCode = ResultSetReader.readString(DatabaseNames.Courier.postCode,
                    resultSet);
            Boolean isAvailable = ResultSetReader.readBoolean(DatabaseNames.Courier.isAvailable,
                    resultSet);

            return new Courier(id, postCode, isAvailable);
        }
    }

}
