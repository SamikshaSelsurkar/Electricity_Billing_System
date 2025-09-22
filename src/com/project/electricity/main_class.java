package com.project.electricity;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class main_class extends JFrame implements ActionListener{

	String acctype;
	String meter_pass;
    public main_class(String acctype,String meter_pass) {
    	
    	this.acctype=acctype;
        this.meter_pass=meter_pass;
        
        // JFrame fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ✅ Screen size घे
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        // ✅ Load आणि scale image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon2 = new ImageIcon(image);

        // ✅ JLabel background
        JLabel imagelabel = new JLabel(imageIcon2);
        imagelabel.setBounds(0, 0, width, height);

        setContentPane(imagelabel);
        imagelabel.setLayout(null);   
        
        JMenuBar menuBar= new JMenuBar();
        setJMenuBar(menuBar);
    
    JMenu menu=new JMenu("Menu");
    menu.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem newcustomer=new JMenuItem("New Customer");
    newcustomer.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon customerImg=new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
    Image customerImage=customerImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    newcustomer.setIcon(new ImageIcon(customerImage));
    newcustomer.addActionListener(this);
    menu.add(newcustomer);
    
    JMenuItem customerdetails=new JMenuItem("Customer Details");
    customerdetails.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon customerdetailsImg=new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
    Image customerdetailsImage=customerdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    customerdetails.setIcon(new ImageIcon(customerdetailsImage));
    customerdetails.addActionListener(this);
    menu.add(customerdetails);
    
    JMenuItem depositedetails=new JMenuItem("Deposit Details");
    depositedetails.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon depositedetailsImg=new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
    Image depositedetailsImage=depositedetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    depositedetails.setIcon(new ImageIcon(depositedetailsImage));
    depositedetails.addActionListener(this);
    menu.add(depositedetails);
    
    JMenuItem calculatorbill=new JMenuItem("Calculator Bills");
    calculatorbill.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon calculatorbillImg=new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
    Image calculatorbillImage=calculatorbillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    calculatorbill.setIcon(new ImageIcon(calculatorbillImage));
    calculatorbill.addActionListener(this);
    menu.add(calculatorbill);
    
    
    JMenu info=new JMenu("Information");
    info.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem upinfo=new JMenuItem("Update Information");
    upinfo.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon upinfoImg=new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
    Image upinfoImage=upinfoImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    upinfo.setIcon(new ImageIcon(upinfoImage));
    upinfo.addActionListener(this);
    info.add(upinfo);
    
    JMenuItem viewinfo=new JMenuItem("View Information");
    viewinfo.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon viewinfoImg=new ImageIcon(ClassLoader.getSystemResource("icon/information.png"));
    Image viewinfoImage=viewinfoImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    viewinfo.setIcon(new ImageIcon(viewinfoImage));
    viewinfo.addActionListener(this);
    info.add(viewinfo);
    
    
    JMenu user=new JMenu("User");
    user.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem paybill=new JMenuItem("Pay Bill");
    paybill.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon paybillImg=new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
    Image paybillImage=paybillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    paybill.setIcon(new ImageIcon(paybillImage));
    paybill.addActionListener(this);
    user.add(paybill);
    
    JMenuItem billdetails=new JMenuItem("Bill Details");
    billdetails.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon billdetailsImg=new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
    Image billdetailsImage=billdetailsImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    billdetails.setIcon(new ImageIcon(billdetailsImage));
    billdetails.addActionListener(this);
    user.add(billdetails);
    
    
    JMenu bill=new JMenu("Bill");
    bill.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem genBill=new JMenuItem("Generate Bill");
    genBill.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon genBillImg=new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
    Image genBillImage=genBillImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    genBill.setIcon(new ImageIcon(genBillImage));
    genBill.addActionListener(this);
    bill.add(genBill);
    
    
    JMenu utility=new JMenu("Utility");
    utility.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem notepad=new JMenuItem("NotePad");
    notepad.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon notepadImg=new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
    Image notepadImage=notepadImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    notepad.setIcon(new ImageIcon(notepadImage));
    notepad.addActionListener(this);
    utility.add(notepad);
    
    JMenuItem calculator=new JMenuItem("Calculator");
    calculator.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon calculatorImg=new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
    Image calculatorImage=calculatorImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    calculator.setIcon(new ImageIcon(calculatorImage));
    calculator.addActionListener(this);
    utility.add(calculator);
    
    
    JMenu exit=new JMenu("Exit");
    exit.setFont(new Font("serif",Font.PLAIN,15));
    
    JMenuItem eexit=new JMenuItem("Exit");
    eexit.setFont(new Font("monospaced",Font.PLAIN,14));
    ImageIcon eexitImg=new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
    Image eexitImage=eexitImg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    eexit.setIcon(new ImageIcon(eexitImage));
    eexit.addActionListener(this);
    exit.add(eexit);
    
    if(acctype.equals("Admin")) {
        menuBar.add(menu);
    }
    else {
    	menuBar.add(bill);
        menuBar.add(user);
        menuBar.add(info);
    }
    menuBar.add(utility);
    menuBar.add(exit);
    

         setLayout(new FlowLayout());
        setVisible(true);
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
             
    	String msg = e.getActionCommand();
    	if(msg.equals("New Customer")) {
    		new newCustomer();
    	}
    	else if(msg.equals("Customer Details")) {
    		new customer_details();
    	}
    	else if(msg.equals("Deposit Details")) {
    		new deposit_details();
    	}
    	else if(msg.equals("Update Information")) {
    		new update_information(meter_pass);
    	}
    	else if(msg.equals("View Information")) {
    		new view_information(meter_pass);
    	}
    	else if(msg.equals("Bill Details")) {
    		new bill_details(meter_pass);
    	}
    	else if(msg.equals("Calculator Bills")) {   
            new calculate_bill();
        }
    	else if(msg.equals("Calculator")) {
    	    try {
    	        new ProcessBuilder("cmd", "/c", "start", "calculator:").start();
    	    } catch (IOException e1) {
    	        e1.printStackTrace();
    	    }
    	}
    	else if(msg.equals("NotePad")) {
   		
    		try {
				Runtime.getRuntime().exec("notepad.exe");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
   	}
    else if(msg.equals("Exit")) {
    	setVisible(false);
		new Login();
    	}
    	else if(msg.equals("Pay Bill")) {
    		new pay_bill(meter_pass);
    	}
    	
    	else if(msg.equals("Generate Bill")) {
    		new generate_bill(meter_pass);
    	}
    	
//    	else if(msg.equals("Calculator")) {
//    		new customer_details();
    	
    }
    public static void main(String[] args) {
        new main_class("","");
    }

	
}
