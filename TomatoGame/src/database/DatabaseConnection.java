package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class DatabaseConnection {

	public static Connection createConnection() throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
		
		return con;
	}

}
