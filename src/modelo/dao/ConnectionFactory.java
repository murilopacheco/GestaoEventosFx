package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	 private Connection con = null;

	    public Connection getConnection() {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventos", "root", "root");
	            return con;
	        } catch (Exception e) {
	            System.out.println("Erro na conex�o: " + e.getMessage());
	        }
	        return con;
	    }

	    public void setClose() {
	        try {
	            con.close();
	        } catch (Exception e) {
	            System.out.println("Erro na conex�o: " + e.getMessage());
	        }
	    }
	}
