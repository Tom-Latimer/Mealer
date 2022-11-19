package com.example.mealer;

public class Client_Class extends User_Class {
    private String cc_information; //will need to rework this, not sure how to store cc info, maybe a class?

    public Client_Class(String name, String last_name, String email, String password, String address, String type, String cc_information) {
        super(name, last_name, email, password, address, type);
        set_cc_information(cc_information);
    }
    public String get_cc_information(){
        return this.cc_information;
    }
    public void set_cc_information(String cc_information){
        this.cc_information = cc_information;
    }
}

