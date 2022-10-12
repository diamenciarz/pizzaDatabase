USE pizza;

CREATE TABLE Ingredients(IngredientID int,Name varchar(20),Price int,Isvegetarian boolean);

INSERT INTO Ingredients VALUES (1,'pizza', 10, False);

(SELECT * FROM FoodIngredients WHERE MenuItemID = 1) ;
SELECT * FROM FoodIngredients JOIN ingredients USING(ingredientID);

INSERT INTO students (name, grade) VALUES("Car", 12 );