<%-- 
    Document   : purchase-history
    Created on : 14 nov 2025, 1:39:16 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/account.css">
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    <title>Historial de Compras</title>
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
        <div class="info-account">
            <h2>Cuenta de ${sessionScope.usuario.nombre != null ? sessionScope.usuario.nombre : 'Usuario'}</h2>
            <section id="bought-history">
                <div class="section-title">
                    <img src="icons/cart.svg" alt="carrito">
                    <h3>Historial de Compras</h3>
                </div>
                <div class="edit-bg purchase-history">
                </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
        <script src="scripts/purchase-history.js"></script>

    </body>

</html>