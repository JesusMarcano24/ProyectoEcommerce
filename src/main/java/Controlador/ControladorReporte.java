package Controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.ClassProductoImp;
import Dao.ClassInventarioImp;
import Dao.UsuarioDaoImpl;
import Dao.ClassPedidoImp;
import model.Producto;
import model.Inventario;
import model.Usuario;
import model.Pedido;

public class ControladorReporte extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Instanciar los DAOs
        ClassProductoImp productoDao = new ClassProductoImp();
        ClassInventarioImp inventarioDao = new ClassInventarioImp();
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        ClassPedidoImp pedidoDao = new ClassPedidoImp();

        // Recuperar los datos
        List<Producto> productos = productoDao.ListadoProducto();
        List<Inventario> inventarios = inventarioDao.ListadoInventario();
        List<Usuario> usuarios = usuarioDao.ListadoUsuario();
        List<Pedido> pedidos = pedidoDao.ListadoPedido();

        // Guardar los datos en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("productos", productos);
        session.setAttribute("inventarios", inventarios);
        session.setAttribute("usuarios", usuarios);
        session.setAttribute("pedidos", pedidos);

        // Redireccionar a la página de reportes
        response.sendRedirect("reporte.jsp");
    }
}