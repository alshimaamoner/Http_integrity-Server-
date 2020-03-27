package service;
import java.io.*;
import java.sql.*;

public class DBConn {
    
	private static Connection conn;
	
    public static Connection getConnection() {
        if(conn == null) {
			try {
                Class.forName("com.mysql.jdbc.Driver"); 
                 String myDriver = "com.mysql.jdbc.Driver";
                 String myUrl = "jdbc:mysql://localhost:3306/employee";
                 Class.forName(myDriver);
                 conn = DriverManager.getConnection(myUrl, "root", "password");
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return conn;
    }

}