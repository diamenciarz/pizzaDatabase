Drop table pizza;
CREATE TABLE Orders(
Order_ID int,
Client_ID int,
Courier_ID int,
Order_status enum('preparing', 'delivering','delivered','cancelled'),
Order_date timestamp,
price int
);
CREATE TABLE Food_items(
Food_ID int,
Food_name varchar(20),
price int,
vegetarian boolean
);
CREATE TABLE Ingredients(
Ingredient_ID int,
Ingredient_name varchar(20),
price int,
vegetarian boolean
);
CREATE TABLE Food_Ingredients(
Food_ID int,
Ingredient_ID int
);
CREATE TABLE Order_items(
Order_ID int,
Food_ID int
);
CREATE TABLE Clients(
Client_ID int,
Client_name varchar(20),
Phone_number int,
Adress varchar(20),
Pizza_Count int(10)
);
CREATE TABLE Codes(
Client_ID int,
Discount_code varchar(5),
Is_used boolean
);
CREATE TABLE Courier(
Courier_ID int,
Post_code varchar(7),
Is_delivering boolean
);