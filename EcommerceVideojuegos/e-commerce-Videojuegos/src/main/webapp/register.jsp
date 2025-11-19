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
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    <title>Registrate</title>

</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
        <form action="RegistrarUsuario" method="post">
            <h2 class="form-title">Crear Cuenta</h2>
            <%
                String error = request.getParameter("error");
                if(error != null && error.equals("1")) {
            %>
                <div class="error-message">
                    Las contraseñas no coinciden, vuelva a revisarlas.
                </div>
            <%
                } else if (error != null && error.equals("2")) {
            %>
                <div class="error-message">
                    Ya existe un usuario con ese correo electrónico.
                </div>
            <%
                }
            %>
            <label for="name">Nombre Completo</label>
            <input id="name" name="name" type="text" required>
            <label for="mail">Correo electrónico</label>
            <input id="mail" name="mail" type="email" required>
            <label for="pswd">Contraseña</label>
            <input id="pswd" name="pswd" type="password" required>
            <label for="confirm-pswd">Confirmar contraseña</label>
            <input id="confirm-pswd" name="confirm-pswd" type="password" required>
            <button type="submit">Registrar</button>
        </form>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>