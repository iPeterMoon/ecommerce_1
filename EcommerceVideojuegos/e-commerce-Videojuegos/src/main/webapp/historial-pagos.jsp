<%-- 
    Document   : historial-pagos
    Created on : 14 nov 2025, 1:34:44 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Historial pagos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/historial_pagos.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

    <main class="container">
        <h1 class="main-title">Buscador próx</h1>
        <div class="card-form-container">
            <div class="header-data">
            </div>
            <div class="form-content">
                <div class="review-container">
                    <div class="review-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 10524</p>
                        </div>
                        <p class="payment-method">Método de pago: Tarjeta</p>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/check.svg" class="check" />Pagado</a>
                        </div>
                    </div>
                </div>

                <div class="review-container">
                    <div class="review-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 20831</p>
                        </div>
                        <p class="payment-method">Método de pago: Tarjeta</p>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/check.svg" class="check" />Pagado</a>
                        </div>
                    </div>
                </div>

                <div class="review-container">
                    <div class="review-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 70289</p>
                        </div>
                        <p class="payment-method">Método de pago: Tarjeta</p>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/check.svg" class="check" />Pagado</a>
                        </div>
                    </div>
                </div>
            </div>
    </main>

      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>