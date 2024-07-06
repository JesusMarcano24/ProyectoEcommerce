<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Pedido</title>
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
    input[type="text"], input[type="number"], input[type="date"] {
        width: 200px;
        padding: 10px;
        box-sizing: border-box;
        border-radius: 5px;
        border: 1px solid #ccc;
    }
    input[readonly] {
        background-color: #e8f5e9;
        border: none;
        pointer-events: none;
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
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var today = new Date().toISOString().split('T')[0];
        document.getElementById("fechaPedido").value = today;

        var pedidoManualInput = document.getElementsByName("pedidoManual")[0];
        var totalInput = document.getElementsByName("total")[0];

        pedidoManualInput.addEventListener("input", function() {
            var pedidoManual = parseFloat(pedidoManualInput.value) || 0;
            var total = pedidoManual + (pedidoManual * 0.18);
            totalInput.value = total.toFixed(2);
        });
    });
</script>
</head>
<body>
<%
    String usuarioLogueado = (String) session.getAttribute("usuarioLogueado");
%>
<h2>
    <a class="menu-button" href="Menu.jsp">Volver al Menú</a>
</h2>
<h1>Registrar Pedido</h1>
<div class="form-container">
    <form action="ControladorPedido" method="post">
        <table>
            <tr>
                <th colspan="2" class="center">Datos del Pedido</th>
            </tr>
            <tr>
                <td>Estado:</td>
                <td><input type="text" name="estado" required></td>
            </tr>
            <tr>
                <td>Fecha Pedido:</td>
                <td><input type="date" id="fechaPedido" name="fechaPedido" readonly></td>
            </tr>
            <tr>
                <td>Nombre Usuario:</td>
                <td><input type="text" name="nombreUsuario" value="<%= usuarioLogueado %>" readonly></td>
            </tr>
            <tr>
                <td>Pedido Manual:</td>
                <td><input type="number" name="pedidoManual" required></td>
            </tr>
            <tr>
                <td>Total con IGV:</td>
                <td><input type="number" step="0.01" name="total" readonly></td>
            </tr>
            <tr>
                <td colspan="2" class="center">
                    <input type="submit" value="Registrar Pedido" class="submit-button">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>