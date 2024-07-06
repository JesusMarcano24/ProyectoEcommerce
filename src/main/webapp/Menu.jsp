<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TECHWARE Menu</title>
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f9f9f9;
        margin: 0;
    }
    h1 {
        color: #4CAF50;
        margin-bottom: 30px;
    }
    .menu-container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    .menu-button {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        margin: 10px;
        transition: transform 0.2s;
        width: 250px;
    }
    .menu-button:hover {
        background-color: #45a049;
        transform: scale(1.05);
    }
    .user-info {
        position: absolute;
        top: 20px;
        right: 20px;
        font-size: 14px;
        color: #333;
    }
</style>
</head>
<body>
<%
    String usuarioLogueado = (String) session.getAttribute("usuarioLogueado");
    String rolLogueado = (String) session.getAttribute("rolLogueado");
%>
<div class="user-info">
    <strong>Usuario:</strong> <%= usuarioLogueado %><br>
    <strong>Rol:</strong> <%= rolLogueado %>
</div>
<h1>TECHWARE Menu</h1>
<div class="menu-container">
    <a class="menu-button" href="ControladorProducto?accion=Listar">Mostrar listado de Productos</a>
    <a class="menu-button" href="ControladorInventario?accion=Listar">Mostrar listado de Inventarios</a>
    <a class="menu-button" href="ControladorPedido?accion=Listar">Mostrar listado de Pedidos</a>
    <a class="menu-button" href="ControladorUsuario">Mostrar listado de Usuarios</a>
    <% if ("admin".equals(rolLogueado)) { %>
        <a class="menu-button" href="ControladorReporte">Mostrar Reportes</a>
    <% } %>
</div>
</body>
</html> 