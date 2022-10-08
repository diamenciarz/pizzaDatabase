import java.util.ArrayList;

public class MenuItem {
    public int Food_ID;
    public String name;
    public float price;
    public boolean vegetarian;
    public ArrayList<Ingredient> foodIngredients = new ArrayList<>();

    public MenuItem(){

    }

    public MenuItem(int Food_ID, String name, int price,ArrayList<Ingredient> foodIngredients){
        this.Food_ID=Food_ID;
        this.name=name;
        this.foodIngredients=foodIngredients;
        IsVegetarian(foodIngredients);

    
}
    void IsVegetarian(ArrayList<Ingredient> foodIngredients){
        vegetarian=true;
        for (int i = 0; i < foodIngredients.size(); i++) {
            if(foodIngredients.get(i).isVegetarian==false){
                vegetarian=false;
            }
        }
        
    }
    void Price(ArrayList<Ingredient> foodIngredients){
        float price=0;
        for (int i = 0; i < foodIngredients.size(); i++) {
            price=price+foodIngredients.get(i).price;
        }
        price=(float) (1.4*price);
        
    }
}
