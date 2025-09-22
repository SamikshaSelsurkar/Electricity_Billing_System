package com.project.electricity;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class generate_bill extends JFrame implements ActionListener{
   
	String meter;
	Choice  searchMonthCho;
	JTextArea area;JButton bill;
	 public generate_bill(String meter) {
   this.meter=meter;
   
   
   setSize(500,700);
   setLocation(500,30);
   setLayout(new BorderLayout());
   JPanel panel = new JPanel();
   
   JLabel heading  = new JLabel("Generate Bill");
   
   
   
   JLabel meter_no  = new JLabel(meter);
   

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
	
	area = new JTextArea(50,15);
	area.setText("\n \n \t -----------------------------Click on the -----------------\n \t ---------------------------Generate Bill");
    area.setFont(new Font("Senserif",Font.ITALIC,15));
    JScrollPane pane = new JScrollPane(area);
    
    
    bill = new JButton("Generate Bill");
    bill.addActionListener(this);
    add(pane);
    
    panel.add(heading);
    panel.add(meter_no);
    panel.add(searchMonthCho);
    add(panel, "North");
    add(bill,"South");
    setVisible(true);
    
	 }
	 @Override
		public void actionPerformed(ActionEvent e) {

		
		 try {
			 database c = new database();
			 String smonth = searchMonthCho.getSelectedItem();
			 area.setText("\n Power Limited \n Electricity Bill For Month of "+smonth+",2025\n\n\n");
			ResultSet resultset = c.statement.executeQuery("select * from new_customer where meterno = '"+meter+"'");
			
			
			if (resultset.next()){
                area.append("\n    Customer Name        : "+resultset.getString("name"));
                area.append("\n    Customer Meter Number: "+resultset.getString("meterno"));
                area.append("\n    Customer Address     : "+resultset.getString("address"));
                area.append("\n    Customer City        : "+resultset.getString("city"));
                area.append("\n    Customer State       : "+resultset.getString("state"));
                area.append("\n    Customer Email       : "+resultset.getString("email"));
                area.append("\n    Customer Phone Number       : "+resultset.getString("phone"));

            }

         resultset = c.statement.executeQuery("select * from meter_info where meter_number ='"+meter+"'");
        if (resultset.next()){
            area.append("\n    Customer Meter Location        : "+resultset.getString("meter_location"));
            area.append("\n    Customer Meter Type: "+resultset.getString("meter_type"));
            area.append("\n    Customer Phase Code   : "+resultset.getString("phase_code"));
            area.append("\n    Customer Bill Type        : "+resultset.getString("bill_type"));
            area.append("\n    Customer Days      : "+resultset.getString("day"));


        }
            resultset = c.statement.executeQuery("select * from tax");
            if (resultset.next()){
                area.append("\n    Cost Per Unit        : "+resultset.getString("cost_per_unit"));
                area.append("\n   Meter Rent: "+resultset.getString("meter_rent"));
                area.append("\n   Service Charge   : "+resultset.getString("service_charge"));
                area.append("\n   Service Tax        : "+resultset.getString("service_tax"));
                area.append("\n   Swacch Bharat      : "+resultset.getString("swacch_bharat"));
                area.append("\n   Fixed Tax     : "+resultset.getString("fixed_tax"));

            }
            resultset = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchMonthCho.getSelectedItem()+"'");
            if (resultset.next()) {
                area.append("\n    Current Month       : " + resultset.getString("month"));
                area.append("\n   Units Consumed: " + resultset.getString("unit"));
                area.append("\n   Total Charges   : " + resultset.getString("total_bill"));
                area.append("\n Total Payable: "+resultset.getString("total_bill"));
            }
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}

	public static void main(String[] args) {

		new generate_bill("");
	}
	
}
