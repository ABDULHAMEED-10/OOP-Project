package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class View_Medicine_Stock extends JFrame{
    private JPanel panel;
    private JTable my_table;
    private JLabel main_label;
    private String[][] medicine_data;
    private JScrollPane my_scrollPane;
    private JButton back_button;
    private JLabel table_header;
    //private JFrame frame;
    private JButton main_menu_button;
    private int total_stock_amount=0;
    private JLabel all_stock_amount;
    private JTextField stock_amount_box;

public View_Medicine_Stock(){

    panel = new JPanel();
    ImageIcon img = new ImageIcon("main_menu2.jpg");
    panel = new JPanel() {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
            g.fillRect(131,142,619,25);

        }
    };
    panel.setLayout(null);

    main_label = new JLabel("ALL MEDICINES STOCK");
    Font f1 = new Font("arial",Font.BOLD, 30);
    main_label.setFont(f1);
    main_label.setBounds(275, 43, 800, 50);
    main_label.setForeground(Color.WHITE);
//////////////////////////////////////////////////////////////////////
    back_button=new JButton("BACK");
    back_button.setBounds(27, 510, 120, 30);
    back_button.addActionListener(new MyHandler() );
//////////////////////////////////////////////////////////////////////
    main_menu_button=new JButton("MAIN MENU");
    main_menu_button.setBounds(155,510,120,30);
    main_menu_button.addActionListener(new MyHandler());
//////////////////////////////////////////////////////////////////////
    table_header=new JLabel("MEDICINE ID           MEDICINE NAME   COMPANY NAME  " +
            " CATEGORY         QUANTITY           PRICE PER UNIT");

    table_header.setBounds(133,140,800,30);
    table_header.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////////////////
    stock_amount_box=new JTextField();
    all_stock_amount=new JLabel("TOTAL STOCK AMOUNT");
    all_stock_amount.setBounds(612,100,200,25);
    stock_amount_box.setBounds(610,120,140,20);
    stock_amount_box.setBorder(null);
    all_stock_amount.setForeground(Color.WHITE);
   // .....................................................................
    String column[]={"ID","NAME","COMPANY","CATEGORY","QUANTITY","PRICE"};
    String medicine_data[][]=new String[100][6];
    try {
        ObjectInputStream output = new ObjectInputStream(new FileInputStream("Products.dat"));
        ArrayList<Medicine> list = (ArrayList<Medicine>) output.readObject();
        for(int i=0;i<list.size();i++){
            if(!(list.get(i).toString().equals(""))) {
                if(Integer.parseInt(list.get(i).getMed_quantity())<=0 && !(list.get(i).getMed_name().equals(""))){
                    Manage_Medicines.Del_Medicine(i);
                    continue;
                }
            String a="";
            for (int j=0;j<6;j++){

                    if (j == 0)
                        a = list.get(i).getMed_id();
                    if (j == 1)
                        a = list.get(i).getMed_name();
                    if (j == 2)
                        a = list.get(i).getMed_company();
                    if (j == 3)
                        a = list.get(i).getMed_category();
                    if (j == 4)
                        a = list.get(i).getMed_quantity();
                    if (j == 5) {
                        a = list.get(i).getMed_price();
                        total_stock_amount+=Integer.parseInt(a); // calculating total sales amount
                    }
                    medicine_data[i][j] = a;
                }
            }
        }
    }
    catch (Exception ex){
        ex.printStackTrace();
    }
    stock_amount_box.setText(Integer.toString(total_stock_amount));
    my_table=new JTable(medicine_data,column);
    my_table.setBounds(133,170,618,330);


    panel.add(my_table);
    panel.add(main_label);
    panel.add(main_menu_button);
    panel.add(back_button);
    panel.add(table_header);
    panel.add(all_stock_amount);
    panel.add(stock_amount_box);

    add(panel);
    setSize(900, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setTitle("MEDICAL STORE MANAGEMENT");
    setVisible(true);

}

    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == back_button) {
                setVisible(false);
                new Medicine_Menu();
            }
            if (e.getSource() == main_menu_button) {
                setVisible(false);
                new MainMenu();
            }
        }
    }
}
