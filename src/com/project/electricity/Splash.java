package com.project.electricity;

import java.awt.Image;

import javax.swing.*;
import java.awt.*;
public class Splash extends JFrame {

	Splash(){
		
		ImageIcon imageicon=new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg"));
		Image imageone = imageicon.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
		ImageIcon imageIcon2 = new ImageIcon(imageone);
		JLabel imageLabel = new JLabel(imageIcon2);
		add(imageLabel);
		
	setSize(500, 500);	
	setLocation(500, 200);
	setVisible(true);
	
	
	try {
		Thread.sleep(3000);
		setVisible(false);
		
		new Login();
	}catch (Exception e) {
e.printStackTrace();


}
	
	}
	
 public static void main(String[]args) {
	 new Splash();
	 
 }
}
