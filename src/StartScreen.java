import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.scene.Node;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.textfield.builder.TextFieldBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class StartScreen extends BaseAppState implements ScreenController {
    private final Node localRootNode = new Node("MyStartScreen");
    private Nifty nifty;
    @Override
    protected void initialize(Application app) {
    }

    @Override
    protected void cleanup(Application app) {
        nifty.removeElement(nifty.getCurrentScreen(), nifty.getCurrentScreen().getRootElement());
    }
    @Override
    protected void onEnable() {
        this.nifty = ((Launch) getApplication()).getNifty();
        ((SimpleApplication) getApplication()).getRootNode().attachChild(localRootNode);
        ((SimpleApplication) getApplication()).getFlyByCamera().setDragToRotate(true);

      
        
        ScreenController controller = this;
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        
        // <screen>
        nifty.addScreen("Screen_0", new ScreenBuilder("Hello Nifty Screen"){{
            controller(controller); // Screen properties
                
            // <layer>
            layer(new LayerBuilder("Layer_0") {{
                childLayoutVertical(); // layer properties, add more...
               
                backgroundColor("#FFFFFF");
                                                                                            // nifty.setDebugOptionPanelColors(true);
                // <panel>
                panel(new PanelBuilder("Panel_1") {{
                    width("100%");
                    height("100%");
                   childLayoutVertical(); // panel properties, add more...
                   backgroundColor("#ADD8E6");
                   // GUI elements
                text(new TextBuilder() {{
                   height("50%");
                   font("Interface/Fonts/Default.fnt");
                    color("#000000");
                    text("Please enter your User ID");
                    alignCenter();
                    valignCenter();
                }});
                control(new TextFieldBuilder("UserID", "") {{
                    alignCenter();
                    valignCenter();
                    width("200px");
                }});
                  
                 control(new ButtonBuilder("Button_0", "Login"){{
                        visibleToMouse(true);
                        alignCenter();
                        valignCenter();
                        height("5%");
                        width("15%");
                        interactOnClick("goToSecondScreen()");
                    }});

                    //.. add more GUI elements here

                }});
                // </panel>
              }});
            // </layer>
          }}.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_0"); // start the screen
    }
   
    @Override
    protected void onDisable() {
    }

    @Override
    public void update(float tpf) {
    }

    @Override
    public void bind(Nifty arg0, Screen arg1) {
        
    }

    @Override
    public void onEndScreen() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onStartScreen() {
        // TODO Auto-generated method stub
        
    }
    //-----
    public void goToSecondScreen(){
        Launch.UserID=nifty.getCurrentScreen().findNiftyControl("UserID", TextField.class).getRealText();
        try {
           int t= Integer.valueOf(Launch.UserID);
            getStateManager().detach(this);
            getStateManager().attach(Launch.secondScreen);
        } catch (Exception e) {
            System.out.println("Please only enter numbers");
        }
       
    }


}