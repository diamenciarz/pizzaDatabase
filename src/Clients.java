
import java.net.ConnectException;
import java.sql.ResultSet;
import java.util.ArrayList;



public class Clients {
    public int Client_ID;
    public String name;
    public int phoneNumber;
    public String adress;
    public boolean tenOrLess;
    
    public Clients(int Client_ID,String name, int phoneNumber, String adress){
        this.name=name;
        this.phoneNumber=phoneNumber;
        this.adress=adress;
        this.Client_ID=Client_ID;
    }
   
    
    
}
