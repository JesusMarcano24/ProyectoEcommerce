package Controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClassProductoImp;
import modelo.TblProductocl3;

/**
 * Servlet implementation class ControladorProducto
 */
public class ControladorProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		//Instanciamos
		TblProductocl3 producto = new TblProductocl3();
		ClassProductoImp crud = new ClassProductoImp();
		
		//recuperamos la accion y codigo
		String accion=request.getParameter("accion");
		//aplicamos una condicion...
		if(accion!=null){
			switch(accion){
			case "Modificar":
				int codigo=Integer.parseInt(request.getParameter("cod"));
				//asignar el codigo...
				producto.setIdproductoscl3(codigo);
				TblProductocl3 buscarcod=crud.BuscarProducto(producto);
				request.setAttribute("codigo",buscarcod.getIdproductoscl3());
				request.setAttribute("nombre",buscarcod.getNombrecl3());
				request.setAttribute("descripcion",buscarcod.getDescripcl3());
				request.setAttribute("estado",buscarcod.getEstadocl3());
				request.setAttribute("precioCompra",buscarcod.getPreciocompcl3());
				request.setAttribute("precioVenta",buscarcod.getPrecioventacl3());
				//redireccionamos..
				request.getRequestDispatcher("/FormActualizarProducto.jsp").forward(request, response);
				//salimos
				break;
			case "Eliminar":
				int codeliminar=Integer.parseInt(request.getParameter("cod"));
				//asignamos el codigo a eliminar
				producto.setIdproductoscl3(codeliminar);
				//invocamos al metodo eliminar...
				crud.EliminarProducto(producto);
				//refrescar el listado..
				List<TblProductocl3> listado=crud.ListadoProducto();
				request.setAttribute("listadodeproductos",listado);
				//redireccionar
				request.getRequestDispatcher("/ListadoProductos.jsp");
				//salimos
				break;
			
			case "Listar":
				List<TblProductocl3> listadoProducto= crud.ListadoProducto();
				//Hacemos el listado de productos
				request.setAttribute("listadoProductos", listadoProducto);
				//Redireccion
				request.getRequestDispatcher("/ListadoProductos.jsp").forward(request, response);
				break;
				
			 }  //fin del switch...
			
			
		}   //fin 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Recuperamos valores
	    String nombre = request.getParameter("nombre");
	    String codigo = request.getParameter("codigo");
	    double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
	    double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));
	    String estado = request.getParameter("estado");
	    String descripcion = request.getParameter("descripcion");

	    // Instanciamos las entidades
	    TblProductocl3 producto = new TblProductocl3();
	    ClassProductoImp crud = new ClassProductoImp();

	    producto.setNombrecl3(nombre);
	    producto.setDescripcl3(descripcion);
	    producto.setEstadocl3(estado);
	    producto.setPreciocompcl3(precioCompra);
	    producto.setPrecioventacl3(precioVenta);

	    List<TblProductocl3> listadoProducto;

	    if (codigo != null && !codigo.isEmpty()) {
	        // Recupero el código a actualizar
	        int cod = Integer.parseInt(codigo);
	        // Asigno el código a actualizar
	        producto.setIdproductoscl3(cod);
	        // Invoco al método actualizar
	        crud.ActualizarProducto(producto);
	    } else {
	        // Invocamos el método registrar
	        crud.RegistrarProducto(producto);
	    }

	    // Actualizamos el listado
	    listadoProducto = crud.ListadoProducto();

	    // Hacemos el listado de productos
	    request.setAttribute("listadoProductos", listadoProducto);
	    // Redireccionamos al usuario al listado
	    request.getRequestDispatcher("/ListadoProductos.jsp").forward(request, response);
	}

}
