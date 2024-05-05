package com.company;
import java.io.Serializable;
public class Sales implements Serializable {
    private String med_name;
    private String med_price;
    private String med_quantity;
    private String med_category;
    private String date;
    public Sales(String name,String category,
                 String quantity,String price,String date) {
        this.med_name=name;
        this.med_category=category;
        this.med_price=price;
        this.med_quantity=quantity;
        this.date=date;
}
    public String getMed_name(){
        return med_name;
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
    public String getDate(){
        return date;
    }

}
