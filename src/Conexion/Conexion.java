package Conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
	private static org.apache.commons.dbcp2.BasicDataSource dataSource = null;
	
	private static DataSource getDataSource() {
	
	if(dataSource==null) {
		dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/crud"); //Crear la bd
		dataSource.setInitialSize(20);//Conexiones iniciadas
		dataSource.setMaxIdle(15); //Conexiones activas
		dataSource.setMaxTotal(20); //total de Conexiones
		dataSource.setMaxWaitMillis(5000);
		
		
	}
	 
	return dataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		
		return getDataSource().getConnection();
	}
	
	
}
