package com.project.electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login");

        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel username = new JLabel("UserName");
        username.setBounds(300, 60, 100, 20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel loggin = new JLabel("Loggin In As");
        loggin.setBounds(300, 140, 100, 20);
        add(loggin);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(66, 127, 219));
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(66, 127, 219));
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBackground(new Color(66, 127, 219));
        signupButton.setBounds(400, 210, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofileOne = new ImageIcon(profileTwo);
        JLabel profilelabel = new JLabel(fprofileOne);
        profilelabel.setBounds(5, 5, 250, 250);
        add(profilelabel);

        setSize(650, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   
    	@Override
    	public void actionPerformed(ActionEvent e) {
    	    if (e.getSource() == loginButton) {
    	        String susername = userText.getText();
    	        String spassword = passwordText.getText();
    	        String suser = loginChoice.getSelectedItem();

    	        if (susername.isEmpty() || spassword.isEmpty()) {
    	            JOptionPane.showMessageDialog(
    	                    null,
    	                    "Please fill all fields!",
    	                    "Warning",
    	                    JOptionPane.WARNING_MESSAGE
    	            );
    	            return; 
    	        }

    	        
    	        try {
    	            database c = new database();
    	            String query;

    	            if (suser.equals("Admin")) {
    	                query = "SELECT username, password, usertype " +
    	                        "FROM signup " +
    	                        "WHERE username='" + susername +
    	                        "' AND password='" + spassword +
    	                        "' AND usertype='Admin'";
    	            } else {
    	                query = "SELECT s.username, s.password, s.usertype, n.meterno " +
    	                        "FROM signup s " +
    	                        "JOIN new_customer n ON s.meter_no = n.meterno " +
    	                        "WHERE s.username='" + susername +
    	                        "' AND s.password='" + spassword +
    	                        "' AND s.usertype='Customer'";
    	            }

    	            ResultSet resultset = c.statement.executeQuery(query);

    	            if (resultset.next()) {
    	                if (suser.equals("Admin")) {
    	                    setVisible(false);
    	                    new main_class(suser, null);
    	                } else {
    	                    String meter = resultset.getString("meterno");
    	                    setVisible(false);
    	                    new main_class(suser, meter);
    	                }
    	            } else {
    	                JOptionPane.showMessageDialog(null, "Invalid Login");
    	            }
    	        } catch (SQLException e1) {
    	            e1.printStackTrace();
    	        }
    	    } else if (e.getSource() == cancelButton) {
    	        setVisible(false);
    	    } else if (e.getSource() == signupButton) {
    	        setVisible(false);
    	        new Signup();
    	    }
    	}

    public static void main(String[] args) {
        new Login();
    }
}
