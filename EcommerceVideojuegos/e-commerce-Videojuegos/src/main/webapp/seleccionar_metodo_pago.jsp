<%-- 
    Document   : seleccionar_metodo_pago
    Created on : 14 nov 2025, 1:40:43 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Seleccionar método pago</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/seleccionar_metodo_pago.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <h1 class="main-title">Seleccionar método de pago</h1>
        <div class="payment-method-container">
            <div class="header-methods">
                <p>Métodos de pago</p>
            </div>
            <div class="methods-content">
                <a href="introducir_datos_bancarios.jsp"><button class="method-button">Tarjeta</button></a>
                <a href="detalles-pedido-transfe.jsp"><button class="method-button">Transferencia</button></a>
                <a href="detalles-pedido-contraentrega.jsp"><button class="method-button">Contra entrega</button></a>
            </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>
