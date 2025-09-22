package com.project.electricity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class update_information extends JFrame implements ActionListener{

	JLabel nameText;
	JTextField addressText,cityText,stateText,emailText,phoneText;
	
	String meter;
	JButton update,cancel;
	
	public update_information(String meter) {

		this.meter=meter;
		
		setBounds(400,150,777,450);
		getContentPane().setBackground(new Color(229, 255, 227));
		setLayout(null);
		
		JLabel heading = new JLabel("Update Customer Information");
		heading.setBounds(50,10,400,40);
		heading.setFont(new Font("serif",Font.BOLD,20));
		add(heading);
		
		JLabel name = new JLabel("Name");
		name.setBounds(30,70,100,20);
		add(name);
		
		nameText = new JLabel("");
		nameText.setBounds(150,70,200,20);
		add(nameText);
		
		JLabel meterNo = new JLabel("Meter Number");
		meterNo.setBounds(30,110,100,20);
		add(meterNo);
		
		JLabel meterText = new JLabel("");
		meterText.setBounds(150,110,100,20);
		add(meterText);
		
		JLabel address = new JLabel("Address");
		address.setBounds(30,150,100,20);
		add(address);
		
		addressText = new JTextField();
		addressText.setBounds(150,150,200,20);
		add(addressText);
		
		JLabel city = new JLabel("City");
		city.setBounds(30,190,100,20);
		add(city);
		
		cityText = new JTextField();
		cityText.setBounds(150,190,200,20);
		add(cityText);
		
		JLabel state = new JLabel("State");
		state.setBounds(30,230,100,20);
		add(state);
		
		stateText = new JTextField();
		stateText.setBounds(150,230,200,20);
		add(stateText);
		
		JLabel email = new JLabel("Email");
		email.setBounds(30,270,100,20);
		add(email);
		
		emailText = new JTextField();
		emailText.setBounds(150,270,200,20);
		add(emailText);
		
		JLabel phone = new JLabel("Phone");
		phone.setBounds(30,310,100,20);
		add(phone);
		
		phoneText = new JTextField();
		phoneText.setBounds(150,310,200,20);
		add(phoneText);
		
		
		try {
			database c = new database();
			ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno = '" + meter + "'");
			if(resultset.next()) {
				nameText.setText(resultset.getString("name"));
				meterText.setText(resultset.getString("meterno"));
				addressText.setText(resultset.getString("address"));
				cityText.setText(resultset.getString("city"));
				stateText.setText(resultset.getString("state"));
				emailText.setText(resultset.getString("email"));
				phoneText.setText(resultset.getString("phone"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		update = new JButton("Update");
		update.setBackground(new Color(33, 106, 145));
		update.setForeground(Color.white);
		update.setBounds(50,360,120,25);
		update.addActionListener(this);
		add(update);
		
		cancel = new JButton("Cancel");
		cancel.setBackground(new Color(33, 106, 145));
		cancel.setForeground(Color.white);
		cancel.setBounds(200,360,120,25);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
		ImageIcon i3 =new ImageIcon(i2);
		JLabel imglabel =new JLabel(i3);
		imglabel.setBounds(360,0,400,410);
		add(imglabel);
		
	setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==update) {
			
			String saddress = addressText.getText();
			String scity = cityText.getText();
			String sstate = stateText.getText();
			String semail = emailText.getText();
			String sphone = phoneText.getText();

			
			try {
				database c = new database();
				c.statement.executeUpdate(
					    "UPDATE new_customer " +
					    "SET address = '" + saddress + 
					    "', city = '" + scity + 
					    "', state = '" + sstate + 
					    "', email = '" + semail + 
					    "', phone = '" + sphone + 
					    "' WHERE meterno = '" + meter + "'"
					);
			
			JOptionPane.showMessageDialog(null, "User Information Updated Successfully..");
			setVisible(false);
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			setVisible(false);
		}
	}

	
	public static void main(String[] args) {

		new update_information("");
	}


	
}
