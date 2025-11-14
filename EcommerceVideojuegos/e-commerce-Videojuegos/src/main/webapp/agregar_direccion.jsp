<%-- 
    Document   : agregar_direccion
    Created on : 14 nov 2025, 1:30:02 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Agregar dirección</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/agregar_direccion.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
   <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

    <main class="container">
        <h1 class="main-title">Favor de agregar una dirección para el envío</h1>
        <div class="address-form-container">
            <div class="form-header">
                <p>Agregar dirección</p>
            </div>
            <div class="form-content">
                <div class="form-group">
                    <label for="addressName">Nombre de la dirección</label>
                    <input type="text" id="addressName">
                </div>

                <div class="form-row">
                    <div class="form-group half-width">
                        <label for="street">Calle</label>
                        <input type="text" id="street">
                    </div>
                    <div class="form-group quarter-width">
                        <label for="extNumber">Número exterior</label>
                        <input type="text" id="extNumber">
                    </div>
                </div>

                <div class="form-group">
                    <label for="colony">Colonia</label>
                    <input type="text" id="colony">
                </div>

                <div class="form-row">
                    <div class="form-group half-width">
                        <label for="zipCode">Código postal</label>
                        <input type="text" id="zipCode">
                    </div>
                    <div class="form-group quarter-width">
                        <label for="city">Ciudad</label>
                        <input type="text" id="city">
                    </div>
                </div>

                <div class="form-group">
                    <label for="state">Estado</label>
                    <input type="text" id="state">
                </div>
            </div>
            <div class="form-buttons">
                <a href="shopping-cart.jsp"><button class="cancel-button">Cancelar</button></a>
                <a href="realizar_pedido.jsp"><button class="save-button">Guardar</button></a>
            </div>
        </div>
    </main>

      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>
