package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductoDAO;
import model.Producto;


@WebServlet(description ="administra peticiones para la tabla prodecutos",urlPatterns = {"/productos"} )
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ProductoController() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		
		if(opcion.equals("crear")) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/view/registrarProducto.jsp");
		   
			rd.forward(request, response);
		}else if(opcion.equals("listar")){
			ProductoDAO dao = new ProductoDAO();
			List<Producto> lista= new ArrayList<>();
			
			try {
				lista = dao.listaProductos();
				for(Producto producto:lista) {
					System.out.println(producto);
					
				}
				request.setAttribute("lista", lista);
				RequestDispatcher rd = request.getRequestDispatcher("/view/listaProductos.jsp");
				
				rd.forward(request, response);
				
			}catch(SQLException e) {
				
			}
			
			
		}
		else if(opcion.equals("eliminar")) {
			ProductoDAO dao = new ProductoDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			
			try {
				dao.eliminar(id);
				System.out.println("Se elimino el registro" + id);
				RequestDispatcher rd= request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			}catch(SQLException e) {
				
				
			}
			
		}
		else if(opcion.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: "+id);
			
			ProductoDAO dao = new ProductoDAO();
			Producto p = new Producto();
			
			try {
			p= dao.getProducto(id);
			System.out.println();
			request.setAttribute("producto", p);
			RequestDispatcher rd = request.getRequestDispatcher("/view/editar.jsp");
			rd.forward(request, response);
				
			}catch(SQLException e) {
				
			}
			
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		Date fechaActual = new Date();
		
		if (opcion.equals("guardar")) {
			ProductoDAO productoDAO = new ProductoDAO();
			Producto producto = new Producto();
			producto.setNombre(request.getParameter("nombre"));
			producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
			producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
			producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
			try {
				productoDAO.guardar(producto);
				System.out.println("Registro guardado satisfactoriamente...");
				System.out.println(producto);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}else if(opcion.equals("editar")) {
		Producto producto = new Producto();
		ProductoDAO dao = new ProductoDAO();
		
		producto.setId(Integer.parseInt(request.getParameter("id")));
		producto.setNombre(request.getParameter("nombre"));
		producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
		producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
		producto.setActualizarFecha(new java.sql.Date(fechaActual.getTime()));
		
		try {
			dao.editar(producto);
			
			System.out.println("Registro editado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
	}

   }
	}
