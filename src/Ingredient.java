public class Ingredient {
    public int ingredientID;
    public String name;
    public float price;
    public boolean isVegetarian;

    public Ingredient() {

    }

    public Ingredient(int ingredientID, String name, float price, boolean isVegetarian) {
        this.ingredientID = ingredientID;
        this.name = name;
        this.price = price;
        this.isVegetarian = isVegetarian;
    }
}
