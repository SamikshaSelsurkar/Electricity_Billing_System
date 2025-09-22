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

public class view_information extends JFrame implements ActionListener{

	JButton cancel;
	
	String view;
	public view_information(String view) {
		this.view=view;
		
		setBounds(350,150,750,550);
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		JLabel heading = new JLabel("View Customer Information");
		heading.setBounds(250,0,500,40);
		heading.setFont(new Font("serif",Font.BOLD,20));
		add(heading);
		
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(70,80,100,20);
		add(nameLabel);
		
		JLabel nameLabelText = new JLabel("");
		nameLabelText.setBounds(200,80,150,20);
		add(nameLabelText);
		
		JLabel meterno = new JLabel("Meter Number");
		meterno.setBounds(70,140,100,20);
		add(meterno);
		
		JLabel meternoText = new JLabel("");
		meternoText.setBounds(200,140,150,20);
		add(meternoText);
		
		JLabel address = new JLabel("Address");
		address.setBounds(70,200,100,20);
		add(address);
		
		JLabel addressText = new JLabel("");
		addressText.setBounds(200,200,150,20);
		add(addressText);
		
		JLabel city = new JLabel("City");
		city.setBounds(70,260,100,20);
		add(city);
		
		JLabel cityText = new JLabel("");
		cityText.setBounds(200,260,150,20);
		add(cityText);
		
		JLabel state = new JLabel("State");
		state.setBounds(500,80,100,20);
		add(state);
		
		JLabel stateText = new JLabel("");
		stateText.setBounds(600,80,150,20);
		add(stateText);
		
		JLabel email = new JLabel("Email");
		email.setBounds(500,140,100,20);
		add(email);
		
		JLabel emailText = new JLabel("");
		emailText.setBounds(600,140,150,20);
		add(emailText);
		
		JLabel phone = new JLabel("Phone");
		phone.setBounds(500,200,100,20);
		add(phone);
		
		JLabel phoneText = new JLabel("");
		phoneText.setBounds(600,200,150,20);
		add(phoneText);
		
		
		try {
			database c = new database();
			ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno  = '"+view+"'");
			if(resultset.next()) {
				nameLabelText.setText(resultset.getString("name"));
				meternoText.setText(resultset.getString("meterno"));
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
		
		cancel = new JButton("Cancel");
		cancel.setBackground(new Color (24,118,242));
		cancel.setForeground(Color.white);
		cancel.setBounds(220,300,120,25);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
		Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
		ImageIcon i3 =new ImageIcon(i2);
		JLabel imglabel =new JLabel(i3);
		imglabel.setBounds(100,210,600,300);
		add(imglabel);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cancel) {
			setVisible(false);
		}
		
	}
	public static void main(String[] args) {
          
		new view_information("");
	}

	

}
