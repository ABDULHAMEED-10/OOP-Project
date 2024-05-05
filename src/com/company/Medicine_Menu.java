package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Medicine_Menu extends JFrame{
    private JButton add_med_btn;
    private JButton manage_med_btn;
    private JPanel panel;
    private JLabel menu_label;
    private JButton back_button;
    private JButton view_med_button;

    public Medicine_Menu() {
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

        add_med_btn=new JButton("ADD MEDICINE");
        manage_med_btn=new JButton("MANAGE MEDICINES");
        back_button=new JButton("BACK");
        view_med_button=new JButton("VIEW ALL STOCK");

        menu_label=new JLabel("MEDICINE MENU");
        Font f1=new Font("arial",Font.BOLD,30);
        menu_label.setFont(f1);
        menu_label.setBounds(325,43,800,50);
        menu_label.setForeground(Color.WHITE);

        add_med_btn.addActionListener(new MyHandler());
        manage_med_btn.addActionListener(new MyHandler());
        back_button.addActionListener(new MyHandler());
        view_med_button.addActionListener(new MyHandler());

        add_med_btn.setBounds(330,220,200,40);
        manage_med_btn.setBounds(330,265,200,40);
        view_med_button.setBounds(330,310,200,40);
        back_button.setBounds(27, 510, 120, 30);

        add_med_btn.setBackground(Color.BLACK);
        add_med_btn.setForeground(Color.WHITE);
        manage_med_btn.setForeground(Color.WHITE);
        manage_med_btn.setBackground(Color.BLACK);
       
        view_med_button.setBackground(Color.BLACK);
        view_med_button.setForeground(Color.WHITE);

        panel.add(add_med_btn);
        panel.add(manage_med_btn);
        panel.add(back_button);
        panel.add(menu_label);
        panel.add(view_med_button);

        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("MEDICAL STORE MANAGEMENT");
        setVisible(true);

    }

    class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new MainMenu();
            }
            if(e.getSource()==add_med_btn){
                setVisible(false);
                new Add_Medicine();
            }
            if(e.getSource()==manage_med_btn){
                setVisible(false);
                new Manage_Medicines();
            }
         
            if(e.getSource()==view_med_button){
                setVisible(false);
                new View_Medicine_Stock();
            }


            }


        }
    }

