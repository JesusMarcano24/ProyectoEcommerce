<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="model.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Usuarios</title>
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
    .menu-button {
        display: inline-block;
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        margin: 20px auto;
        transition: transform 0.2s;
        display: block;
        width: 250px;
    }
    .menu-button:hover {
        background-color: #45a049;
        transform: scale(1.05);
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
<h1>Listado de Usuarios registrados en la BD</h1>

<h2>
    <a class="menu-button" href="Menu.jsp">Volver al Men�</a>
</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Rol</th>
            <th>Contrase�a</th>
        </tr>
    </thead>
    <tbody>
        <% 
        List<Usuario> listadoUsuarios = (List<Usuario>)request.getAttribute("listadoUsuarios");
        if (listadoUsuarios != null) {
            for (Usuario lis : listadoUsuarios) {
        %>
        <tr>
            <td><%=lis.getId()%></td>
            <td><%=lis.getNombre()%></td>
            <td><%=lis.getEmail()%></td>
            <td><%=lis.getRol()%></td>
            <td><%=lis.getPassword()%></td>
            
        </tr>	
        <%	
            }
        }
        %>
    </tbody>
</table>
</body>
</html>