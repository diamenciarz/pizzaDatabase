package objects;
import java.util.ArrayList;

public class MenuItem {
    public int foodID;
    public String name;
    public float price;
    public boolean isVegetarian;
    public ArrayList<Ingredient> ingredients = new ArrayList<>();

    public MenuItem() {

    }

    public MenuItem(int foodID, String name, float price, boolean isVegetarian, ArrayList<Ingredient> ingredients) {
        this.foodID = foodID;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        setIsVegetarian();

    }

    void setIsVegetarian() {
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.isVegetarian) {
                isVegetarian = false;
                return;
            }
        }
        isVegetarian = true;
    }

    void price(ArrayList<Ingredient> foodIngredients) {
        float price = 0;
        for (int i = 0; i < foodIngredients.size(); i++) {
            price = price + foodIngredients.get(i).price;
        }
        price *= 1.4;

    }
}
