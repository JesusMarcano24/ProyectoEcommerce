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

import Dao.ClassInventarioImp;
import model.Inventario;

/**
 * Servlet implementation class ControladorInventario
 */
public class ControladorInventario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorInventario() {
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
		Inventario inventario = new Inventario();
		ClassInventarioImp crud = new ClassInventarioImp();
		
		//recuperamos la accion y codigo
		String accion=request.getParameter("accion");
		//aplicamos una condicion...
		if(accion!=null){
			switch(accion){
			case "Modificar":
				int codigo=Integer.parseInt(request.getParameter("cod"));
				//asignar el codigo...
				inventario.setId(codigo);
				Inventario buscarcod=crud.BuscarInventario(inventario);
				request.setAttribute("codigo",buscarcod.getId());
				request.setAttribute("nombreProducto",buscarcod.getNombreProducto());
				request.setAttribute("ultimaActualizacion",buscarcod.getUltimaActualizacion());
				request.setAttribute("cantidad",buscarcod.getCantidad());
				//redireccionamos..
				request.getRequestDispatcher("/FormActualizarInventario.jsp").forward(request, response);
				//salimos
				break;
			case "Eliminar":
				int codeliminar=Integer.parseInt(request.getParameter("cod"));
				//asignamos el codigo a eliminar
				inventario.setId(codeliminar);
				//invocamos al metodo eliminar...
				crud.EliminarInventario(inventario);
				//refrescar el listado..
				List<Inventario> listado=crud.ListadoInventario();
				request.setAttribute("listadodeInventarios",listado);
				//redireccionar
				request.getRequestDispatcher("/ListadoInventarios.jsp");
				//salimos
				break;
			
			case "Listar":
				List<Inventario> listadoInventario = crud.ListadoInventario();
				//Hacemos el listado de inventarios
				request.setAttribute("listadoInventarios", listadoInventario);
				//Redireccion
				request.getRequestDispatcher("/ListadoInventarios.jsp").forward(request, response);
				break;
				
			 }  //fin del switch...
			
			
		}   //fin 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Recuperamos valores
	    String nombreProd = request.getParameter("nombreProducto");
	    String codigo = request.getParameter("codigo");
	    String ultimaActStr = request.getParameter("ultimaActualizacion");
	    String cantidadStr = request.getParameter("cantidad");
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date ultimaAct = null;
	    Integer cantidad = Integer.parseInt(cantidadStr);
	    try {
	    	ultimaAct = dateFormat.parse(ultimaActStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    // Instanciamos las entidades
	    Inventario inventario = new Inventario();
	    ClassInventarioImp crud = new ClassInventarioImp();

	    inventario.setNombreProducto(nombreProd);
	    inventario.setCantidad(cantidad);
	    inventario.setUltimaActualizacion(ultimaAct);

	    List<Inventario> listadoInventario;

	    if (codigo != null && !codigo.isEmpty()) {
	        // Recupero el código a actualizar
	        int cod = Integer.parseInt(codigo);
	        // Asigno el código a actualizar
	        inventario.setId(cod);
	        // Invoco al método actualizar
	        crud.ActualizarInventario(inventario);
	    } else {
	        // Invocamos el método registrar
	        crud.RegistrarInventario(inventario);
	    }

	    // Actualizamos el listado
	    listadoInventario = crud.ListadoInventario();

	    // Hacemos el listado de inventarios
	    request.setAttribute("listadoInventarios", listadoInventario);
	    // Redireccionamos al usuario al listado
	    request.getRequestDispatcher("/ListadoInventarios.jsp").forward(request, response);
	}

}
