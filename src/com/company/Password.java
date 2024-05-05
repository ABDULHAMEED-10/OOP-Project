package com.company;

import java.io.Serializable;

public class Password implements Serializable {
    private  String username;
    private String password;
    private String store_name;
    private String email;
    private String email_password;

public Password(String username,String password,String store_name,String email,String email_password){
    this.username=username;
    this.password=password;
    this.store_name=store_name;
    this.email=email;
    this.email_password=email_password;
}
public String getUsername(){
    return username;
}
public String getPassword(){
    return password;
}
public String getStore_name(){
    return store_name;
}
public String getEmail(){
    return email;
}
public String getEmail_password(){
    return email_password;
}

}
