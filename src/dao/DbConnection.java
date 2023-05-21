package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static String bd="banco";
	private static String parametros="?useSSL=false&serverTimezone=UTC";
	private static String user="root";
	private static String passwd="1234";
	private static String url="jdbc:mysql://localhost:3309/"+bd+parametros;
	private Connection conn;
	
	public DbConnection() {
		// TODO Auto-generated constructor stub
		try {
			System.out.println("Conectando a la BBDD");
			conn=DriverManager.getConnection(url,user,passwd);
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	public Connection getConnection() {
		return conn;
	}
	public void disconect() {
		if(conn!=null) {
			try {
				System.out.println("Cerrando conexion a la BBDD");
				conn.close();
				System.out.println("Conexion cerrada");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
	
}
