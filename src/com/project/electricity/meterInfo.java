package com.project.electricity;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class meterInfo extends JFrame implements ActionListener{

	JLabel heading,meterNumber,meterNumberText,meterloc,meterType,phaseCode,billType,day,note,note1;
	Choice meterlocCho,meterTypeCho,phaseCodeCho,billTypeCho;
	JButton submit;
	String meternumber;
	
	public meterInfo(String meternumber) {
		this.meternumber=meternumber;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.PINK);
		add(panel);
		
		    heading = new JLabel("Meter Information");
			heading.setBounds(180,10,200,20);
			heading.setFont(new Font("Tahoma",Font.BOLD,20));
			panel.add(heading);
			
             meterNumber = new JLabel("Meter Number");
             meterNumber.setBounds(50, 80, 100, 20);
             panel.add(meterNumber);
             
             meterNumberText = new JLabel(meternumber);
             meterNumberText.setBounds(180, 80, 150, 20); 
     		panel.add(meterNumberText);
             
     		 meterloc = new JLabel("Meter Number");
     		 meterloc.setBounds(50, 120, 100, 20);
             panel.add(meterloc);
             
             meterlocCho =new Choice();
             meterlocCho.add("OutSide");
             meterlocCho.add("InSide");
             meterlocCho.setBounds(180, 120, 150, 20);
             panel.add(meterlocCho);
             
             meterType = new JLabel("Meter Type");
             meterType.setBounds(50, 160, 100, 20);
             panel.add(meterType);
             
             meterTypeCho =new Choice();
             meterTypeCho.add("Electric Meter");
             meterTypeCho.add("Solar Meter");
             meterTypeCho.add("Smart Meter");
             meterTypeCho.setBounds(180, 160, 150, 20);
             panel.add(meterTypeCho);
             
             phaseCode = new JLabel("Phase Code");
             phaseCode.setBounds(50, 200, 100, 20);
             panel.add(phaseCode);
             
             phaseCodeCho =new Choice();
             phaseCodeCho.add("011");
             phaseCodeCho.add("022");
             phaseCodeCho.add("033");
             phaseCodeCho.add("044");
             phaseCodeCho.add("055");
             phaseCodeCho.add("066"); 
             phaseCodeCho.add("077");
             phaseCodeCho.add("088");
             phaseCodeCho.add("099");
             phaseCodeCho.setBounds(180, 200, 150, 20);
             panel.add(phaseCodeCho);
             
             
             billType = new JLabel("Bill Type");
             billType.setBounds(50, 240, 100, 20);
             panel.add(billType);
             
             billTypeCho =new Choice();
             billTypeCho.add("Normal");
             billTypeCho.add("Industrial");
             billTypeCho.setBounds(180, 240, 150, 20);
             panel.add(billTypeCho);
             
             day = new JLabel("30 Days Billing Time.....");
             day.setBounds(50, 280, 150, 20);
             panel.add(day);
             
             note = new JLabel("Note:-");
             note.setBounds(50, 320, 100, 20);
             panel.add(note);
             
             note1 = new JLabel("By Default Bill is Calculated for 30  Days only");
             note1.setBounds(50, 360, 300, 20);
             panel.add(note1);
             
             submit = new JButton("Submit");
             submit.setBounds(240, 400, 100, 25);
             submit.setBackground(Color.black);
             submit.setForeground(Color.white);
             submit.addActionListener(this);
             panel.add(submit);
             
             
             setLayout(new BorderLayout());
     		add(panel,"Center");
     		
     		ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
    		Image i2 = i1.getImage().getScaledInstance(230, 200,Image.SCALE_DEFAULT);
    		ImageIcon i3 =new ImageIcon(i2);
    		JLabel imglabel =new JLabel(i3);
    		add(imglabel,"East");
    		
		      setSize(700,500);
		      setLocation(400,200);
		      setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {
			
			String smeterNum=meternumber;
			String smeterLoc=meterlocCho.getSelectedItem();
			String smeterType=meterTypeCho.getSelectedItem();
			String sphaseCode=phaseCodeCho.getSelectedItem();
			String sbillType=billTypeCho.getSelectedItem();
			String sday="30";
			
			String query_meterInfo = "insert into meter_info values('"+smeterNum+"','"+smeterLoc+"','"+smeterType+"','"+sphaseCode+"','"+sbillType+"','"+sday+"')";
			
			
			try {
				database c = new database();
				c.statement.executeUpdate(query_meterInfo);
			
				JOptionPane.showMessageDialog(null, "Meter Information Submited Successufully..");
				setVisible(false);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
			else {
				setVisible(false);
			}
		}
		
	
	
	public static void main(String[] args) {
     new meterInfo("");
	}




	

}
