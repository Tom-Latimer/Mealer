package com.example.mealer;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Client_Class extends User_Class {
    private String cc_information; //will need to rework this, not sure how to store cc info, maybe a class?
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    public Client_Class(String name, String last_name, String email, String password, String address, String type, String cc_information) {
        super(name, last_name, email, password, address, type);
        this.cc_information = cc_information;
    }
    public String get_cc_information(){
        return FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_cc_information").toString();
    }

    public void set_cc_information(String cc_information){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("_cc_information").setValue(cc_information);
    }
}

