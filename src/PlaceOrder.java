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
public class PlaceOrder extends BaseAppState implements ScreenController {
    private NiftyJmeDisplay niftyDisplay;
    private AssetManager assetManager;
    private Nifty nifty;
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
        nifty.addScreen("Screen_2", new ScreenBuilder("Hello Nifty Screen"){{
            controller(controller); // Screen properties

            // <layer>
            layer(new LayerBuilder("Layer_2") {{
                childLayoutVertical(); // layer properties, add more...
               
                backgroundColor("#FFFFFF");
                                                                                             //nifty.setDebugOptionPanelColors(true);
                // <panel>
                panel(new PanelBuilder("Panel_3") {{
                    width("100%");
                    height("100%");
                   childLayoutVertical(); // panel properties, add more...
                    //.. add more GUI elements here
                    control(new ButtonBuilder("Button_0", "Go Back"){{
                        visibleToMouse(true);
                        alignCenter();
                        valignCenter();
                        height("5%");
                        width("15%");
                        interactOnClick("goBack()");
                    }});
                }});
                // </panel>
              }});
            // </layer>
          }}.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_2"); // start the screen
    }
    
    public void goBack(){
        getStateManager().detach(this);
        getStateManager().attach(Launch.secondScreen);
    }
}
