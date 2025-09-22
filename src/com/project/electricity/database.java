package com.project.electricity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
	
	Connection connection;
	Statement statement;
	
database(){
	try {
		Class.forName("oracle.jdbc.OracleDriver");

		connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","sys as sysdba","admin");
	    statement = connection.createStatement();
	
	
	
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}
