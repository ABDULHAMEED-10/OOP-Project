package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Add_Medicine extends JFrame implements Serializable {
    public static ArrayList<Medicine> medicine_list=new ArrayList<>();
    private JTextField med_name_box;
    private JTextField med_id_box;
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
    private JButton add_button;
    private JButton main_menu_button;
public Add_Medicine(){
    panel=new JPanel();
    ImageIcon img = new ImageIcon("main_menu2.jpg");
    panel=new JPanel(){

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
        }
    };
    panel.setLayout(null);
    main_label=new JLabel("ADD PRODUCT");
    Font f1=new Font("arial",Font.BOLD,30);
    main_label.setFont(f1);
    main_label.setBounds(325,40,800,50);
    main_label.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////
// Text Boxes
    med_name_box=new JTextField();
    med_quantity_box=new JTextField();
    med_price_box=new JTextField();
    med_id_box=new JTextField();
    String[] med_category_array={"","Syrup","Tablets","Cap","Drops","Cream","Injection"};
    med_category_box=new JComboBox(med_category_array);
    med_company_box=new JTextField();

    med_id_box.setBounds(430,180,200,27);
    med_name_box.setBounds(430,230,200,27);
    med_category_box.setBounds(430,280,200,27);
    med_company_box.setBounds(430,330,200,27);
    med_quantity_box.setBounds(430,380,200,27);
    med_price_box.setBounds(430,430,200,27);

    med_id_box.setBorder(null);
    med_quantity_box.setBorder(null);
    med_price_box.setBorder(null);
    med_name_box.setBorder(null);
    med_company_box.setBorder(null);

//////////////////////////////////////////////////////////////
// LABELS
    med_id=new JLabel("PRODUCT ID");
    med_name=new JLabel("PRODUCT NAME");
    med_price=new JLabel("PRODUCT PRICE");
    med_company=new JLabel("COMPANY NAME");
    med_quantity=new JLabel("PRODUCT QUANTITY");
    med_category=new JLabel("PRODUCT CATEGORY");

    med_id.setBounds(260,182,200,30);
    med_name.setBounds(260,232,200,30);
    med_category.setBounds(260,282,200,30);
    med_company.setBounds(260,332,200,30);
    med_quantity.setBounds(260,382,200,30);
    med_price.setBounds(260,432,200,30);


//////////////////////////////////////////////////////////////
// back button
    back_button=new JButton("BACK");
    back_button.setBounds(45,500,120,30);
//add button
    add_button=new JButton("SAVE");
    add_button.setBounds(725,500,120,30);
    add_button.setBackground(Color.BLACK);
    add_button.setForeground(Color.WHITE);

    back_button.addActionListener(new MyHandler());
    add_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
    main_menu_button=new JButton("MAIN MENU");
    main_menu_button.addActionListener(new MyHandler());
    main_menu_button.setBounds(45,465,120,30);
    main_menu_button.addActionListener(new MyHandler());
/////////////////////////////////////////////////////////////
    panel.add(med_name);
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
    panel.add(add_button);
    panel.add(back_button);

    add(panel);
    setSize(900,600);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("MEDICAL STORE MANAGEMENT");
    setVisible(true);

}
    class MyHandler implements ActionListener {
        public boolean Search_Med(String name,String category) {
            boolean found=false;
            try {
                ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
                for (int i = 0; i < list.size(); i++) {
                    if ((list.get(i).getMed_name().equalsIgnoreCase(name)) &&
                            (list.get(i).getMed_category().equals(category))) {
                        found = true;
                        break;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return found;
        }
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new Medicine_Menu();
            }
            if (e.getSource() == main_menu_button) {
                setVisible(false);
                new MainMenu();
            }
    // Add Products
            if(e.getSource()==add_button) {
                boolean Found = Search_Med(med_name_box.getText(), med_category_box.getSelectedItem().toString());
                if (!Found) {
                    medicine_list.clear();
                    if (!(med_name_box.getText().equals("")) && !(med_id.toString().equals("")) &&
                            !(med_quantity_box.getText().equals("")) && !(med_price_box.getText().equals(""))
                            && !(med_category_box.getSelectedItem().equals(""))) {

                        Medicine med_object = new Medicine(med_id_box.getText(), med_name_box.getText(),
                                med_category_box.getSelectedItem().toString(), med_company_box.getText(),
                                med_quantity_box.getText(), med_price_box.getText());

                        medicine_list.add(med_object);
                        try {

                            try {
                                ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
                                ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
                                for (int i = 0; i < list.size(); i++) {
                                    medicine_list.add(list.get(i));
                                }
                                output.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("Products.dat"));
                            f.writeObject(medicine_list);
                            f.close();
                            JOptionPane.showMessageDialog(null, "PRODUCT SAVED SUCCESSFULLY");
                            ///////////////////////
                            med_name_box.setText(null);
                            med_id_box.setText(null);
                            med_company_box.setText(null);
                            med_category_box.setSelectedItem(null);
                            med_price_box.setText(null);
                            med_quantity_box.setText(null);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "PLEASE PROVIDE COMPLETE INFORMATION");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"This Medicine Already Exists");
                }
            }


        }


    }



}
