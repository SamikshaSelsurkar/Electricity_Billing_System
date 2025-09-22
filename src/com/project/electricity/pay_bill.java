package com.project.electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class pay_bill extends JFrame implements ActionListener{
Choice searchMonthCho;
JButton Pay,Back;
	String meter;
	public pay_bill(String meter) {
		this.meter=meter;

	setSize(800,500);
	setLocation(300,150);
	setLayout(null);
	
	JLabel heading = new JLabel("Pay Bill");
	heading.setFont(new Font("Tohoma",Font.BOLD,24));
	heading.setBounds(120,5,400,30);
	add(heading);
	
	JLabel meterNumber = new JLabel("Meter Number");
	meterNumber.setBounds(35,80,200,20);
	add(meterNumber);
	
	JLabel meterNumberText = new JLabel("");
	meterNumberText.setBounds(300,80,200,20);
	add(meterNumberText);
	
	JLabel name = new JLabel("Name");
	name.setBounds(35,140,200,20);
	add(name);
	
	JLabel nameText = new JLabel("");
	nameText.setBounds(300,140,200,20);
	add(nameText);
	
	JLabel month = new JLabel("Month");
	month.setBounds(35,200,200,20);
	add(month);
	
	searchMonthCho = new Choice();
	searchMonthCho.add("January");
	searchMonthCho.add("February");
	searchMonthCho.add("March");
	searchMonthCho.add("April");
	searchMonthCho.add("May");
	searchMonthCho.add("June");
	searchMonthCho.add("July");
	searchMonthCho.add("August");
	searchMonthCho.add("September");
	searchMonthCho.add("October");
	searchMonthCho.add("November");
	searchMonthCho.add("December");
	
	searchMonthCho.setBounds(300,200,150,20);
	add(searchMonthCho);
	
	JLabel unit = new JLabel("Unit");
	unit.setBounds(35,260,200,20);
	add(unit);
	
	JLabel unitText = new JLabel("");
	unitText.setBounds(300,260,200,20);
	add(unitText);
	

	JLabel totalBill = new JLabel("TotalBill");
	totalBill.setBounds(35,320,200,20);
	add(totalBill);
	
	JLabel totalBillText = new JLabel("");
	totalBillText.setBounds(300,320,200,20);
	add(totalBillText);
	
	JLabel status = new JLabel("Status");
	status.setBounds(35,380,200,20);
	add(status);
	
	JLabel statusText = new JLabel("");
	statusText.setBounds(300,380,200,20);
	statusText.setForeground(Color.red);
	add(statusText);
	
	
	try {
		database c = new database();
		ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno = '"+meter+"'");
		while(resultset.next()) {
			meterNumberText.setText(meter);
			nameText.setText(resultset.getString("name"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	searchMonthCho.addItemListener(new ItemListener() {
		
		@Override
		public void itemStateChanged(ItemEvent e) {

			try {
				database c= new database();
				ResultSet resultset = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchMonthCho.getSelectedItem()+"'");
				while(resultset.next()) {
					unitText.setText(resultset.getString("unit"));
					totalBillText.setText(resultset.getString("total_bill"));
					statusText.setText(resultset.getString("status"));

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	Pay = new JButton("Pay");
	Pay.setBackground(Color.black);
	Pay.setForeground(Color.white);
	Pay.setBounds(100,400,100,25);
	Pay.addActionListener(this);
	add(Pay);
	
	Back = new JButton("Back");
	Back.setBackground(Color.black);
	Back.setForeground(Color.white);
	Back.setBounds(230,400,100,25);
	Back.addActionListener(this);
	add(Back);
	
	
	setVisible(true);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==Pay) {
			
			try {
				database c = new database();
				c.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+searchMonthCho.getSelectedItem()+"'");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			setVisible(false);
			new payment_bill(meter);
		}
		else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
         new pay_bill("");
	}

	
}
