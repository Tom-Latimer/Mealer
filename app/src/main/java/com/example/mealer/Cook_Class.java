package com.example.mealer;

import android.net.Uri;

public class Cook_Class extends User_Class {
    private String description;
    private String postalCode;
    private String country;
    private String unitNum;
    private String void_check_URL;
    private boolean suspended;
    private String suspensionDate;

    public Cook_Class(String first_name , String last_name, String email , String password , String address , String unitNum , String postalCode , String country ,String type , String void_check_URL , String description){
        super(first_name , last_name, email , password ,  address , type);
        set_postal_code(postalCode);
        set_country(country);
        set_unit_num(unitNum);
        set_void_check(void_check_URL);
        set_description(description);
        set_suspended(false);
        set_suspension_date("");
    }
    public String get_void_check_URL(){
        return void_check_URL;
    }
    public String get_description(){
        return description;
    }
    public String get_postal_code() { return postalCode; }
    public String get_country() { return country; }
    public String get_unit_num() { return unitNum; }
    public boolean get_suspended(){ return suspended; }
    public String get_suspension_date(){ return suspensionDate;}
    public void set_postal_code(String postalCode) { this.postalCode = postalCode; }
    public void set_country(String country) { this.country = country; }
    public void set_unit_num(String unitNum) { this.unitNum = unitNum; }
    public void set_void_check(String void_check){
        this.void_check_URL = void_check;
    }
    public void set_description(String description){this.description = description;}
    public void set_suspended(boolean suspended){this.suspended = suspended;}
    public void set_suspension_date(String suspensionDate){this.suspensionDate = suspensionDate;}
    public void create_meal(String name, String description, String price, String Meal_type, String Cuisine_type, String[] ingredients, String[] allergens){
        Meal_Class meal = new Meal_Class(name, description, price, Meal_type, Cuisine_type, ingredients, allergens);

    }
    private void delete_meal(Meal_Class meal){

    }
    private void edit_meal(){

    }

}


