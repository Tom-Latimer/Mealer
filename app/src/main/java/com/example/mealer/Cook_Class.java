package com.example.mealer;

public class Cook_Class extends User_Class {
    private String void_check; //needs to be a picture, not sure how to store that yet
    private String description;


    public Cook_Class(String name , String last_name, String email , String password ,  String address , String type , String void_check , String description){
        super(name , last_name, email , password ,  address , type );
        set_void_check(void_check);
        set_description(description);
    }
    private void set_void_check(String void_check){
        this.void_check = void_check;
    }
    private void set_description(String description){
        this.description = description;
    }
    private void create_meal(String name, String description, String price, String Meal_type, String Cuisine_type, String[] ingredients, String[] allergens){
        Meal_Class meal = new Meal_Class(name, description, price, Meal_type, Cuisine_type, ingredients, allergens);

    }
    private void delete_meal(Meal_Class meal){

    }
    private void edit_meal(){

    }

}


