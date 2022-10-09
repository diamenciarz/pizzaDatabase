USE pizza;

CREATE TABLE Ingredients(IngredientID int,Name varchar(20),Price int,Isvegetarian boolean);

INSERT INTO Ingredients VALUES (1,'pizza', 10, False);

SELECT * FROM Ingredients;