package com.company;

import java.io.Serializable;

public class Medicine implements Serializable {
    private String med_name;
    private String med_company;
    private String med_price;
    private String med_quantity;
    private String med_id;
    private String med_category;

public Medicine(String id,String name,String category
        ,String company,String quantity,String price) {
    this.med_id=id;
    this.med_name=name;
    this.med_category=category;
    this.med_company=company;
    this.med_price=price;
    this.med_quantity=quantity;
}
public String getMed_name(){
    return med_name;
}
public String getMed_company(){
    return med_company;
}
public String getMed_category(){
    return med_category;
}
public String getMed_price(){
    return med_price;
}
public String getMed_quantity(){
    return med_quantity;
}
public String getMed_id(){
    return med_id;
}
public void setMed_name(String name){
    this.med_name=name;
}
public void setMed_price(String price){
    this.med_price=price;
}
public void setMed_company(String company){
    this.med_company=company;
}
public void setMed_quantity(String quantity){
    this.med_quantity=quantity;
}
public  void setMed_id(String id){
    this.med_id=id;
}
public void setMed_category(String category){
    this.med_category=category;
}
}
