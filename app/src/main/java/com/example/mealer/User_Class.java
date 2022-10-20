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
    public String get_name(){
        return name;
    }
    public String get_last_name(){
        return last_name;
    }
    public String get_email(){
        return email;
    }
    public String get_password(){
        return password;
    }
    public String get_address(){
        return address;
    }
    public String get_type(){
        return type;
    }
    public void set_name(String name){
        this.name = name;
    }
    public void set_last_name(String last_name){
        this.last_name = last_name;
    }
    public void set_email(String email){
        this.email = email;
    }
    public void set_password(String password){
        this.password = password;
    }
    public void set_address(String address){
        this.address = address;
    }
    public void set_type(String type){
        this.type = type;
    }


}
