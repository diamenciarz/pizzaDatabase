package objects;

public class Client {
    public int Client_ID;
    public String name;
    public int phoneNumber;
    public String adress;
    public int pizzaCount;

    public Client(int Client_ID, String name, int phoneNumber, String adress, int pizzaCount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.Client_ID = Client_ID;
        this.pizzaCount = pizzaCount;
    }
}
