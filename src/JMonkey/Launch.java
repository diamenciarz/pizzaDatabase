package JMonkey;
import java.net.ConnectException;
import java.util.ArrayList;

import com.jme3.app.SimpleApplication;
import com.jme3.niftygui.NiftyJmeDisplay;

import de.lessvoid.nifty.Nifty;
import objects.*;

public class Launch extends SimpleApplication{
    private NiftyJmeDisplay niftyDisplay;
    private static Nifty nifty;

    public static  String UserID;
    public static  ArrayList<MenuItem> menuItems=new ArrayList<MenuItem>();;
    public static Order order= new Order();

    public static final StartScreen startScreen = new StartScreen();
    public static final SecondScreen secondScreen = new SecondScreen();
    public static final PlaceOrder orderScreen = new PlaceOrder();
    public static final UserData userDataScreen = new UserData();
    public static final Menu menuScreen = new Menu();
   
    private void initNifty() {
        niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                this.getAssetManager(),
                this.getInputManager(),
                this.getAudioRenderer(),
                this.getGuiViewPort());

        nifty = niftyDisplay.getNifty();
        this.getGuiViewPort().addProcessor(niftyDisplay);
        this.getFlyByCamera().setDragToRotate(true);

    }
    @Override
    public void simpleInitApp() {
        initNifty();
        getStateManager().attach(startScreen);
        
    }
    public Nifty getNifty() {
        return nifty;
    }
    public static void main(String[] args) throws ConnectException {
        Startup.startup();
        Launch app = new Launch();
        app.start();
    }

   
}