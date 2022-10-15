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
    private String get_name(){
        return name;
    }
    private String get_last_name(){
        return last_name;
    }
    private String get_email(){
        return email;
    }
    private String get_password(){
        return password;
    }
    private String get_address(){
        return address;
    }
    private String get_type(){
        return type;
    }
    private void set_name(String name){
        this.name = name;
    }
    private void set_last_name(String last_name){
        this.last_name = last_name;
    }
    private void set_email(String email){
        this.email = email;
    }
    private void set_password(String password){
        this.password = password;
    }
    private void set_address(String address){
        this.address = address;
    }
    private void set_type(String type){
        this.type = type;
    }


}
