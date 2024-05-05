package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Locale;

public class New_Sales extends JFrame{
    public static ArrayList<Sales> sales_record_list=new ArrayList<>();
    private JTable my_table;
    private JPanel panel;
    private JLabel main_label;
    private JLabel med_name_label;
    private JLabel med_quantity_label;
    private JTextField name_Box;
    private JTextField quantity_box;
    private JButton cart_button;
    private JButton back_button;
    private String[][] medicine_data;
    private String[] column;
    private JComboBox med_category_box;
    private JLabel grand_total_label;
    private JLabel table_header;
    private JButton done_button;
    private int grand_total=0;
    private int index;
    private  int quantity;
    private JButton refresh_button;
    private int table_index=0;
    private int[] array;
    private int array_index=0;
    private int[] sold_quantities_array;
    private int sold_quantity_index;
    public New_Sales(){
        panel=new JPanel();
        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel=new JPanel(){

            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
                g.fillRect(133,272,617,30);

            }
        };
        array=new int[11];
        sold_quantities_array=new int[11];
        panel.setLayout(null);
        main_label=new JLabel("BILLING");
        Font f1=new Font("arial",Font.BOLD,30);
        main_label.setFont(f1);
        main_label.setBounds(385,43,800,50);
        main_label.setForeground(Color.WHITE);
        /////////////////////////////////////////////////////
        name_Box=new JTextField();
        quantity_box=new JTextField();
        med_name_label=new JLabel("MEDICINE NAME");
        med_quantity_label=new JLabel("MEDICINE QUANTITY");
        cart_button=new JButton("Add to Cart");
        back_button=new JButton("BACK");
        grand_total_label=new JLabel("GRAND TOTAL :");
        String[] med_category_array={"Tablets","Syrup","Cap","Drops","Cream","Injection"};
        med_category_box=new JComboBox(med_category_array);
        done_button=new JButton("DONE");
        refresh_button=new JButton("CLEAR");

        name_Box.setBounds(350,180,200,25);
        quantity_box.setBounds(350,230,200,25);
        med_name_label.setBounds(200,182,200,30);
        med_quantity_label.setBounds(200,232,200,30);
        back_button.setBounds(27, 510, 120, 30);
        cart_button.setBounds(570, 230, 110, 25);
        done_button.setBounds(632, 510, 120, 30);
        refresh_button.setBounds(500, 510, 100, 30);
        med_category_box.setBounds(570,180,110,25);
        Font f2=new Font("arial",Font.BOLD,15);
        grand_total_label.setBounds(580,477,150,25);
        grand_total_label.setFont(f2);

        cart_button.setBackground(Color.BLACK);
        cart_button.setForeground(Color.WHITE);
        med_name_label.setForeground(Color.WHITE);
        med_quantity_label.setForeground(Color.WHITE);
        done_button.setBackground(Color.BLACK);
        done_button.setForeground(Color.WHITE);
        name_Box.setBorder(null);
        quantity_box.setBorder(null);
        ////////////////////////////////////////////////////
        back_button.addActionListener(new MyHandler());
        cart_button.addActionListener(new MyHandler());
        done_button.addActionListener(new MyHandler());
        refresh_button.addActionListener(new MyHandler());
        ////////////////////////////////////////////////
        String[] column1={"NAME","CATEGORY","QUANTITY","PRICE PER UNIT","TOTAL PRICE"};
        medicine_data=new String[11][5];
        my_table=new JTable(medicine_data,column1);
        my_table.setBounds(133,300,618,200);
        my_table.setBackground(Color.WHITE);
        ////////////////////////////////////////////////
        table_header=new JLabel("   MEDICINE NAME         CATEGORY                   " +
                "  QUANTITY                    PRICE PER UNIT             TOTAL PRICE");
        table_header.setBounds(133,273,800,30);
        table_header.setForeground(Color.WHITE);
        ///////////////////////////////////////////////
        panel.add(done_button);
        panel.add(grand_total_label);
        panel.add(med_category_box);
        panel.add(main_label);
        panel.add(med_name_label);
        panel.add(med_quantity_label);
        panel.add(name_Box);
        panel.add(quantity_box);
        panel.add(cart_button);
        panel.add(back_button);
        panel.add(my_table);
        panel.add(table_header);
        panel.add(refresh_button);
        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("NEW SALES");
        setVisible(true);

    }
    class MyHandler implements ActionListener{

        public int Search_Med(String name,String category) {
            boolean found=false;
            try {
                ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
                for (index = 0; index < list.size(); index++) {
                    if ((list.get(index).getMed_name().equalsIgnoreCase(name)) &&
                            (list.get(index).getMed_category().equals(category))) {
                        found = true;
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (found)
                return index;
            else
                return -1;
        }
        public void add_to_sales_record(String name,String category,String quantity,String price,String date){
            Sales object=new Sales(name,category,quantity,price,date);
            sales_record_list.clear();
            sales_record_list.add(object);
            try {
                try {
                    ObjectInputStream output = new ObjectInputStream(new FileInputStream("Sales_record.dat"));
                    ArrayList<Sales> list1 = (ArrayList<Sales>) output.readObject();
                    for (int i = 0; i < list1.size(); i++) {
                        sales_record_list.add(list1.get(i));
                    }
                    output.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("Sales_record.dat"));
                f.writeObject(sales_record_list);
                f.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == back_button) {
                setVisible(false);
                new MainMenu();
            }
            if(e.getSource()==refresh_button){
                grand_total=0;
                table_index=-1;
                for(int i=0;i<medicine_data.length;i++){
                    for (int j=0;j<5;j++){
                        medicine_data[i][j]="";
                    }
                }
                my_table.repaint();
               // my_table.repaint();
                grand_total_label.setText("");
                grand_total_label.repaint();
            }
            ////////////////////////////////////////////////////////////////
            if(e.getSource()==done_button ){
                // REMAINING QUANTITY CALCULATING
                ///////////////////////////////////////////////
                grand_total_label.setText("GRAND TOTAL: "+grand_total);
                grand_total_label.repaint();       //updating
                try {
                    ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                    ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();

                    for(int i=0;i<array_index;i++){
                            int available_quantity = Integer.parseInt(list.get(array[i]).getMed_quantity());
                            int r_q = available_quantity-sold_quantities_array[i];
                            String remaining_quantity = Integer.toString(r_q);
                            list.get(array[i]).setMed_quantity(remaining_quantity);
                    }
                    output.close();
                    //updating list
                    ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("Products.dat"));
                    f.writeObject(list);
                    f.close();
                    ////////////////////////////////////////////
                    String name="";
                    String category="";
                    String quantity="";
                    String  price="";
                    String date="";
                    for(int i=0;i<table_index;i++){
                        for(int j=0;j<5;j++){
                            if(!(medicine_data[i][j].equals(""))) {
                                if (j == 0)
                                    name = medicine_data[i][j];
                                if (j == 1)
                                    category = medicine_data[i][j];
                                if (j == 2)
                                    quantity = medicine_data[i][j];
                                if (j == 3)
                                    date = java.time.LocalDate.now().toString();
                                if (j == 4)
                                    price = medicine_data[i][j];
                            }
                        }
                        add_to_sales_record(name, category,
                                quantity, price, date);
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,"Please Collect "+grand_total+" Rs");
            }
            ////////////////////////////////////////////////////////////////////////////////////
            if(e.getSource()==cart_button){
                if(!(name_Box.getText().equals("")) && !(quantity_box.getText().equals("")))
                {
                    index = Search_Med(name_Box.getText(),med_category_box.getSelectedItem().toString());
                    //////////////////
                    array[array_index]=index; // storing saled medicines indexex
                    array_index++;  //
                    sold_quantities_array[sold_quantity_index]=Integer.parseInt(quantity_box.getText());
                    sold_quantity_index++;
                    /////////////////
                    if (index != -1) {
                        try {
                            ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                            ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
                            output.close();
                            /////////////////////////////////////////////
                            quantity = Integer.parseInt(quantity_box.getText());
                            int available_quantity=Integer.parseInt(list.get(index).getMed_quantity());
                            if(quantity<=available_quantity)
                            {  // checking available quantity
                                String text = "";
                                for (int j = 0; j < 5; j++) {
                                        if (j == 0)
                                            text = list.get(index).getMed_name();
                                        if (j == 1)
                                            text = list.get(index).getMed_category();
                                        if (j == 2)
                                            text = quantity_box.getText();
                                        if (j == 3) {
                                            text=list.get(index).getMed_price();
                                        }
                                        if (j == 4) {
                                            int price = Integer.parseInt(list.get(index).getMed_price());
                                            int total_price = quantity * price;  // calculating price
                                            grand_total += total_price;   // calculating grand total
                                            text = Integer.toString(total_price);
                                        }
                                    medicine_data[table_index][j] = text;
                                }
                                table_index++;
                            //////////////////////////////////////////////

                                my_table.repaint();  //updating the table
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"Sorry, Not Enough Quantity of Medicine is AVailable");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Medicine Not Found");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please Enter Medicine Name and Quantity");
            }
            }


            }
        }
}