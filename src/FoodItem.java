import java.util.ArrayList;

public class FoodItem {
    public int Food_ID;
    public String name;
    public double price;
    public boolean vegetarian;
    public ArrayList<Ingredients> foodIngredients;

    public FoodItem(int Food_ID, String name, int price,ArrayList<Ingredients> foodIngredients){
        this.Food_ID=Food_ID;
        this.name=name;
        this.foodIngredients=foodIngredients;
        IsVegetarian(foodIngredients);

    
}
    void IsVegetarian(ArrayList<Ingredients> foodIngredients){
        vegetarian=true;
        for (int i = 0; i < foodIngredients.size(); i++) {
            if(foodIngredients.get(i).vegetarian==false){
                vegetarian=false;
            }
        }
        
    }
    void Price(ArrayList<Ingredients> foodIngredients){
        double price=0;
        for (int i = 0; i < foodIngredients.size(); i++) {
            price=price+foodIngredients.get(i).price;
        }
        price=1.4*price;
        
    }
}
