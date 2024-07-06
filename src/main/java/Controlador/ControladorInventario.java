package Controlador;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Inventario inventario = new Inventario();
        ClassInventarioImp crud = new ClassInventarioImp();

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "Modificar":
                    int codigo = Integer.parseInt(request.getParameter("cod"));
                    inventario.setId(codigo);
                    Inventario buscarcod = crud.BuscarInventario(inventario);
                    request.setAttribute("codigo", buscarcod.getId());
                    request.setAttribute("nombreProducto", buscarcod.getNombreProducto());
                    request.setAttribute("ultimaActualizacion", new Date());
                    request.setAttribute("cantidad", buscarcod.getCantidad());
                    request.getRequestDispatcher("/FormActualizarInventario.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    int codeliminar = Integer.parseInt(request.getParameter("cod"));
                    inventario.setId(codeliminar);
                    crud.EliminarInventario(inventario);
                    List<Inventario> listado = crud.ListadoInventario();
                    request.setAttribute("listadodeInventarios", listado);
                    request.getRequestDispatcher("/ListadoInventario.jsp").forward(request, response);
                    break;
                case "Listar":
                    List<Inventario> listadoInventario = crud.ListadoInventario();
                    request.setAttribute("listadoInventarios", listadoInventario);
                    request.getRequestDispatcher("/ListadoInventario.jsp").forward(request, response);
                    break;
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreProd = request.getParameter("nombreProducto");
        String codigo = request.getParameter("codigo");
        String cantidadStr = request.getParameter("cantidad");

        Integer cantidad = Integer.parseInt(cantidadStr);

        Inventario inventario = new Inventario();
        ClassInventarioImp crud = new ClassInventarioImp();

        inventario.setNombreProducto(nombreProd);
        inventario.setCantidad(cantidad);
        inventario.setUltimaActualizacion(new Date());

        List<Inventario> listadoInventario;

        if (codigo != null && !codigo.isEmpty()) {
            int cod = Integer.parseInt(codigo);
            inventario.setId(cod);
            crud.ActualizarInventario(inventario);
        } else {
            crud.RegistrarInventario(inventario);
        }

        listadoInventario = crud.ListadoInventario();

        request.setAttribute("listadoInventarios", listadoInventario);
        request.getRequestDispatcher("/ListadoInventario.jsp").forward(request, response);
    }
}