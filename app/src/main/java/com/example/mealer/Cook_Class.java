package com.example.mealer;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cook_Class extends User_Class {
    private ArrayList<Meal_Class> meals;
    private ArrayList<PurchaseRequest> purchaseRequests;
    private String description;
    private String postalCode;
    private String country;
    private String unitNum;
    private String void_check_URL;
    private boolean suspended;
    private String suspensionDate;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    public Cook_Class(){

    }

    public Cook_Class(String first_name , String last_name, String email , String password , String address , String unitNum , String postalCode , String country ,String type , String void_check_URL , String description, ArrayList<Meal_Class> mealList, ArrayList<PurchaseRequest> purchaseRequestsList){
        super(first_name , last_name, email , password ,  address , type);
        set_postal_code(postalCode);
        set_country(country);
        set_unit_num(unitNum);
        set_void_check(void_check_URL);
        set_description(description);
        set_suspended(false);
        set_suspension_date("");
        set_meals(mealList);
        set_purchaseRequests(purchaseRequestsList);
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
    public ArrayList<Meal_Class> get_meals(){
        return meals;
    }
    public void set_meals(ArrayList<Meal_Class> lstMeal){
        meals = lstMeal;
    }
    public ArrayList<PurchaseRequest> get_purchaseRequests(){
        return purchaseRequests;
    }
    public void set_purchaseRequests(ArrayList<PurchaseRequest> lstPurchaseRequests){
        purchaseRequests = lstPurchaseRequests;
    }

    public void delete_meal(String meal){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Meals").child(uid);
        ref.child(meal).removeValue();
    }

    public void add_meal(Meal_Class meal){
        //check if meal is null

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Meals").child(uid);

        String id = ref.push().getKey();
        meal.set_mealID(id);
        meal.set_cookID(uid);
        ref.child(id).setValue(meal);

    }

}























