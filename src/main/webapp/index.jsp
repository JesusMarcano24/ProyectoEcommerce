<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
    String loginError = (String) request.getAttribute("loginError");
    if(loginError == null) loginError = "";
%>
<meta charset="UTF-8">
<title>Ingresar al sistema</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        flex-direction: column;
    }
    .login-container {
        background-color: #e8f5e9;
        border: 2px solid #4CAF50;
        border-radius: 5px;
        padding: 30px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 400px;
    }
    h1 {
        color: #4CAF50;
        text-align: center;
        margin-bottom: 20px;
        font-size: 18px;
    }
    .btn-primary {
        background-color: #4CAF50;
        border: none;
        padding: 10px 20px;
        border-radius: 3px;
        width: fit-content;
    }
    .btn-primary:hover {
        background-color: #45a049;
    }
    .alert-warning {
        background-color: #fff3cd;
        border-color: #ffeeba;
        color: #856404;
    }
    .text-center {
        text-align: center;
    }
    .welcome-text {
        color: #4CAF50;
        font-size: 24px;
        margin-bottom: 20px;
    }
</style>
</head>
<body>
    <div class="text-center welcome-text">Bienvenidos a TECHWARE</div>
    <div class="login-container">
        <h1 class="fw-bold text-center">Ingresar al sistema</h1>
        <form action="ControladorUsuario" method="post">
            <div class="mb-3">
                <label class="form-label">Usuario</label>
                <input type="text" class="form-control" name="nombre" autocomplete="off" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Clave</label>
                <input type="password" class="form-control" name="password" required>
            </div>
            <div class="text-center">
                <button class="btn btn-primary" type="submit">
                    <i class="bi bi-door-open"></i> Iniciar Sesión
                </button>
            </div>
            <%
                if(!loginError.equals("")) {
            %>
            <div class="alert alert-warning text-center">
                <%=loginError %>
            </div>
            <%
                }
            %>
        </form>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>