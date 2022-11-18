package com.example.mealer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User_Class {
    private String _name;
    private String _last_name;
    private String _email;
    private String _password;

    private String _address;
    private String _type;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    public User_Class () {}

    public User_Class(String name , String last_name, String email , String password ,  String address , String type ){

        this._name = name;
        this._last_name = last_name;
        this._email = email;
        this._password = password;
        this._address = address;
        this._type = type;

    }
    public String get_name(){
        return this._name;
    }
    public String get_last_name(){
        return this._last_name;
    }
    public String get_email(){
        return this._email;
    }
    public String get_password(){
        return this._password;
    }
    public String get_address(){
        return this._address;
    }
    public String get_type(){
        return this._type;
    }
    public void set_name(String name){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_name").setValue(name);
    }
    public void set_last_name(String last_name){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_last_name").setValue(last_name);
    }
    public void set_email(String email){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_email").setValue(email);
    }
    public void set_password(String password){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_password").setValue(password);
    }
    public void set_address(String address){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_address").setValue(address);
    }
    public void set_type(String type){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_type").setValue(type);
    }


}
