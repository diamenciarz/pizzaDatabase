package objects;

public class Courier {
    public int Courier_ID;
    public String postCode;
    public boolean isAvailable;

    public Courier(int Courier_ID, String postCode, boolean isAvailable) {
        this.Courier_ID = Courier_ID;
        this.postCode = postCode;
        this.isAvailable = isAvailable;
    }
}
