package com.project.electricity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class newCustomer extends JFrame implements ActionListener{

	JLabel heading,customerName,meterNum,meterNumText,address,city,state,email,phone;
	TextField nameText,addressText,cityText,stateText,emailText,phoneText;
	JButton next,cancel;
	
	public newCustomer() {
		super("New Customer");
		setSize(700, 500);
		setLocation(400, 200);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.pink);
		add(panel);
		
		 heading = new JLabel("New Customer");
		heading.setBounds(180,10,200,20);
		heading.setFont(new Font("Tahoma",Font.BOLD,20));
		panel.add(heading);
		
		customerName = new JLabel("New Customer");
		customerName.setBounds(50,80,100,20);
		panel.add(customerName);

		nameText = new TextField();
		nameText.setBounds(180, 80, 180, 20); 
		panel.add(nameText);

		meterNum = new JLabel("Meter Number");
		meterNum.setBounds(50,120,100,20);
		panel.add(meterNum);

		meterNumText = new JLabel("");
		meterNumText.setBounds(180, 120, 180, 20); 
		panel.add(meterNumText);

		Random ran = new Random();
		long number = ran.nextLong()% 1000000;
		meterNumText.setText(""+ Math.abs(number));
		
		
		address = new JLabel("Address");
		address.setBounds(50,160,100,20);
		panel.add(address);

		addressText = new TextField();
		addressText.setBounds(180, 160, 180, 20); 
		panel.add(addressText);
		
		city = new JLabel("City");
		city.setBounds(50,200,100,20);
		panel.add(city);

		cityText = new TextField();
		cityText.setBounds(180, 200, 180, 20); 
		panel.add(cityText);
		
		state = new JLabel("State");
		state.setBounds(50,240,100,20);
		panel.add(state);

		stateText = new TextField();
		stateText.setBounds(180, 240, 180, 20); 
		panel.add(stateText);
		
		email = new JLabel("Email");
		email.setBounds(50,280,100,20);
		panel.add(email);

		emailText = new TextField();
		emailText.setBounds(180, 280, 180, 20); 
		panel.add(emailText);
		
		phone = new JLabel("Phone");
		phone.setBounds(50,320,100,20);
		panel.add(phone);

		phoneText = new TextField();
		phoneText.setBounds(180, 320, 180, 20); 
		panel.add(phoneText);
		
		next=new JButton("Next");
		next.setBounds(120, 390, 100, 25);
		next.setBackground(Color.black);
		next.setForeground(Color.white);
		next.addActionListener(this);
		panel.add(next);
		

		cancel=new JButton("Cancel");
		cancel.setBounds(230, 390, 100, 25);
		cancel.setBackground(Color.black);
		cancel.setForeground(Color.white);
		cancel.addActionListener(this);
		panel.add(cancel);
		
		
		setLayout(new BorderLayout());
		add(panel,"Center");
		
		ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/boy (2).png"));
		Image i2 = i1.getImage().getScaledInstance(230, 200,Image.SCALE_DEFAULT);
		ImageIcon i3 =new ImageIcon(i2);
		JLabel imglabel =new JLabel(i3);
		add(imglabel,"West");

		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==next) {
			String sname=nameText.getText();
			String smeter=meterNumText.getText();
			String saddress=addressText.getText();
			String scity=cityText.getText();
			String sstate=stateText.getText();
			String eemail=emailText.getText();
			String sphone=phoneText.getText();

			 if (sname.equals("") || smeter.equals("") || saddress.equals("") || 
			            scity.equals("") || sstate.equals("") || eemail.equals("") || sphone.equals("")) {
			            
			            JOptionPane.showMessageDialog(
			                this,
			                "All fields are required!",
			                "Warning",
			                JOptionPane.WARNING_MESSAGE
			            );
			            return;
			        }
			
			String query_customer = "insert into new_customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+eemail+"','"+sphone+"')";
			String query_signup = "insert into Signup values('"+smeter+"','','"+sname+"','','')";
			
			try {
				database  c = new database();
				c.statement.executeUpdate(query_customer);			
				c.statement.executeUpdate(query_signup);
				
			JOptionPane.showMessageDialog(null, "Customer details added Successfully..");
			setVisible(false);
			new meterInfo(smeter);
			
			} catch (SQLException e1) {
				e1.printStackTrace();
		}
	}
		else {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new newCustomer();
	}



	

}
