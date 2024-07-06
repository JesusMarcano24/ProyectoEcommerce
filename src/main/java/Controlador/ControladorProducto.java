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
        Producto producto = new Producto();
        ClassProductoImp crud = new ClassProductoImp();
        
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "Modificar":
                    int codigo = Integer.parseInt(request.getParameter("cod"));
                    producto.setId(codigo);
                    Producto buscarcod = crud.BuscarProducto(producto);
                    request.setAttribute("codigo", buscarcod.getId());
                    request.setAttribute("nombre", buscarcod.getNombre());
                    request.setAttribute("descripcion", buscarcod.getDescripcion());
                    request.setAttribute("precio", buscarcod.getPrecio());
                    request.setAttribute("fechaCreacion", buscarcod.getFechaCreacion());
                    request.getRequestDispatcher("/FormActualizarProducto.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    int codeliminar = Integer.parseInt(request.getParameter("cod"));
                    producto.setId(codeliminar);
                    crud.EliminarProducto(producto);  // Línea 66
                    List<Producto> listado = crud.ListadoProducto();
                    request.setAttribute("listadodeproductos", listado);
                    request.getRequestDispatcher("/ListadoProductos.jsp").forward(request, response);
                    break;
                case "Listar":
                    List<Producto> listadoProducto = crud.ListadoProducto();
                    request.setAttribute("listadoProductos", listadoProducto);
                    request.getRequestDispatcher("/ListadoProductos.jsp").forward(request, response);
                    break;
            }
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Producto producto = new Producto();
        ClassProductoImp crud = new ClassProductoImp();

        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setFechaCreacion(fechaCreacion);

        List<Producto> listadoProducto;

        if (codigo != null && !codigo.isEmpty()) {
            int cod = Integer.parseInt(codigo);
            producto.setId(cod);
            crud.ActualizarProducto(producto);
        } else {
            crud.RegistrarProducto(producto);
        }

        listadoProducto = crud.ListadoProducto();

        request.setAttribute("listadoProductos", listadoProducto);
        request.getRequestDispatcher("/ListadoProductos.jsp").forward(request, response);
    }

}
