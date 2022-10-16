
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;


public class SecondScreen extends BaseAppState implements ScreenController {
    private NiftyJmeDisplay niftyDisplay;
    private AssetManager assetManager;
    private Nifty nifty;
    
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
        nifty.addScreen("Screen_1", new ScreenBuilder("Hello Nifty Screen"){{
            controller(controller); // Screen properties

            // <layer>
            layer(new LayerBuilder("Layer_1") {{
                childLayoutVertical(); // layer properties, add more...
               
                backgroundColor("#FFFFFF");
                                                                                             nifty.setDebugOptionPanelColors(true);
                // <panel>
                panel(new PanelBuilder("Panel_1") {{
                    width("100%");
                    height("100%");
                   childLayoutVertical(); // panel properties, add more...
                   backgroundColor("#ADD8E7");
                   // GUI elements
                   control(new ButtonBuilder("Button_1", "See the menu"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignCenter();
                    height("5%");
                    width("15%");
                    interactOnClick("goToMenu()");
                    marginBottom("10px");
                }});
                control(new ButtonBuilder("Button_2", "Your user Data"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignCenter();
                    height("5%");
                    width("15%");
                    interactOnClick("goToUserData()");
                    marginTop("10px");
                    marginBottom("10px");
                }});
                control(new ButtonBuilder("Button_3", "Order"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignCenter();
                    height("5%");
                    width("15%");
                    interactOnClick("goToOrder()");
                    marginTop("10px");
                }});
                  
                control(new ButtonBuilder("Button_13", "Exit"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignCenter();
                    height("5%");
                    width("15%");
                    interactOnClick("Exit()");
                    marginTop("10px");
                }});

                    //.. add more GUI elements here

                }});
                // </panel>
               
              }});
            // </layer>
          }}.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_1"); // start the screen
    }
    public void goToOrder(){
        getStateManager().detach(Launch.secondScreen);
       getStateManager().attach(Launch.orderScreen);
    }
    public void goToMenu(){
        getStateManager().detach(Launch.secondScreen);
        getStateManager().attach(Launch.menuScreen);
    }
    public void goToUserData(){
        getStateManager().detach(Launch.secondScreen);
        getStateManager().attach(Launch.userDataScreen);
    }
    
    
    public void Exit(){
        //TODO
    }
    

    @Override
    public void bind(Nifty arg0, Screen arg1) {
       
        
    }

    @Override
    public void onEndScreen() {
      
        
    }

    @Override
    public void onStartScreen() {
       
        
    }
    
}
