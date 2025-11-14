<%-- 
    Document   : introducir_datos_bancarios
    Created on : 14 nov 2025, 1:36:06 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>introducir_datos_bancarios</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/introducir_datos_bancarios.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <h1 class="main-title">Ingresar datos bancarios</h1>
        <div class="card-form-container">
            <div class="header-data">
                <p>Datos bancarios</p>
            </div>
            <div class="form-content">
                <div class="form-row">
                    <div class="form-group large-width">
                        <label for="cardNumber">Número de tarjeta</label>
                        <input type="text" id="cardNumber">
                    </div>
                    <div class="form-group small-width">
                        <label for="cvv">CVV</label>
                        <input type="text" id="cvv">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group large-width">
                        <label for="cardName">Nombre del titular</label>
                        <input type="text" id="cardName">
                    </div>
                    <div class="form-group small-width">
                        <label for="expiryDate">Fecha de vencimiento</label>
                        <input type="text" id="expiryDate">
                    </div>
                </div>
            </div>

            <div class="form-buttons-container">
                <a href="seleccionar_metodo_pago.jsp"><button class="cancel-button">Cancelar</button></a>
                <a href="detalles-pedido-tarjeta-pagado.jsp"><button class="confirm-button">Confirmar</button></a>
            </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>