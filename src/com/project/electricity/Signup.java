package com.project.electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup extends JFrame implements ActionListener{
	
	Choice loginASCho;
    TextField meterText,employerText, usernameText, nameText,  passwordText;
    JButton create,back;
    
	Signup(){
		super("Signup Page");
		getContentPane().setBackground(new Color(173, 216, 230));

		
		JLabel createAs=new JLabel("Create Account As");
		createAs.setBounds(30, 50, 125, 20);
		add(createAs);
		
		
		loginASCho=new Choice();
		 loginASCho.add("Admin");
		 loginASCho.add("Customer");
		 loginASCho.setBounds(170, 50, 120, 20);
		 add(loginASCho);
		
		JLabel meterNo=new JLabel("Meter Number");
		meterNo.setBounds(30, 100, 125, 20);
		meterNo.setVisible(false);
		add(meterNo);
		
		meterText =new TextField();
		meterText.setBounds(170, 100, 125, 20);
		meterText.setVisible(false);
		add(meterText);
		
		JLabel employer=new JLabel("Employer ID");
		employer.setBounds(30, 100, 125, 20);
		employer.setVisible(true);
		add(employer);
		
		employerText =new TextField();
		employerText.setBounds(170, 100, 125, 20);
		employerText.setVisible(true);
		add(employerText);
		
		JLabel userName=new JLabel("UserName");
		userName.setBounds(30, 140, 125, 20);
		add(userName);
		
		usernameText=new TextField("");
		usernameText.setBounds(170, 140, 125, 20);
		add(usernameText);
		
        JLabel name=new JLabel("Name");
		name.setBounds(30, 180, 125, 20);
		add(name);
		
		nameText=new TextField();
		nameText.setBounds(170, 180, 125, 20);
         add(nameText);
		
         meterText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
             
				try {
					database c = new database();
					ResultSet resultset = c.statement.executeQuery("select * from Signup where meter_no = '"+meterText.getText()+"'");
					
					if(resultset.next()) {
						nameText.setText(resultset.getString("name"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
         
		JLabel password=new JLabel("Password");
		password.setBounds(30, 220, 125, 20);
		add(password);
	
		passwordText=new TextField();
		passwordText.setBounds(170, 220, 125, 20);
		add(passwordText);
		
		loginASCho.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String user = loginASCho.getSelectedItem();
				if(user.equals("Customer")) {
					employer.setVisible(false);
					nameText.setEditable(false);
					employerText.setVisible(false);
					meterNo.setVisible(true);
					meterText.setVisible(true);
				}
				else {
					employer.setVisible(true);
					employerText.setVisible(true);
					meterNo.setVisible(false);
					meterText.setVisible(false);
				}
			}
		});
		
		create=new JButton("Create");
		create.setBackground(new Color(66, 127, 219));
		create.setForeground(Color.black);
		create.setBounds(50, 285, 100, 25);
		create.addActionListener(this);
		add(create);
		
		
		back=new JButton("Back");
		back.setBackground(new Color(66, 127, 219));
		back.setForeground(Color.black);
		back.setBounds(180, 285, 100, 25);
		back.addActionListener(this);
		add(back);
		
		
		ImageIcon boyIcon =new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
		Image boyImg = boyIcon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
		ImageIcon boyIcon2 =new ImageIcon(boyImg);
		JLabel boylabel =new JLabel(boyIcon2);
		boylabel.setBounds(300, 50, 250, 250);

		add(boylabel);
		
		setSize(600, 400);
		setLocation(500,200);
		setLayout(null);
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == create) {
	        String sloginAs = loginASCho.getSelectedItem();
	        String susername = usernameText.getText();
	        String sname = nameText.getText();
	        String spassword = passwordText.getText();
	        String smeter = sloginAs.equals("Admin") ? employerText.getText() : meterText.getText();

	        if (susername.isEmpty() || sname.isEmpty() || spassword.isEmpty() || smeter.isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "Fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
	            return; 
	        }

	        
	        try {
	            database c = new database();
	            String query;

	            if (sloginAs.equals("Admin")) {
	                query = "insert into Signup (meter_no, username, name, password, usertype) " +
	                        "values ('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+sloginAs+"')";
	            } else {
	                query = "update Signup set username = '"+susername+"', name = '"+sname+"', password = '"+spassword+"', usertype = '"+sloginAs+"' " +
	                        "where meter_no = '"+smeter+"'";
	            }

	            int rows = c.statement.executeUpdate(query);

	            if (rows > 0) {
	                JOptionPane.showMessageDialog(null, "Account Created");
	                setVisible(false);
	                new Login();
	            } else {
	                JOptionPane.showMessageDialog(null, "Account not created!");
	            }

	        } catch (SQLException e1) {
	            e1.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
	        }

    	 
     }
     else if(e.getSource()==back) {
    	 setVisible(false);
    	 new Login();
    	 
     }
    
		
	}
	
	
public static void main(String[]args) {
	new Signup();
}


}
