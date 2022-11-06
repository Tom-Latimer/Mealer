package com.example.mealer;

public class User_Class {
    private String _name;
    private String _last_name;
    private String _email;
    private String _password;

    private String _address;
    private String _type;

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
        return _name;
    }
    public String get_last_name(){
        return _last_name;
    }
    public String get_email(){
        return _email;
    }
    public String get_password(){
        return _password;
    }
    public String get_address(){
        return _address;
    }
    public String get_type(){
        return _type;
    }
    public void set_name(String name){
        this._name = name;
    }
    public void set_last_name(String last_name){
        this._last_name = last_name;
    }
    public void set_email(String email){
        this._email = email;
    }
    public void set_password(String password){
        this._password = password;
    }
    public void set_address(String address){
        this._address = address;
    }
    public void set_type(String type){
        this._type = type;
    }


}
