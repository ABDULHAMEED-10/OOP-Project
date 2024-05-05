package com.company;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class Forget_Password extends JFrame implements Serializable{
    private JTextField textBox_1;
    private JTextField textBox_2;
    private JLabel label_1;
    private JLabel label_2;
    private JButton ok_button;
    // login info files
    private JPanel panel;
    private JLabel main_label;
    private JButton back_button;
    public Forget_Password(){
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
        textBox_1.setBounds(530,265,192,22);
        textBox_2.setBounds(530,317,192,22);
        textBox_1.addActionListener(new MyHandler());
        textBox_2.addActionListener(new MyHandler());
        textBox_1.setBorder(null);
        textBox_2.setBorder(null);
        label_1=new JLabel("EMAIL");
        label_2=new JLabel("PASSWORD");
        main_label=new JLabel("PASSWORD RECOVERY");
        label_1.setBounds(530,245,100,23);
        label_2.setBounds(530,297,150,23);
        main_label.setBounds(517,147,300,23);
        label_1.setForeground(Color.WHITE);
        label_2.setForeground(Color.WHITE);
        Font f=new Font("arial",Font.BOLD,20);
        main_label.setFont(f);
        ok_button=new JButton("DONE");
        ok_button.setBorder(null);
        ok_button.addActionListener(new MyHandler());
        ok_button.setBounds(593,353,70,22);
        ok_button.setBackground(Color.WHITE);
        back_button=new JButton("BACK");
        back_button.setBounds(475,425,70,22);
        back_button.setBackground(Color.WHITE);
        back_button.setBorder(null);
        back_button.addActionListener(new MyHandler());
        panel.add(back_button);
        panel.add(ok_button);
        getContentPane().add(panel);
        panel.add(textBox_1);
        panel.add(textBox_2);
        panel.add(label_1);
        panel.add(label_2);
        panel.add(main_label);
        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Forget Password");
        setVisible(true);

    }
    class MyHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==back_button){
                setVisible(false);
                new Login();
            }
            if(e.getSource()==ok_button){
                try {
                    ObjectInputStream output=new ObjectInputStream(new FileInputStream("login.txt"));
                    Password a=null;
                    a=(Password) output.readObject();
                    output.close();
                    if ((textBox_1.getText().equals(a.getEmail()) && (textBox_2.getText().equals(a.getEmail_password())))) {
                        setVisible(false);
                        new MainMenu();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Incorrect Email or Password");
                        textBox_1.setText("");
                        textBox_2.setText("");

                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        }
    }

}
