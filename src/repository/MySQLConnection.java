package repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class MySQLConnection {
	
	private Connection connection;
	static String url ="jdbc:mysql://localhost:3306/sanniovillage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "root";
	static String password = "Dimenticata1";
	
	public MySQLConnection() {
		connection = null;
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(url, username, password);


		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Errore di connessione al server MySQL");
			throw new Error("Error ", ex);		
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static Connection getConnection(){  
	    try {  
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      return DriverManager.getConnection(url,username,password);  
	    } catch(Exception e){}  

	    return null;  
	  }  

}