package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import model.Producto;

public class ProductoDAO {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estado;
	
	//obtener el pool
		private Connection obtenerConexion() throws SQLException {
			return Conexion.getConnection();
		}
	
	
	public boolean guardar(Producto producto) throws SQLException {
		String sql= null;
		estado=false;
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			sql="INSERT INTO productos (id,nombre,cantidad,precio,fecha_crear,fecha_actualizar) "
					+ "VALUES(?,?,?,?,?,?)";
			
		     statement = connection.prepareStatement(sql);
		     statement.setInt(1, producto.getId());
		     statement.setString(2, producto.getNombre());
		     statement.setDouble(3, producto.getCantidad());
		     statement.setDouble(4, producto.getPrecio());
		     statement.setDate(5, producto.getFechaCrear());
		     statement.setDate(6, producto.getActualizarFecha());
		     
		     statement.executeUpdate();	
		     estado = true;
		     
		     connection.commit();
		     statement.close();
			connection.close();
			
		}catch(SQLException e) {
			connection.rollback();
			e.printStackTrace();
			System.out.println(e);
			
		}
		
		
		return estado;
	}
	
	//Lista de todos los productos 
	
	public List<Producto>listaProductos() throws SQLException{
		ResultSet rs = null;
		List<Producto> productoLista = new ArrayList<>();
		
		String sql = null;
		estado = false;
		connection = obtenerConexion();
		
		
		try {
			sql="SELECT * FROM productos";
			statement=connection.prepareStatement(sql);
			rs= statement.executeQuery(sql);
			
			while(rs.next()) {
				Producto p = new Producto();
			p.setId(rs.getInt(1));
			p.setNombre(rs.getString(2));
			p.setCantidad(rs.getDouble(3));
			p.setPrecio(rs.getDouble(4));
			p.setFechaCrear(rs.getDate(5));
			p.setActualizarFecha(rs.getDate(6));
			
			productoLista.add(p);
				
			}
			
			statement.close();
			rs.close();
			
			
		}catch(SQLException e) {
			
		}
		
		
		return productoLista;
	}
	
	
	public Producto getProducto(int idProducto) throws SQLException {
		ResultSet rs= null;
		Producto p = new Producto();
		
		String sql=null;
		estado = false;
		connection = obtenerConexion();
		
		try {
			sql = "SELECT * FROM productos WHERE id =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, idProducto);
			
			rs = statement.executeQuery();
			
			if(rs.next()) {				
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setCantidad(rs.getDouble(3));
				p.setPrecio(rs.getDouble(4));
				p.setFechaCrear(rs.getDate(5));
				p.setActualizarFecha(rs.getDate(6));
			}
			statement.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}
	
	
	// editar producto
		public boolean editar(Producto producto) throws SQLException {
			String sql = null;
			estado = false;
			connection = obtenerConexion();
			try {
				connection.setAutoCommit(false);
				sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
				statement = connection.prepareStatement(sql);

				statement.setString(1, producto.getNombre());
				statement.setDouble(2, producto.getCantidad());
				statement.setDouble(3, producto.getPrecio());
				statement.setDate(4, producto.getActualizarFecha());
				statement.setInt(5, producto.getId());

				statement.executeUpdate();
				
				estado=true;
				connection.commit();
				statement.close();
				connection.close();

			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}

			return estado;
		}

		// eliminar producto
		public boolean eliminar(int idProducto) throws SQLException {
			String sql = null;
			estado = false;
			connection = obtenerConexion();
			try {
				connection.setAutoCommit(false);
				sql = "DELETE FROM productos WHERE id=?";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, idProducto);

				statement.executeUpdate();
				estado = true;
				connection.commit();
				statement.close();
				connection.close();

			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			}

			return estado;
		}
		

}
