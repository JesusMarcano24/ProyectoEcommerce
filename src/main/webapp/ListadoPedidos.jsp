<%@page import="model.Pedido"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Pedidos</title>
<!-- Añadir Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
    /* Estilos para el botón flotante */
    .floating-button-container {
        position: relative;
        text-align: center;
    }
    .floating-button {
        position: fixed;
        bottom: 30px;
        right: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 60px;
        height: 60px;
        background-color: #4CAF50;
        color: white;
        border-radius: 50%;
        border: none;
        cursor: pointer;
        transition: transform 0.2s, right 0.3s;
        font-size: 24px;
        text-align: center;
        text-decoration: none;
    }
    .floating-button:hover {
        text-decoration: none;
        color: white;
    }
    .floating-button:hover .button-text {
        display: inline-block;
        opacity: 1;
        visibility: visible;
        width: auto;
    }
    .button-text {
        position: absolute;
        right: 10px;
        background-color: #4CAF50;
        color: white;
        padding: 5px 10px;
        border-radius: 5px;
        white-space: nowrap;
        opacity: 0;
        font-size: 16px;
        visibility: hidden;
        transition: opacity 0.3s, visibility 0.3s, right 0.3s;
    }
    .floating-button:hover .button-text {
        right: 70px;
    }
</style>
</head>
<body>
<h2>
    <a class="menu-button" href="Menu.jsp">Volver al Menú</a>
</h2>
<h1>Listado de Pedidos registrados en la BD</h1>

<table class="table table-striped">
    <thead>
        <tr>
            <th>ID</th>
            <th>Estado</th>
            <th>Nombre Usuario</th>
            <th>Pedido Manual</th>
            <th>Total</th>
            <th colspan="2" class="text-center">Acciones</th>
        </tr>
    </thead>
    <tbody>
        <% 
        List<Pedido> listadoPedidos = (List<Pedido>) request.getAttribute("listadoPedidos");
        if (listadoPedidos != null) {
            for (Pedido lis : listadoPedidos) {
        %>
        <tr>
            <td><%= lis.getId() %></td>
            <td><%= lis.getEstado() %></td>
            <td><%= lis.getNombreUsuario() %></td>
            <td><%= lis.getPedidoManual() %></td>
            <td><%= lis.getTotal() %></td>
            <td class="text-center">
                <a href="ControladorPedido?accion=Eliminar&cod=<%= lis.getId() %>" class="btn btn-danger">Eliminar</a>
            </td>
	        <td class="text-center">
                <a href="ControladorPedido?accion=Modificar&cod=<%= lis.getId() %>" class="btn btn-primary">Actualizar</a>
            </td>
        </tr>	
        <%	
            }
        }
        %>
    </tbody>
</table>

<!-- Botón flotante -->
<div class="floating-button-container">
    <a href="FormRegistrarPedido.jsp" class="floating-button">
        +
        <span class="button-text">Registrar Pedido</span>
    </a>
</div>

<!-- Añadir Bootstrap JS y dependencias -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>