package com.project.electricity;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.foreign.AddressLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class bill_details extends JFrame implements ActionListener{

	String meter;
	public bill_details(String meter) {

		this.meter=meter;
		
		setSize(700,550);
		setLocation(400,150);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		
		JTable table = new JTable();
		
		
		
		try {
			database c = new database();
			String query_bill = "select * from bill where meter_no = '"+meter+"'";
			ResultSet resultset = c.statement.executeQuery(query_bill);
			table.setModel(DbUtils.resultSetToTableModel(resultset));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0,0,700,650);
		add(sp);
		setVisible(true);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new bill_details("");

	}


	

}
