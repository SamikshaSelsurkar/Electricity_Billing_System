package com.project.electricity;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class calculate_bill extends JFrame implements ActionListener{
	
     JLabel nameText,addressText;
     TextField unitText;
	Choice meternumCho,monthCho;
	JButton submit,cancel;
	
	public calculate_bill() {

		
	JPanel panel = new JPanel();
	panel.setLayout(null);
	panel.setBackground(Color.GRAY);
	add(panel);
	
	
	JLabel heading = new JLabel("Calculate Electricity Bill");
	heading.setBounds(70,10,300,20);
	heading.setFont(new Font("Tahoma",Font.BOLD,20));
	panel.add(heading);
		
		JLabel meternum = new JLabel("Meter Number");
		meternum.setBounds(50,80,100,20);
		panel.add(meternum);
		
		meternumCho = new Choice();
		
		try {
			database c = new database();
			ResultSet resultset = c.statement.executeQuery("select * from new_customer");
			
			while(resultset.next()) {
				meternumCho.add(resultset.getString("meterno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		meternumCho.setBounds(180,80,100,20);
		panel.add(meternumCho);
		
		
		
		JLabel name = new JLabel("Name");
		name.setBounds(50,120,100,20);
		panel.add(name);
		
		nameText = new JLabel("");
		nameText.setBounds(180,120,150,20);
		panel.add(nameText);
		
		JLabel address = new JLabel("Address");
		address.setBounds(50,160,100,20);
		panel.add(address);
		
		addressText = new JLabel("");
		addressText.setBounds(180,160,150,20);
		panel.add(addressText);
		
		try {
			database c= new database();
			ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno='"+meternumCho.getSelectedItem()+"'");
		    while(resultset.next()) {
		    	nameText.setText(resultset.getString("name"));
		    	addressText.setText(resultset.getString("address"));
		    }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		meternumCho.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					database c= new database();
					ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno='"+meternumCho.getSelectedItem()+"'");
				    while(resultset.next()) {
				    	nameText.setText(resultset.getString("name"));
				    	addressText.setText(resultset.getString("address"));	
				    }
				
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel unitconsumed = new JLabel("Unit Consumed");
		unitconsumed.setBounds(50, 200, 100, 20);
		panel.add(unitconsumed);
		
		unitText = new TextField();
		unitText.setBounds(180,200,150,20);
		panel.add(unitText);
		
		JLabel month = new JLabel("Month");
		month.setBounds(50, 240, 100, 20);
		panel.add(month);
		
		monthCho = new Choice();
		monthCho.add("January");
		monthCho.add("February");
		monthCho.add("March");
		monthCho.add("April");
		monthCho.add("May");
		monthCho.add("June");
		monthCho.add("July");
		monthCho.add("August");
		monthCho.add("September");
		monthCho.add("October");
		monthCho.add("November");
		monthCho.add("December");
		
		monthCho.setBounds(180,240,150,20);
        panel.add(monthCho);

        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);
        
        setLayout(new BorderLayout());
        add(panel,"Center");
        
        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
		Image i2 = i1.getImage().getScaledInstance(250, 200,Image.SCALE_DEFAULT);
		ImageIcon i3 =new ImageIcon(i2);
		JLabel imglabel =new JLabel(i3);
		add(imglabel,"East");
        
	        setSize(650,400);
	        setLocation(400,200);
	        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==submit) {
    		
    		String smeterNo = meternumCho.getSelectedItem();
    		String sunit = unitText.getText();
    		String smonth = monthCho.getSelectedItem();
    		
    		
    		
    		int totalBill = 0;
    		int units = Integer.parseInt(sunit);
    		String query_tax = "select * from tax";
    		
    		try {
        		database c = new database();
				ResultSet resultset= c.statement.executeQuery(query_tax);
				
				while(resultset.next()) {
					totalBill += units * Integer.parseInt(resultset.getString("cost_per_unit"));
					totalBill += Integer.parseInt(resultset.getString("meter_rent"));
					totalBill += Integer.parseInt(resultset.getString("service_charge"));
					totalBill += Integer.parseInt(resultset.getString("swacch_bharat"));
					totalBill += Integer.parseInt(resultset.getString("fixed_tax"));
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		
    		String query_total_bill = "insert into bill values('"+smeterNo+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";
    		
    		try {
        		database c = new database();
				c.statement.executeUpdate(query_total_bill);
				
				JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully..");
				setVisible(false);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		
    		
    	}else {
    		setVisible(false);
    	}
	}
	public static void main(String[] args) {

new calculate_bill();
	}


}
