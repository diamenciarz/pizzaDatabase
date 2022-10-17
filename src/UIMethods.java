import java.util.ArrayList;
import objects.*;

public class UIMethods {
    public static MenuItem getMenuItemWIthId(ArrayList<MenuItem> menuItems, int id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.menuItemID == id) {
                return menuItem;
            }
        }
        return null;
    }

    public static boolean canOrder(ArrayList<MenuItem> selectedItems) {
        for (MenuItem menuItem : selectedItems) {
            for (Ingredient ingredient : menuItem.ingredients) {
                boolean isPizza = ingredient.ingredientID == 2;
                if (isPizza) {
                    return true;
                }
            }
        }
        return false;
    }
}
