import java.util.ArrayList;

import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.scrollbar.builder.ScrollbarBuilder;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import objects.MenuItem;
public class Menu extends BaseAppState implements ScreenController{
    private NiftyJmeDisplay niftyDisplay;
    private AssetManager assetManager;
    private Nifty nifty;
    private int swap=0;
    private static int startPosition=13;

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
        nifty.addScreen("Screen_3", new ScreenBuilder("Hello Nifty Screen"){{
            controller(controller); // Screen properties

            // <layer>
            layer(new LayerBuilder("Layer_3") {{
                childLayoutHorizontal(); // layer properties, add more...
               
                backgroundColor("#FFFFFF");
                                                                                             nifty.setDebugOptionPanelColors(true);
                // <panel>
                panel(new PanelBuilder("Panel_4") {{
                    width("65%");
                    height("100%");
                   
                   childLayoutVertical(); // panel properties, add more...

                   text(new TextBuilder("text_0") {{
                    setEnabled(!onOff());
                    height("15%");
                    font("Interface/Fonts/Default.fnt");
                     color("#000000");  
                     text(menuToString());
                     alignRight();
                     valignCenter();
                     wrap(true);
                    }});

                    text(new TextBuilder("text_1") {{
                        setEnabled(onOff());
                         height("15%");
                         font("Interface/Fonts/Default.fnt");
                          color("#000000");
                          text(menuToString2());
                          alignRight();
                          valignCenter();
                          wrap(true);
                          
                          
                          
                      }});
                      
                
                }});
                panel(new PanelBuilder("Panel_6") {{
                    width("20%");
                    height("100%");
                   childLayoutAbsoluteInside();
                   childClip(true);
                   control(new ButtonBuilder("Button_4", "+"){{
                    visibleToMouse(true);
                    y("13px");
                    alignLeft();
                    valignCenter();
                    height("8%");
                    width("40%");

                }});
                text(new TextBuilder("text_2") {{
                     x("62.2");
                     y("13px");
                     height("8%");
                     font("Interface/Fonts/Default.fnt");
                      color("#000000");
                      text("1");//TODO 
                      alignRight();
                      valignCenter();
                      wrap(true);
                      
                      
                      
                  }});
                control(new ButtonBuilder("Button_4", "-"){{
                    visibleToMouse(true);
                    alignRight();
                    valignCenter();
                    y("13px");
                    x("77.8px");
                    height("8%");
                    width("40%");

                }});
                control(new ButtonBuilder("Button_5", "+"){{
                    visibleToMouse(true);
                    y("58.4px");
                    alignLeft();
                    valignCenter();
                    height("8%");
                    width("40%");

                }});
                text(new TextBuilder("text_3") {{
                     x("62.2");
                     y("58.4px");
                     height("8%");
                     font("Interface/Fonts/Default.fnt");
                      color("#000000");
                      text("1");//TODO 
                      alignRight();
                      valignCenter();
                      wrap(true);
                      
                      
                      
                  }});
                control(new ButtonBuilder("Button_6", "-"){{
                    visibleToMouse(true);
                    alignRight();
                    valignCenter();
                    y("58.4px");
                    x("77.8px");
                    height("8%");
                    width("40%");

                }});
                control(new ButtonBuilder("Button_7", "+"){{
                    visibleToMouse(true);
                    y("103.8");
                    alignLeft();
                    valignCenter();
                    height("8%");
                    width("40%");

                }});
                text(new TextBuilder("text_4") {{
                     x("62.2");
                     y("103.8");
                     height("8%");
                     font("Interface/Fonts/Default.fnt");
                      color("#000000");
                      text("1");//TODO 
                      alignRight();
                      valignCenter();
                      wrap(true);
                      
                      
                      
                  }});
                control(new ButtonBuilder("Button_8", "-"){{
                    visibleToMouse(true);
                    alignRight();
                    valignCenter();
                    y("103.8");
                    x("77.8px");
                    height("8%");
                    width("40%");

                }});
                control(new ButtonBuilder("Button_9", "+"){{
                    visibleToMouse(true);
                    y("149.2");
                    alignLeft();
                    valignCenter();
                    height("8%");
                    width("40%");

                }});
                text(new TextBuilder("text_5") {{
                     x("62.2");
                     y("149.2");
                     height("8%");
                     font("Interface/Fonts/Default.fnt");
                      color("#000000");
                      text("1");//TODO 
                      alignRight();
                      valignCenter();
                      wrap(true);
                      
                      
                      
                  }});
                control(new ButtonBuilder("Button_10", "-"){{
                    visibleToMouse(true);
                    alignRight();
                    valignCenter();
                    y("149.2");
                    x("77.8px");
                    height("8%");
                    width("40%");

                }});
                }});
                panel(new PanelBuilder("Panel_5") {{
                    width("15%");
                    height("100%");
                   childLayoutVertical(); // panel properties, add more...

                   control(new ButtonBuilder("Button_1", "More results"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignBottom();
                    marginTop("70%");
                    height("5%");
                    width("75%");
                    interactOnClick("moreResults()");

                }});
                control(new ButtonBuilder("Button_2", "Less results"){{
                    visibleToMouse(true);
                    alignCenter();
                    valignBottom();
                    marginTop("10px");
                    height("5%");
                    width("75%");
                    interactOnClick("lessResults()");
                }});
                    control(new ButtonBuilder("Button_0", "Go Back"){{
                        visibleToMouse(true);
                        alignCenter();
                        valignBottom();
                        marginTop("10px");
                        height("5%");
                        width("75%");
                        interactOnClick("goBack()");
                    }});
                    //.. add more GUI elements here

                }});
                // </panel>
              }});
            // </layer>
          }}.build(nifty));
        // </screen>

        nifty.gotoScreen("Screen_3"); // start the screen
    }
    public void goBack(){
        getStateManager().detach(this);
        getStateManager().attach(Launch.secondScreen);
    }
    public String menuToString(){
        String menu="| Food ID | name | price | vegetarian? |";
        ArrayList<MenuItem> TMP = Server.UserMethods.getMenu();
        for (int i = 0; i <= 10; i++) {
           menu=menu +'\n' +TMP.get(i).menuItemID + " | " + TMP.get(i).name + " | " + TMP.get(i).price + " | " + TMP.get(i).isVegetarian+" | "+'\n'+"--------------------------------------------------------";
        }
        return menu;
    }
    public void moreResults(){
        nifty.removeElement(nifty.getCurrentScreen(), nifty.getCurrentScreen().findElementById("text_0")); 
    }
    public void lessResults(){
        getStateManager().detach(this);
        getStateManager().attach(Launch.menuScreen);
    }
    public String menuToString2(){
        String menu="| Food ID | name | price | vegetarian? |";
            ArrayList<MenuItem> TMP = Server.UserMethods.getMenu();
            for (int i = 10; i < TMP.size(); i++) {
               menu=menu +'\n' +TMP.get(i).menuItemID + " | " + TMP.get(i).name + " | " + TMP.get(i).price + " | " + TMP.get(i).isVegetarian+" | "+'\n'+"--------------------------------------------------------";
            }
            return menu;
    }
    public Boolean onOff(){
        if(swap!=0){
            swap--;
            return true;
           
        }
        else{
            swap++;
            return false;
        }
    }
    public double Placment(){
        return startPosition+45.4;
    }
}
