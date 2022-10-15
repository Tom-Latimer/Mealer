package com.example.mealer;

public class Meal_Class {
    private String name;
    private String description;
    private String price;
    private String Meal_type;
    private String Cuisine_type;
    private String[] ingredients;
    private String[] allergens;

    public Meal_Class(String name, String description, String price, String Meal_type, String Cuisine_type, String[] ingredients, String[] allergens) {
        set_name(name);
        set_description(description);
        set_price(price);
        set_Meal_type(Meal_type);
        set_Cuisine_type(Cuisine_type);
        set_ingredients(ingredients);
        set_allergens(allergens);

    }

    public Meal_Class() {

    }

    private void set_name(String name) {
        this.name = name;
    }

    private void set_description(String description) {
        this.description = description;
    }

    private void set_price(String price) {
        this.price = price;
    }

    private void set_Cuisine_type(String type) {
        this.Cuisine_type = type;
    }
    private void set_Meal_type(String type) {
        this.Meal_type = type;
    }
    private void set_ingredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    private void set_allergens(String[] allergens) {
        this.allergens = allergens;
    }


    public String get_name() {
        return name;
    }

    public String get_description() {
        return description;
    }

    public String get_price() {
        return price;
    }

    public String get_Meal_type() {
        return Meal_type;
    }
    public String get_Cuisine_type() {
        return Cuisine_type;
    }

    public String[] get_ingredients() {
        return ingredients;
    }

    public String[] get_allergens() {
        return allergens;
    }
}
