package com.company;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
public class Login extends JFrame implements Serializable{
    private JTextField textBox_1;
    private JTextField textBox_2;
    private JLabel label_1;
    private JLabel label_2;
    private JButton ok_button;
    // login info files
    static File login_path=new File("login Path");
    static  File login=new File(login_path,"Login_info.dat");
    private JPanel panel;
    private JLabel login_label;
    private JButton forget_button;
    public Login(){
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
        label_1=new JLabel("USERNAME");
        label_2=new JLabel("PASSWORD");
        login_label=new JLabel("LOGIN");
        label_1.setBounds(530,245,100,23);
        label_2.setBounds(530,297,100,23);
        login_label.setBounds(594,147,100,23);
        label_1.setForeground(Color.WHITE);
        label_2.setForeground(Color.WHITE);
        Font f=new Font("arial",Font.BOLD,22);
        login_label.setFont(f);
        ok_button=new JButton("LOGIN");
        forget_button=new JButton("FORGET PASSWORD?");
        forget_button.setBorder(null);
        forget_button.addActionListener(new MyHandler());
        ok_button.setBorder(null);
        ok_button.addActionListener(new MyHandler());
        ok_button.setBounds(593,353,70,22);
        forget_button.setBounds(651,425,130,22);
        ok_button.setBackground(Color.WHITE);
        forget_button.setBackground(Color.WHITE);
        getContentPane().add(panel);
        panel.add(ok_button);
        panel.add(forget_button);
        panel.add(textBox_1);
        panel.add(textBox_2);
        panel.add(label_1);
        panel.add(label_2);
        panel.add(login_label);
        add(panel);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("LOGIN");
        setVisible(true);

    }
    class MyHandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==forget_button){
                setVisible(false);
                new Forget_Password();
            }
            if(e.getSource()==ok_button){
                try {
                    ObjectInputStream output=new ObjectInputStream(new FileInputStream("login.dat"));
                    Password a=null;
                    a=(Password) output.readObject();
                    output.close();
                        if ((textBox_1.getText().equals(a.getUsername())) && (textBox_2.getText().equals(a.getPassword()))) {
                            setVisible(false);
                            new MainMenu();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
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
