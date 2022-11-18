package com.example.mealer;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cook_Class extends User_Class {
    private ArrayList<Meal_Class> meals;
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

    public Cook_Class(String first_name , String last_name, String email , String password , String address , String unitNum , String postalCode , String country ,String type , String void_check_URL , String description, ArrayList<Meal_Class> mealList){
        super(first_name , last_name, email , password ,  address , type);
        this.description = description;
        this.postalCode = postalCode;
        this.country = country;
        this.unitNum = unitNum;
        this.void_check_URL = void_check_URL;
        this.suspended = false;
        this.suspensionDate = "";
        this.meals = mealList;

    }
    public String get_void_check_URL(){
        return void_check_URL;
    }
    public String get_description(){
        return this.description;
    }
    public String get_postal_code(DataSnapshot snapshot) { return this.postalCode; }
    public String get_country() { return this.country; }
    public String get_unit_num() { return this.unitNum; }
    public boolean get_suspended(){ return this.suspended;  }
    public String get_suspension_date(){ return this.suspensionDate; }
    public void set_postal_code(String postalCode) {FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("_postal_code").setValue(postalCode);}
    public void set_country(String country) { FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("_country").setValue(country); }
    public void set_unit_num(String unitNum) { FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("_unit_num").setValue(unitNum); }
    public void set_void_check(String void_check){
        this.void_check_URL = void_check;
    }
    public void set_description(String description){FirebaseDatabase.getInstance().getReference().child("Users").child(uid).child("_description").setValue(description);}
    public void set_suspended(boolean suspended){this.suspended = suspended;}
    public void set_suspension_date(String suspensionDate){this.suspensionDate = suspensionDate;} //aaaa
    public ArrayList<Meal_Class> get_meals(DataSnapshot dataSnapshot){
        //check if meal is null

        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Meal").child(uid);

        for(DataSnapshot shot : dataSnapshot.getChildren()){
            Meal_Class meal = shot.getValue(Meal_Class.class);
            meals.add(meal);
        }

        return meals;

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
        ref.child(id).setValue(meal);

    }

}


