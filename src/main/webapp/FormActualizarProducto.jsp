<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.TblProductocl3" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actualizar Producto</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    h1 {
        text-align: center;
        color: #4CAF50;
    }
    .form-container {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
    }
    table {
        border-collapse: collapse;
        width: 50%;
        background-color: #e8f5e9;
        border: 2px solid #4CAF50;
        border-radius: 5px;
        margin: 0 auto;
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #4CAF50;
        color: white;
        border-radius: 5px 5px 0 0;
    }
    td {
        border-bottom: 1px solid #ddd;
    }
    input[type="text"], textarea {
        width: 100%;
        padding: 10px;
        box-sizing: border-box;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    .center {
        text-align: center;
    }
    .submit-button {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 5px;
        transition: transform 0.2s;
    }
    .submit-button:hover {
        background-color: #45a049;
        transform: scale(1.02);
    }
</style>
</head>
<body>
<h1>Actualizar Producto</h1>
<div class="form-container">
    <form method="post" action="ControladorProducto">
        <table>
            <input type="hidden" name="codigo" value="<%=request.getAttribute("codigo")%>">
            <tr>
                <th colspan="2" class="center">Datos del Producto</th>
            </tr>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" value="<%=request.getAttribute("nombre")%>"></td>
            </tr>
            <tr>
                <td>Descripción:</td>
                <td><textarea name="descripcion" rows="4" cols="50"><%=request.getAttribute("descripcion")%></textarea></td>
            </tr>
            <tr>
                <td>Estado:</td>
                <td><input type="text" name="estado" value="<%=request.getAttribute("estado")%>"></td>
            </tr>
            <tr>
                <td>Precio de Venta:</td>
                <td><input type="text" name="precioVenta" value="<%=request.getAttribute("precioVenta")%>"></td>
            </tr>
            <tr>
                <td>Precio de Compra:</td>
                <td><input type="text" name="precioCompra" value="<%=request.getAttribute("precioCompra")%>"></td>
            </tr>
            <tr>
                <td colspan="2" class="center"><input type="submit" value="Actualizar Producto" class="submit-button"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>