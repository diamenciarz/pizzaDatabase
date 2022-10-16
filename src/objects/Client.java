package objects;

public class Client {
    public int Client_ID;
    public String name;
    public int phoneNumber;
    public String postCode;
    public int pizzaCount;

    public Client(int Client_ID, String name, int phoneNumber, String adress, int pizzaCount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.postCode = adress;
        this.Client_ID = Client_ID;
        this.pizzaCount = pizzaCount;
    }
}
