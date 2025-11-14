<%-- 
    Document   : login
    Created on : 14 nov 2025, 1:36:49 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/form_login_register.css">

</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
        <form action="">
            <h2 class="form-title">Inicio de sesión</h2>
            <label for="mail">Correo electrónico</label>
            <input id="mail" name="mail" type="email">
            <label for="pswd">Contraseña</label>
            <input id="pswd" name="pswd" type="password">
            <span>¿No tienes una cuenta? <a href="register.jsp">Crear cuenta</a></span>
            <button type="submit">Iniciar sesión</button>
        </form>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>