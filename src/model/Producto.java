package model;

import java.sql.Date;

public class Producto {
	private int id;
	private String nombre;
	private double cantidad;
	private double precio;
	private Date fechaCrear;
	private Date actualizarFecha;
	
	
	public Producto() {
		
	}
	
	public Producto(int id, String nombre,double cantidad, double precio, Date fechaCrear, Date actualizarFecha) {
		this.id=id;
		this.nombre= nombre;
		this.cantidad=cantidad;
		this.precio= precio;
		this.fechaCrear=fechaCrear;
		this.actualizarFecha=actualizarFecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCrear() {
		return fechaCrear;
	}

	public void setFechaCrear(Date fechaCrear) {
		this.fechaCrear = fechaCrear;
	}

	public Date getActualizarFecha() {
		return actualizarFecha;
	}

	public void setActualizarFecha(Date actualizarFecha) {
		this.actualizarFecha = actualizarFecha;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
				+ ", fechaCrear=" + fechaCrear + ", actualizarFecha=" + actualizarFecha + "]";
	}
	

}
