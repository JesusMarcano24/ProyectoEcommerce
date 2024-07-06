package Controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.ClassPedidoImp;
import model.Pedido;

public class ControladorPedido extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ControladorPedido() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pedido pedido = new Pedido();
        ClassPedidoImp crud = new ClassPedidoImp();

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "Modificar":
                    int codigo = Integer.parseInt(request.getParameter("cod"));
                    pedido.setId(codigo);
                    Pedido buscarcod = crud.BuscarPedido(pedido);
                    request.setAttribute("codigo", buscarcod.getId());
                    request.setAttribute("estado", buscarcod.getEstado());
                    request.setAttribute("fechaPedido", buscarcod.getFechaPedido());
                    request.setAttribute("nombreUsuario", buscarcod.getNombreUsuario());
                    request.setAttribute("pedidoManual", buscarcod.getPedidoManual());
                    request.setAttribute("total", buscarcod.getTotal());
                    request.getRequestDispatcher("/FormActualizarPedido.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    int codeliminar = Integer.parseInt(request.getParameter("cod"));
                    pedido.setId(codeliminar);
                    crud.EliminarPedido(pedido);
                    List<Pedido> listado = crud.ListadoPedido();
                    request.setAttribute("listadoPedidos", listado);
                    request.getRequestDispatcher("/ListadoPedidos.jsp").forward(request, response);
                    break;
                case "Listar":
                    List<Pedido> listadoPedido = crud.ListadoPedido();
                    request.setAttribute("listadoPedidos", listadoPedido);
                    request.getRequestDispatcher("/ListadoPedidos.jsp").forward(request, response);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigoStr = request.getParameter("codigo");
        String estado = request.getParameter("estado");
        String fechaPedidoStr = request.getParameter("fechaPedido");
        String nombreUsuario = request.getParameter("nombreUsuario");
        String pedidoManualStr = request.getParameter("pedidoManual");
        String totalStr = request.getParameter("total");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaPedido = null;
        try {
            fechaPedido = dateFormat.parse(fechaPedidoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int pedidoManual = Integer.parseInt(pedidoManualStr);
        BigDecimal total = new BigDecimal(totalStr);

        Pedido pedido = new Pedido();
        ClassPedidoImp crud = new ClassPedidoImp();

        pedido.setEstado(estado);
        pedido.setFechaPedido(fechaPedido);
        pedido.setNombreUsuario(nombreUsuario);
        pedido.setPedidoManual(pedidoManual);
        pedido.setTotal(total);

        List<Pedido> listadoPedido;

        if (codigoStr != null && !codigoStr.isEmpty()) {
            int codigo = Integer.parseInt(codigoStr);
            pedido.setId(codigo);
            crud.ActualizarPedido(pedido);
        } else {
            crud.RegistrarPedido(pedido);
        }

        listadoPedido = crud.ListadoPedido();

        request.setAttribute("listadoPedidos", listadoPedido);
        request.getRequestDispatcher("/ListadoPedidos.jsp").forward(request, response);
    }
}