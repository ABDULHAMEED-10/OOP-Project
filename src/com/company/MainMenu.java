package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Locale;

public class MainMenu extends JFrame{
    private JButton medicine_button;
    private JButton new_sales_button;
    private JButton sales_record_button;
    private JButton change_password_button;
    private JLabel main_menu_label;
    private JButton logout_button;
    private JPanel panel;
    private String store_name;
    private JLabel StoreName;
    public MainMenu(){
        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel=new JPanel(){

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
                g.fillRect(310,124,240,60);
            }
        };

        panel.setLayout(null);
        medicine_button=new JButton("MEDICINE MENU");
        new_sales_button=new JButton("NEW SALES");
        sales_record_button=new JButton("SALES RECORD");
        change_password_button=new JButton("CHANGE PASSWORDS");
        logout_button=new JButton("LOG OUT");

        medicine_button.setBounds(330,220,200,40);
        new_sales_button.setBounds(330,265,200,40);
        sales_record_button.setBounds(330,310,200,40);
        change_password_button.setBounds(330,360,200,40);
        logout_button.setBounds(330,405,200,40);

        Font f1=new Font("arial",Font.BOLD,35);
 // reading store name
    try {
        ObjectInputStream output = new ObjectInputStream(new FileInputStream("login.dat"));
        Password a = null;
        a = (Password) output.readObject();
        store_name=a.getStore_name();
    }
    catch (Exception e){
        e.printStackTrace();
    }
    //////////////////////////////////////////////
        Font f2=new Font("arial",Font.BOLD,30);
        store_name=store_name.toUpperCase(Locale.ROOT);
        StoreName=new JLabel(store_name);
        StoreName.setFont(f2);

        StoreName.setBounds(250,43,800,50);
        StoreName.setForeground(Color.WHITE);

        main_menu_label=new JLabel("MAIN MENU");
        main_menu_label.setFont(f1);
        main_menu_label.setBounds(330,140,600,30);
        main_menu_label.setForeground(Color.WHITE);

        panel.add(medicine_button);
        panel.add(new_sales_button);
        panel.add(sales_record_button);
        panel.add(change_password_button);
        panel.add(main_menu_label);
        panel.add(StoreName);
        panel.add(logout_button);

        medicine_button.setBackground(Color.BLACK);
        medicine_button.setForeground(Color.WHITE);
        new_sales_button.setForeground(Color.WHITE);
        new_sales_button.setBackground(Color.BLACK);
        sales_record_button.setForeground(Color.WHITE);
        sales_record_button.setBackground(Color.BLACK);
        //change_password_button.setForeground(Color.WHITE);
        change_password_button.setBackground(Color.WHITE);
        logout_button.setBackground(Color.WHITE);
        main_menu_label.setForeground(Color.WHITE);

        medicine_button.addActionListener(new MyHandler());
        new_sales_button.addActionListener(new MyHandler());
        sales_record_button.addActionListener(new MyHandler());
        change_password_button.addActionListener(new MyHandler());
        logout_button.addActionListener(new MyHandler());


        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("MEDICAL STORE MANAGEMENT");
        setVisible(true);


    }

    class MyHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==change_password_button){
                setVisible(false);
                new sign_up();

            }
            if(e.getSource()==medicine_button){
                setVisible(false);
                new Medicine_Menu();
            }
            if(e.getSource()==new_sales_button){
                setVisible(false);
                new New_Sales();
            }
            if(e.getSource()==logout_button){
                setVisible(false);
                new Login();
            }
            if(e.getSource()==sales_record_button){
                setVisible(false);
                new Sales_Record();
            }
        }
        }
}

