public class Courier {
    public int Courier_ID;
    public String postCode;
    public boolean isDelivering;
    
    public Courier(int Courier_ID, String postCode, boolean isDelivering){
    this.Courier_ID =Courier_ID;
    this.postCode=postCode;
    this.isDelivering=false;
    }
    void Delivering(){
        this.isDelivering=true;
    }
}
