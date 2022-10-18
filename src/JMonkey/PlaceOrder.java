package JMonkey;
import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;

import NormalClasses.App;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
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
        this.assetManager = app.getAssetManager();
        this.nifty = ((Launch) app).getNifty();
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
        nifty.addScreen("Screen_2", new ScreenBuilder("Hello Nifty Screen") {
            {
                controller(controller); // Screen properties

                // <layer>
                layer(new LayerBuilder("Layer_2") {
                    {
                        childLayoutHorizontal(); // layer properties, add more...

                        backgroundColor("#FFFFFF");
                        // nifty.setDebugOptionPanelColors(true);
                        panel(new PanelBuilder("Panel_3") {
                            {
                                width("85%");
                                height("100%");
                                childLayoutVertical(); // panel properties, add more...
                                // .. add more GUI elements here

                                text(new TextBuilder("text_0") {
                                    {

                                        height("15%");
                                        font("Interface/Fonts/Default.fnt");
                                        color("#000000");
                                        text(menuToString());
                                        alignRight();
                                        valignCenter();
                                        wrap(true);
                                    }
                                });

                            }
                        });
                        // <panel>
                        panel(new PanelBuilder("Panel_4") {
                            {
                                width("15%");
                                height("100%");
                                childLayoutVertical(); // panel properties, add more...
                                // .. add more GUI elements here
                                control(new ButtonBuilder("Button1", "Place Order") {
                                    {
                                        visibleToMouse(true);
                                        alignCenter();
                                        valignCenter();
                                        height("5%");
                                        width("100%");
                                        interactOnClick("prepOrder()");
                                    }
                                });
                                control(new ButtonBuilder("Button0", "Go Back") {
                                    {
                                        visibleToMouse(true);
                                        alignCenter();
                                        valignCenter();
                                        height("5%");
                                        width("100%");
                                        interactOnClick("goBack()");
                                    }
                                });
                            }
                        });
                        // </panel>
                    }
                });
                // </layer>
            }
        }.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_2"); // start the screen
    }

    public void goBack() {
        getStateManager().detach(this);
        getStateManager().attach(Launch.secondScreen);

    }

    public String menuToString() {
        String menu = "| Food ID | name | price | vegetarian? |";

        for (int i = 0; i < Launch.menuItems.size(); i++) {
            menu = menu + '\n' + Launch.menuItems.get(i).menuItemID + " | " + Launch.menuItems.get(i).name + " | "
                    + Launch.menuItems.get(i).price + " | " + Launch.menuItems.get(i).isVegetarian + " | " + '\n'
                    + "--------------------------------------------------------";
        }
        return menu;
    }

    public void prepOrder() {
        if (UIMethods.canOrder(Launch.menuItems) == true) {
            Launch.order.clientID = Integer.valueOf(Launch.UserID);
            for (int i = 0; i < Launch.menuItems.size(); i++) {
                Launch.order.menuItems.add(Launch.menuItems.get(i));
            }

            System.ou t .p
            App f = new App(); 
            f. lyOrderInfo
            // 1);

            else
                System.out.println("You 
            }
            
            
              