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
import java.util.WeakHashMap;

public class Manage_Medicines extends JFrame {
    private JButton search_button;
    private JTextField med_name_box;
    public JTextField med_id_box;
    private JTextField med_company_box;
    private JTextField med_quantity_box;
    private JTextField med_price_box;
    private JComboBox med_category_box;
    private JLabel med_name;
    private JLabel med_id;
    private JLabel med_quantity;
    private JLabel med_category;
    private JLabel med_company;
    private JLabel med_price;
    private JPanel panel;
    private JLabel main_label;
    private JButton back_button;
    private JLabel search_label;
    private JButton update_button;
    private JButton delete_button;
    private JButton main_menu_button;
    private int index;  // med index

    public static void Del_Medicine(int med_index){
        try {
            ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
            ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
            list.remove(med_index);
            try {
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Products.dat"));
                os.writeObject(list);
            } catch (Exception a) {
                a.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Manage_Medicines() {
        panel = new JPanel();
        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null);

        main_label = new JLabel("MANAGE MEDICINES");
        search_label = new JLabel("ENTER PRODUCT NAME TO SEARCH");

        Font f1 = new Font("arial",Font.BOLD, 30);
        main_label.setFont(f1);
        main_label.setBounds(285, 43, 800, 50);
        main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
        update_button=new JButton("UPDATE");
        update_button.addActionListener(new MyHandler());
        update_button.setBounds(600,500,120,30);
/////////////////////////////////////////////////////////
        delete_button=new JButton("DELETE");
        delete_button.addActionListener(new MyHandler());
        delete_button.setBounds(475,500,120,30);

////////////////////////////////////////////////////////
// Text Boxes
        med_name_box = new JTextField();
        med_quantity_box = new JTextField();
        med_price_box = new JTextField();
        med_id_box = new JTextField();
        String[] med_category_array={"","Syrup","Tablets","Cap","Drops","Cream","Injection"};
        med_category_box = new JComboBox(med_category_array);
        med_company_box = new JTextField();

        med_name_box.setBounds(329, 170, 227, 27);
        med_id_box.setBounds(425, 250, 200, 27);
        med_category_box.setBounds(425, 300, 200, 27);
        med_company_box.setBounds(425, 350, 200, 27);
        med_quantity_box.setBounds(425, 400, 200, 27);
        med_price_box.setBounds(425, 450, 200, 27);

        med_id_box.setBorder(null);
        med_quantity_box.setBorder(null);
        med_price_box.setBorder(null);
        med_name_box.setBorder(null);
        med_company_box.setBorder(null);

//////////////////////////////////////////////////////////////
// LABELS
        med_id = new JLabel("PRODUCT ID");
        med_name = new JLabel("PRODUCT NAME");
        med_price = new JLabel("PRODUCT PRICE");
        med_company = new JLabel("COMPANY NAME");
        med_quantity = new JLabel("PRODUCT QUANTITY");
        med_category = new JLabel("PRODUCT CATEGORY");

        med_id.setBounds(255, 252, 200, 30);
        med_category.setBounds(255, 302, 200, 30);
        med_company.setBounds(255, 352, 200, 30);
        med_quantity.setBounds(255, 402, 200, 30);
        med_price.setBounds(255, 452, 200, 30);

//////////////////////////////////////////////////////////////
// back button
        back_button = new JButton("BACK");
        back_button.setBounds(45, 500, 120, 30);
        back_button.addActionListener(new MyHandler());
        //back_button.setBackground(Color.WHITE);

// search button
        search_button=new JButton("SEARCH");
        search_button.addActionListener(new MyHandler());
        search_button.setBounds(725,500,120,30);
        Font f=new Font("arial",Font.BOLD,13);
        search_label.setBounds(327,143,250,30);
        search_label.setForeground(Color.WHITE);
        search_label.setFont(f);
/////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.setBounds(45,465,120,30);
        main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
        search_button.setBackground(Color.BLACK);
        search_button.setForeground(Color.WHITE);
        delete_button.setBackground(Color.BLACK);
        delete_button.setForeground(Color.WHITE);
        update_button.setBackground(Color.BLACK);
        update_button.setForeground(Color.WHITE);
        //////////////////////////////////////////////////
        panel.add(med_name_box);
        panel.add(main_menu_button);
        panel.add(med_id);
        panel.add(med_id_box);
        panel.add(med_quantity);
        panel.add(med_quantity_box);
        panel.add(med_category_box);
        panel.add(med_category);
        panel.add(med_price);
        panel.add(med_price_box);
        panel.add(med_company);
        panel.add(med_company_box);
        panel.add(main_label);
        panel.add(back_button);
        panel.add(search_label);
        panel.add(update_button);
        panel.add(search_button);
        panel.add(delete_button);


        add(panel);
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("MEDICAL STORE MANAGEMENT");
        setVisible(true);
    }


    class MyHandler implements ActionListener {

        public int Search(String name) {
            boolean found=false;
            try {
                ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();

                for (index = 0; index < list.size(); index++) {
                    if (list.get(index).getMed_name().equalsIgnoreCase(name)) {
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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == back_button) {
                setVisible(false);
                new Medicine_Menu();
            }
            if (e.getSource() == main_menu_button) {
                setVisible(false);
                new MainMenu();
            }
            // Add Products
            if (e.getSource() == search_button) {
                String search_medicine=med_name_box.getText();
                index=Search(search_medicine);

                if(index!=-1) {
                    try {
                        JOptionPane.showMessageDialog(null,"Medicine Found");
                        //boolean found=false;
                        ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                        ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();

                            med_id_box.setText(list.get(index).getMed_id().toString());
                            med_company_box.setText(list.get(index).getMed_company().toString());
                            med_category_box.setSelectedItem(list.get(index).getMed_category().toString());
                            med_price_box.setText(list.get(index).getMed_price().toString());
                            med_quantity_box.setText(list.get(index).getMed_quantity().toString());

                        output.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Medicine Not Found");
                }

            }
            //////////////////////////////////////////////////////////////////////////////////////////
            if(e.getSource()==update_button){
                String search_medicine=med_name_box.getText();
                index=Search(search_medicine);
                if(index!=-1) { // if medicine exists
                    if (!(med_name_box.getText().equals("")) && !(med_id.toString().equals("")) &&
                            !(med_quantity_box.getText().equals("")) && !(med_price_box.getText().equals(""))
                            && !(med_category_box.getSelectedItem().equals(""))) {
                    try {
                        ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                        ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();

                        String id = med_id_box.getText();
                        String name = med_name_box.getText();
                        String company = med_company_box.getText();
                        String category = med_category_box.getSelectedItem().toString();
                        String quantity = med_quantity_box.getText();
                        String price = med_price_box.getText();

                        list.get(index).setMed_id(id);
                        list.get(index).setMed_name(name);
                        list.get(index).setMed_category(category);
                        list.get(index).setMed_company(company);
                        list.get(index).setMed_quantity(quantity);
                        list.get(index).setMed_price(price);
                        try {
                            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Products.dat"));
                            os.writeObject(list);
                        } catch (Exception a) {
                            a.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(null, "Medicine Updated Successfully");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                    else {
                        JOptionPane.showMessageDialog(null,"Please Fill All Boxes");
                    }
                }

                else {
                    JOptionPane.showMessageDialog(null,"Medicine Not Found");
                }

            }
            /////////////////////////////////////////////////////////////////////////////////
            if(e.getSource()==delete_button) {
                String search_medicine=med_name_box.getText();
                index=Search(search_medicine);
                if (index!=-1) {
                    Del_Medicine(index);
                    JOptionPane.showMessageDialog(null, "Medicine Deleted");
                    med_id_box.setText(null);
                    med_company_box.setText(null);
                    med_category_box.setSelectedItem(null);
                    med_price_box.setText(null);
                    med_quantity_box.setText(null);

                }
                else {
                    JOptionPane.showMessageDialog(null,"Medicine Not Found");
                }
            }
        }


    }
}