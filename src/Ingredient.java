public class Ingredient {
    public int Ingredient_ID;
    public String name;
    public float price;
    public boolean isVegetarian;

    public Ingredient(int Ingredient_ID, String name, float price, boolean isVegetarian){
        this.Ingredient_ID=Ingredient_ID;
        this.name=name;
        this.price=price;
        this.isVegetarian=isVegetarian;
    }
}
