package Controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Dao.ClassProductoImp;
import model.Producto;

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
		Producto producto = new Producto();
		ClassProductoImp crud = new ClassProductoImp();
		
		//recuperamos la accion y codigo
		String accion=request.getParameter("accion");
		//aplicamos una condicion...
		if(accion!=null){
			switch(accion){
			case "Modificar":
				int codigo=Integer.parseInt(request.getParameter("cod"));
				//asignar el codigo...
				producto.setId(codigo);
				Producto buscarcod=crud.BuscarProducto(producto);
				request.setAttribute("codigo",buscarcod.getId());
				request.setAttribute("nombre",buscarcod.getNombre());
				request.setAttribute("descripcion",buscarcod.getDescripcion());
				request.setAttribute("precio",buscarcod.getPrecio());
				request.setAttribute("fechaCreacion",buscarcod.getFechaCreacion());
				//redireccionamos..
				request.getRequestDispatcher("/FormActualizarProducto.jsp").forward(request, response);
				//salimos
				break;
			case "Eliminar":
				int codeliminar=Integer.parseInt(request.getParameter("cod"));
				//asignamos el codigo a eliminar
				producto.setId(codeliminar);
				//invocamos al metodo eliminar...
				crud.EliminarProducto(producto);
				//refrescar el listado..
				List<Producto> listado=crud.ListadoProducto();
				request.setAttribute("listadodeproductos",listado);
				//redireccionar
				request.getRequestDispatcher("/ListadoProductos.jsp");
				//salimos
				break;
			
			case "Listar":
				List<Producto> listadoProducto= crud.ListadoProducto();
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
	    String fechaCreacionStr = request.getParameter("fechaCreacion");
	    String precioStr = request.getParameter("precio");
	    String descripcion = request.getParameter("descripcion");
	    
	    BigDecimal precio = new BigDecimal(precioStr);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date fechaCreacion = null;
	    try {
	        fechaCreacion = dateFormat.parse(fechaCreacionStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    // Instanciamos las entidades
	    Producto producto = new Producto();
	    ClassProductoImp crud = new ClassProductoImp();

	    producto.setNombre(nombre);
	    producto.setDescripcion(descripcion);
	    producto.setPrecio(precio);
	    producto.setFechaCreacion(fechaCreacion);

	    List<Producto> listadoProducto;

	    if (codigo != null && !codigo.isEmpty()) {
	        // Recupero el c�digo a actualizar
	        int cod = Integer.parseInt(codigo);
	        // Asigno el c�digo a actualizar
	        producto.setId(cod);
	        // Invoco al m�todo actualizar
	        crud.ActualizarProducto(producto);
	    } else {
	        // Invocamos el m�todo registrar
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
