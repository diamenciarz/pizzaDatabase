package objects;

public class Ingredient {
    public int ingredientID;
    public String name;
    public float price;
    public boolean isVegetarian;

    public Ingredient() {

    }

    public Ingredient(int ingredientID,String names, Float prices, Boolean areVegetarian) {
        this.ingredientID = ingredientID;
        this.name = names;
        this.price = prices;
        this.isVegetarian = areVegetarian;
    }
}
