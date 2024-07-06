<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Producto" %>
<%@ page import="model.Inventario" %>
<%@ page import="model.Usuario" %>
<%@ page import="model.Pedido" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 20px;
    }
    h1, h2 {
        color: #4CAF50;
        text-align: center;
    }
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        background-color: #ffffff;
    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #4CAF50;
        color: white;
    }
    tr:hover {
        background-color: #f1f1f1;
    }
</style>
</head>
<body>
<h2>
    <a class="menu-button" href="Menu.jsp">Volver al Menú</a>
</h2>
<h1>Reportes</h1>

<% 
    // Obtener listas de la sesión
    List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
    List<Producto> productos = (List<Producto>) session.getAttribute("productos");
    List<Inventario> inventarios = (List<Inventario>) session.getAttribute("inventarios");
    List<Pedido> pedidos = (List<Pedido>) session.getAttribute("pedidos");

    // Variables para datos de usuarios
    int numUsuarios = 0;
    int numAdmins = 0;
    int numUsers = 0;

    // Calcular datos de usuarios
    if (usuarios != null) {
        numUsuarios = usuarios.size();
        for (Usuario usuario : usuarios) {
            if ("admin".equalsIgnoreCase(usuario.getRol())) {
                numAdmins++;
            } else if ("user".equalsIgnoreCase(usuario.getRol())) {
                numUsers++;
            }
        }
    }

    // Variables para datos de productos
    int numProductos = 0;
    Producto productoMasCaro = null;
    Producto productoMasBarato = null;
    Producto productoMasAntiguo = null;

    // Calcular datos de productos
    if (productos != null) {
        numProductos = productos.size();
        for (Producto producto : productos) {
            if (productoMasCaro == null || producto.getPrecio().compareTo(productoMasCaro.getPrecio()) > 0) {
                productoMasCaro = producto;
            }
            if (productoMasBarato == null || producto.getPrecio().compareTo(productoMasBarato.getPrecio()) < 0) {
                productoMasBarato = producto;
            }
            if (productoMasAntiguo == null || 
                (producto.getFechaCreacion() != null && 
                (productoMasAntiguo.getFechaCreacion() == null || producto.getFechaCreacion().compareTo(productoMasAntiguo.getFechaCreacion()) < 0))) {
                productoMasAntiguo = producto;
            }
        }
    }

    // Variables para datos de inventarios
    int numInventarios = 0;
    long cantidadTotalProductos = 0;
    Inventario inventarioMasCantidad = null;
    Inventario inventarioMenosCantidad = null;
    Inventario inventarioRecienteActualizacion = null;

    // Calcular datos de inventarios
    if (inventarios != null) {
        numInventarios = inventarios.size();
        for (Inventario inventario : inventarios) {
            cantidadTotalProductos += inventario.getCantidad();
            if (inventarioMasCantidad == null || inventario.getCantidad() > inventarioMasCantidad.getCantidad()) {
                inventarioMasCantidad = inventario;
            }
            if (inventarioMenosCantidad == null || inventario.getCantidad() < inventarioMenosCantidad.getCantidad()) {
                inventarioMenosCantidad = inventario;
            }
            if (inventarioRecienteActualizacion == null || 
                (inventario.getUltimaActualizacion() != null && 
                (inventarioRecienteActualizacion.getUltimaActualizacion() == null || inventario.getUltimaActualizacion().compareTo(inventarioRecienteActualizacion.getUltimaActualizacion()) > 0))) {
                inventarioRecienteActualizacion = inventario;
            }
        }
    }

    // Variables para datos de pedidos
    int numPedidos = 0;
    BigDecimal totalPedidos = BigDecimal.ZERO;
    Pedido pedidoMasReciente = null;

    // Calcular datos de pedidos
    if (pedidos != null) {
        numPedidos = pedidos.size();
        for (Pedido pedido : pedidos) {
            totalPedidos = totalPedidos.add(pedido.getTotal());
            if (pedidoMasReciente == null || 
                (pedido.getFechaPedido() != null && 
                (pedidoMasReciente.getFechaPedido() == null || pedido.getFechaPedido().compareTo(pedidoMasReciente.getFechaPedido()) > 0))) {
                pedidoMasReciente = pedido;
            }
        }
    }
%>

<h2>Usuarios</h2>
<table>
    <tr><td>Número de Usuarios:</td><td><%= numUsuarios %></td></tr>
    <tr><td>Usuarios con Rol Admin:</td><td><%= numAdmins %></td></tr>
    <tr><td>Usuarios con Rol User:</td><td><%= numUsers %></td></tr>
</table>

<h2>Productos</h2>
<table>
    <tr><td>Cantidad de Productos:</td><td><%= numProductos %></td></tr>
    <tr><td>Producto más Caro:</td><td><%= productoMasCaro != null ? productoMasCaro.getNombre() + " - " + productoMasCaro.getPrecio() : "No disponible" %></td></tr>
    <tr><td>Producto más Barato:</td><td><%= productoMasBarato != null ? productoMasBarato.getNombre() + " - " + productoMasBarato.getPrecio() : "No disponible" %></td></tr>
    <tr><td>Producto más Antiguo:</td><td><%= productoMasAntiguo != null ? productoMasAntiguo.getNombre() + " - " + productoMasAntiguo.getFechaCreacion() : "No disponible" %></td></tr>
</table>

<h2>Inventarios</h2>
<table>
    <tr><td>Número de Inventarios:</td><td><%= numInventarios %></td></tr>
    <tr><td>Cantidad Total de Productos:</td><td><%= cantidadTotalProductos %></td></tr>
    <tr><td>Inventario con Más Cantidad:</td><td><%= inventarioMasCantidad != null ? inventarioMasCantidad.getNombreProducto() + " - " + inventarioMasCantidad.getCantidad() : "No disponible" %></td></tr>
    <tr><td>Inventario con Menos Cantidad:</td><td><%= inventarioMenosCantidad != null ? inventarioMenosCantidad.getNombreProducto() + " - " + inventarioMenosCantidad.getCantidad() : "No disponible" %></td></tr>
    <tr><td>Inventario Actualizado Más Recientemente:</td><td><%= inventarioRecienteActualizacion != null ? inventarioRecienteActualizacion.getNombreProducto() + " - " + inventarioRecienteActualizacion.getUltimaActualizacion() : "No disponible" %></td></tr>
</table>

<h2>Pedidos</h2>
<table>
    <tr><td>Número de Pedidos:</td><td><%= numPedidos %></td></tr>
    <tr><td>Total de Pedidos:</td><td><%= totalPedidos %></td></tr>
    <tr><td>Pedido Más Reciente:</td><td><%= pedidoMasReciente != null ? pedidoMasReciente.getNombreUsuario() + " - " + pedidoMasReciente.getFechaPedido() : "No disponible" %></td></tr>
</table>

</body>
</html>