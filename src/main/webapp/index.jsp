<%@page import="java.util.*"%>
<%@page import="modelo.TblUsuariocl3"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
            margin: 0;
        }
        .login-container {
            background-color: #e8f5e9;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .login-container h1 {
            color: #4CAF50;
            text-align: center;
        }
        .login-container input[type="text"], .login-container input[type="password"] {
            width: 95%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .submit-button {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 95%;
            text-align: center;
            text-decoration: none;
            transition: transform 0.2s;
        }
        .submit-button:hover {
            background-color: #45a049;
            transform: scale(1.01);
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>
        <form method="post">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="password" placeholder="Contraseña" required>
            <a class="submit-button" href="Menu.jsp">Login</a>
        </form>
    </div>
</body>
</html>