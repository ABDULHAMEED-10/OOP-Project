package com.company;
import java.io.File;
import java.io.IOException;
public class tester {
    public static void main(String[] args) throws IOException {
        // login info files
         File login_path=new File("login Path");
         File login=new File(login_path,"Login_info.txt");
         if(!login_path.exists())
             login_path.mkdir();
         if(!login.exists()) {
            login.createNewFile();
             new sign_up();
         }
         else {
              new Login();
         }

    }


}
