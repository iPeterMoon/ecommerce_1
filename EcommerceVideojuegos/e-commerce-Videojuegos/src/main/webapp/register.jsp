<%-- 
    Document   : register
    Created on : 14 nov 2025, 1:40:17 a.m.
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
            <h2 class="form-title">Crear Cuenta</h2>
            <label for="name">Nombre Completo</label>
            <input id="name" name="name" type="text">
            <label for="mail">Correo electrónico</label>
            <input id="mail" name="mail" type="email">
            <label for="pswd">Contraseña</label>
            <input id="pswd" name="pswd" type="password">
            <label for="confirm-pswd">Confirmar contraseña</label>
            <input id="confirm-pswd" name="confirm-pswd" type="password">
            <button type="submit">Registrar</button>
        </form>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>