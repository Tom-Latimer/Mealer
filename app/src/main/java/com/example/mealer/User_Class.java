package com.example.mealer;

public abstract class User_Class {
    private String name;
    private String last_name;
    private String email;
    private String password;

    private String address;
    private String type;


    public User_Class(String name , String last_name, String email , String password ,  String address , String type ){

        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;

        this.address = address;
        this.type = type;

    }

}
