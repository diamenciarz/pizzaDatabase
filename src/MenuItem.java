import java.util.ArrayList;

public class MenuItem {
    public int Food_ID;
    public String name;
    public float price;
    public boolean isVegetarian;
    public ArrayList<Ingredient> ingredients = new ArrayList<>();

    public MenuItem() {

    }

    public MenuItem(int Food_ID, String name, int price, ArrayList<Ingredient> ingredients) {
        this.Food_ID = Food_ID;
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
