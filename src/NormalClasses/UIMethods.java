package NormalClasses;
import java.util.ArrayList;
import objects.*;

public class UIMethods {
    /**
     * @param menuItems The menu obtained from the Server.getMenu();
     * @param id of the menuItem to get
     * @return the menuItem with selected {@code id}
     */
    public static MenuItem getMenuItemWIthId(ArrayList<MenuItem> menuItems, int id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.menuItemID == id) {
                return menuItem;
            }
        }
        return null;
    }

    /**
     * @param selectedItems
     * @return true, if the list of items contains at least one pizza.
     */
    public static boolean canOrder(ArrayList<MenuItem> selectedItems) {
        for (MenuItem menuItem : selectedItems) {
            for (Ingredient ingredient : menuItem.ingredients) {
                // Pizza is defined by containing dough, which has id = 2
                boolean isPizza = ingredient.ingredientID == 2;
                if (isPizza) {
                    return true;
                }
            }
        }
        return false;
    }
}
