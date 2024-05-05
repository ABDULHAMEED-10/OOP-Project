package com.company;
import com.sun.tools.javac.Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Sales_Record extends JFrame{

    private JPanel panel;
    private JTable my_table;
    private JLabel main_label;
    private String[][] medicine_data;
    private JButton back_button;
    private JLabel table_header;
    private JButton delete_button;
    private JTextField sales_amout_box;
    private JLabel sales_amout_label;
    private int total_sales_amount=0;
//private JFrame frame;
    private JButton main_menu_button;
    public Sales_Record(){
        panel = new JPanel();
        ImageIcon img = new ImageIcon("main_menu2.jpg");
        panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), null);
                g.fillRect(131,143,619,25);

            }
        };
        panel.setLayout(null);
        main_label = new JLabel("SALES RECORD");
        Font f1 = new Font("arial",Font.BOLD, 30);
        main_label.setFont(f1);
        main_label.setBounds(330, 43, 800, 50);
        main_label.setForeground(Color.WHITE);
//////////////////////////////////////////////////////////////////////
        back_button=new JButton("BACK");
        back_button.setBounds(27, 510, 120, 30);
        back_button.addActionListener(new MyHandler());
//////////////////////////////////////////////////////////////////////
        main_menu_button=new JButton("MAIN MENU");
        main_menu_button.setBounds(155,510,120,30);
        main_menu_button.addActionListener(new MyHandler());
//////////////////////////////////////////////////////////////////////
        table_header=new JLabel(" MEDICINE NAME           CATEGORY " +
                "                  QUANTITY                      TOTAL PRICE                  DATE");
        table_header.setBounds(133,140,800,30);
        table_header.setForeground(Color.WHITE);
///////////////////////////////////////////////////////////////////////
        delete_button=new JButton("DELETE RECORD");
        delete_button.setBounds(610,510,140,30);
        delete_button.setBackground(Color.BLACK);
        delete_button.setForeground(Color.WHITE);
        delete_button.addActionListener(new MyHandler());
////////////////////////////////////////////////////////////////////////
        sales_amout_box=new JTextField();
        sales_amout_label=new JLabel("TOTAL SALES AMOUNT");
        sales_amout_label.setBounds(612,100,200,25);
        sales_amout_box.setBounds(610,120,140,20);
        sales_amout_box.setBorder(null);
        sales_amout_label.setForeground(Color.WHITE);
        ///////////////////////////////////////////////////////////////
        String column[]={"NAME","COMPANY","CATEGORY","QUANTITY","PRICE"};
        medicine_data=new String[100][5];
        try {
            ObjectInputStream output = new ObjectInputStream(new FileInputStream("Sales_record.dat"));
            ArrayList<Sales> list = (ArrayList<Sales>) output.readObject();
            output.close();
            for(int i=0;i<list.size();i++){
                String a="";
                for (int j=0;j<5;j++){
                    if(!(list.get(i).toString().equals(""))) {
                        if (j == 0)
                            a = list.get(i).getMed_name();
                        if (j == 1)
                            a = list.get(i).getMed_category();
                        if (j == 2)
                            a = list.get(i).getMed_quantity();
                        if (j == 3) {
                            a = list.get(i).getMed_price();
                            total_sales_amount+=Integer.parseInt(a); // calculating total sales amount
                        }
                        if (j == 4)
                            a = list.get(i).getDate();
                        medicine_data[i][j] = a;
                    }
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        sales_amout_box.setText(Integer.toString(total_sales_amount));
        my_table=new JTable(medicine_data,column);
        my_table.setBounds(133,170,618,330);
        panel.add(my_table);
        panel.add(sales_amout_box);
        panel.add(sales_amout_label);
        panel.add(main_label);
        panel.add(main_menu_button);
        panel.add(back_button);
        panel.add(table_header);
        panel.add(delete_button);
        add(panel);
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("MEDICAL STORE MANAGEMENT");
        setVisible(true);
    }
    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() ==back_button) {
                setVisible(false);
                new MainMenu();
            }
            if (e.getSource() ==main_menu_button) {
                setVisible(false);
                new MainMenu();
            }
            if(e.getSource()==delete_button){
                try{
                    JOptionPane.showMessageDialog(null,"SALES RECORD DELETED");
                    ObjectOutputStream ob=new ObjectOutputStream(new FileOutputStream("Sales_record.dat"));
                    ob.writeObject(null);
                    ob.close();
                    new MainMenu();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

}
