package com.company;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class sign_up extends JFrame implements Serializable{
    private JTextField textBox_1;
    private JTextField textBox_2;
    private JTextField textBox_3;
    private JLabel label_1;
    private JLabel label_2;
    private JButton ok_button;
    private JPanel panel;
    private JLabel sign_label;
    private JLabel label_3;
    private JLabel label_4;
    private JTextField textBox_4;
    private JTextField textBox_5;
    private JLabel label_5;

    public sign_up(){
        ImageIcon img = new ImageIcon("login.jpg");
        panel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, getWidth(),getHeight(),null);
            }
        };
        panel.setLayout(null);
        textBox_1=new JTextField();
        textBox_2=new JTextField();
        textBox_3=new JTextField();
        textBox_4=new JTextField();
        textBox_5=new JTextField();

        textBox_1.setBounds(530,215,192,22);
        textBox_2.setBounds(530,262,192,22);
        textBox_3.setBounds(530,311,192,22);
        textBox_4.setBounds(530,360,192,22);
        textBox_5.setBounds(530,410,192,22);

        textBox_1.addActionListener(new MyHandler());
        textBox_2.addActionListener(new MyHandler());
        textBox_3.addActionListener(new MyHandler());
        textBox_4.addActionListener(new MyHandler());
        textBox_5.addActionListener(new MyHandler());
        textBox_1.setBorder(null);
        textBox_2.setBorder(null);
        textBox_3.setBorder(null);
        textBox_4.setBorder(null);
        textBox_5.setBorder(null);
        label_1=new JLabel("USERNAME");
        label_2=new JLabel("PASSWORD");
        label_3=new JLabel("STORE NAME");
        label_4=new JLabel("EMAIL");
        label_5=new JLabel("EMAIL PASSWORD");
        label_1.setBounds(530,195,100,23);
        label_2.setBounds(530,242,100,23);
        label_3.setBounds(530,290,100,23);
        label_4.setBounds(530,340,100,23);
        label_5.setBounds(530,390,170,23);
        label_1.setForeground(Color.WHITE);
        label_2.setForeground(Color.WHITE);
        label_3.setForeground(Color.WHITE);
        label_4.setForeground(Color.WHITE);
        label_5.setForeground(Color.WHITE);
        ok_button=new JButton("SIGNUP");
        ok_button.setBorder(null);
        ok_button.addActionListener(new MyHandler());
        ok_button.setBounds(590,436,70,20);
        ok_button.setBackground(Color.WHITE);

        sign_label=new JLabel("SIGN UP");
        Font f=new Font("arial",Font.BOLD,22);
        sign_label.setFont(f);
        sign_label.setBounds(582,147,100,23);

        panel.add(ok_button);
        getContentPane().add(panel);
        panel.add(textBox_1);
        panel.add(textBox_2);
        panel.add(textBox_3);
        panel.add(label_1);
        panel.add(label_2);
        panel.add(sign_label);
        panel.add(label_3);
        panel.add(label_4);
        panel.add(textBox_4);
        panel.add(textBox_5);
        panel.add(label_5);
        add(panel);
        setSize(900,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("SIGN UP");
        setVisible(true);
    }

    class MyHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==ok_button){
                Password object=new Password(textBox_1.getText(),textBox_2.getText(),
                        textBox_3.getText(),textBox_4.getText(),textBox_5.getText());
                try {

                    if(textBox_1.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please Enter Username");
                    if(textBox_2.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please Enter Password");
                    if(textBox_3.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please Your Store Name");
                    if(textBox_4.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Please Enter Your Email");
                    if(!(textBox_1.getText().equals("")) && !(textBox_2.getText().equals("")) &&
                            !(textBox_3.getText().equals("")) && !(textBox_4.getText().equals(""))){

                        ObjectOutputStream input = new ObjectOutputStream(new FileOutputStream("login.dat"));
                        input.writeObject(object);
                        input.close();
                        JOptionPane.showMessageDialog(null, "Login Created Successfully");
                        setVisible(false);
                        new Login();
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
            }
        }
        }
    }

}
