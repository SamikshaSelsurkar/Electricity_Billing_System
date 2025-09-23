package com.project.electricity;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class customer_details extends JFrame implements ActionListener{

	Choice searchMeterCho,searchnameCho;
	JTable table;
	JButton search,print,close;
	public customer_details() {
		
		
	super("Custome Details");
	getContentPane().setBackground(new Color(192,186,254));
		
		
		
		
	setSize(700,500);	
	setLocation(400,200);
	setLayout(null);
		
	JLabel searchMeter= new JLabel("Search By Meter Number");
	searchMeter.setBounds(20,20,150,20);
	add(searchMeter);
	
	searchMeterCho = new Choice();
	searchMeterCho.setBounds(180,20,150,20);
	add(searchMeterCho);
	
	try {
		database c = new database();
		ResultSet resultset = c.statement.executeQuery("select * from new_customer");
		
		while (resultset.next()) {
//			searchMeterCho.add(resultset.getString("meterno"));
			String meter = resultset.getString("meterno");
		    if (meter != null) {   
		        searchMeterCho.add(meter);
		    }
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	JLabel searchname= new JLabel("Search By Name");
	searchname.setBounds(400,20,100,20);
	add(searchname);
	
	searchnameCho = new Choice();
	searchnameCho.setBounds(520,20,150,20);
	add(searchnameCho);
	
	try {
		database c = new database();
		ResultSet resultset = c.statement.executeQuery("select * from new_customer");
		
		while (resultset.next()) {
//			searchnameCho.add(resultset.getString("name"));
			
			String name = resultset.getString("name");
		    if (name != null) {  
		        searchnameCho.add(name);
		    }
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	table = new JTable();
	
	try {
		database c = new database();
		ResultSet resultset = c.statement.executeQuery("select * from new_customer");
		
		table.setModel(DbUtils.resultSetToTableModel(resultset));
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	JScrollPane scrollPane = new JScrollPane(table);
	scrollPane.setBounds(0,100,700,500);
	scrollPane.setBackground(Color.white);
	add(scrollPane);
	
	search = new JButton("Search");
	search.setBackground(Color.white);
	search.setBounds(20,70,80,20);
	search.addActionListener(this);
	add(search);
	
	print = new JButton("Print");
	print.setBackground(Color.white);
	print.setBounds(120,70,80,20);	
	print.addActionListener(this);
	add(print);
	
	close = new JButton("Close");
	close.setBackground(Color.white);
	close.setBounds(600,70,80,20);
	close.addActionListener(this);
	add(close);
	
	setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        
		if(e.getSource()==search) {
			String query_search = "select * from new_customer where meterno= '"+searchMeterCho.getSelectedItem()+"' and name= '"+searchnameCho.getSelectedItem()+"'";
			
			
			try {
				database c = new database();
				ResultSet resultset = c.statement.executeQuery(query_search);
				
				table.setModel(DbUtils.resultSetToTableModel(resultset));
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource()==print) {
			try {
				table.print();
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
		else {
			setVisible(false);
		}
	}
	public static void main(String[] args) {
       new customer_details();
	}

	

}
