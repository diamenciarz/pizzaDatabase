package JMonkey;
import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import NormalClasses.*;

import NormalClasses.QuerySender;
import objects.Client;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;

import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class UserData extends BaseAppState implements ScreenController{
    private NiftyJmeDisplay niftyDisplay;
    private AssetManager assetManager;
    private Nifty nifty;
 
    public String name;
    public int phoneNumber;
    public String adress;
    public int pizzaCount;
    public String codes;

    @Override
    public void bind(Nifty arg0, Screen arg1) {
        
        
    }

    @Override
    public void onEndScreen() {
       
        
    }

    @Override
    public void onStartScreen() {
       
        
    }

    @Override
    protected void cleanup(Application arg0) {
        nifty.removeElement(nifty.getCurrentScreen(), nifty.getCurrentScreen().getRootElement());
        
    }

    @Override
    protected void initialize(Application app) {
        this.assetManager=app.getAssetManager();
        this.nifty=((Launch)app).getNifty();
        this.niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                 app.getAssetManager(),
                 app.getInputManager(),
                 app.getAudioRenderer(),
                 app.getGuiViewPort());
 
          nifty = niftyDisplay.getNifty();
         getApplication().getGuiViewPort().addProcessor(niftyDisplay);
         onEnable();
        
    }

    @Override
    protected void onDisable() {
        
        
    }

    @Override
    protected void onEnable() {
        ScreenController controller = this;
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");

        // <screen>
        nifty.addScreen("Screen_5", new ScreenBuilder("Hello Nifty Screen"){{
            controller(controller); // Screen properties

            // <layer>
            layer(new LayerBuilder("Layer_5") {{
                childLayoutVertical(); // layer properties, add more...
               
                backgroundColor("#FFFFFF");
                                                                                             //nifty.setDebugOptionPanelColors(true);
                // <panel>
                panel(new PanelBuilder("Panel_5") {{
                    width("100%");
                    height("90%");
                   childLayoutVertical(); // panel properties, add more...
                    //.. add more GUI elements here
                    text(new TextBuilder() {{
                       height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("User ID:"+ getClientDetails() );
                         alignCenter();
                         valignCenter();
                     }});
                     text(new TextBuilder() {{
                        height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("name: " + name);
                         alignCenter();
                         valignCenter();
                     }});
                     text(new TextBuilder() {{
                        height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("phone number: "+phoneNumber);
                         alignCenter();
                         valignCenter();
                     }});
                     text(new TextBuilder() {{
                        height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("adress: " +adress);
                         alignCenter();
                         valignCenter();
                     }});
                     text(new TextBuilder("text_0") {{
                       
                        height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("pizza count: "+pizzaCount);
                         alignCenter();
                         valignCenter();
                         interactOnClick("text()");
                         
                     }});
                     text(new TextBuilder("text_1") {{
                       
                        height("5%");
                        font("Interface/Fonts/Default.fnt");
                         color("#000000");
                         text("Codes: "+codes);
                         alignCenter();
                         valignCenter();
                         interactOnClick("text()");
                         
                     }});
                   
                }});
                // </panel>
                //<panel>
                panel(new PanelBuilder("Panel_6") {{
                    width("100%");
                    height("10%");
                   childLayoutVertical();
                   control(new ButtonBuilder("Button_0", "Go Back"){{
                  
                    alignCenter();
                    valignCenter();
                    height("50%");
                    width("15%");
                    interactOnClick("goBack()");
                }});
             
                }});
                //</panel>
              }});
            // </layer>
          }}.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_5"); // start the screen
        
    }
    public void goBack(){
        getStateManager().detach(this);
        getStateManager().attach(Launch.secondScreen);
    }
   
public String text(){
    return "big man";
}
//selectDiscountCodes
//selectOrdersBelongingTo

public String getClientDetails(){
    int t= Integer.valueOf(Launch.UserID);
    Client client= QuerySender.SingleValue.selectClient(t);
    name= client.name;
    phoneNumber=client.phoneNumber;
    adress=client.postCode;
    pizzaCount=client.pizzaCount;
    codes=getUserCodes();
    return Launch.UserID;
}

private String getUserCodes() {
    ArrayList<String> allCodes=Server.UserMethods.getCodes(Integer.valueOf(Launch.UserID));;
    return allCodes.toString();
}
}
