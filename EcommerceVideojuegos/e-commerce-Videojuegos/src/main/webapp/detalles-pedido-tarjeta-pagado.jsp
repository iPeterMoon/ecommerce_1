<%-- 
    Document   : detalles-pedido-tarjeta-pagado
    Created on : 14 nov 2025, 1:33:51 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Detalles pedido</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/detalles_pedido_tarjeta_pagado.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <h1 class="main-title">Pedido realizado</h1>
        <div class="card-form-container">
            <div class="header-data">
                <p class="header-title">Detalles del pedido N: 10524, Fecha 15 Oct, 2025</p>
            </div>
            <div class="form-content">
                <div class="details-container">
                    <div class="details-info">

                        <div class="details-direction">
                            <div class="info-content">
                                <div class="content-title">
                                    <p class="user">Dirección del envío</p>
                                </div>
                                <div class="content-direction">
                                    <p>Calle Falsa 123, colonia Centro</p>
                                    <p>Ciudad Obregón</p>
                                    <p>Número referencia: 123123123123</p>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="details-game">
                        <img src="https://preview.redd.it/new-minecraft-art-cover-what-do-you-think-v0-jncj9eqr9bad1.jpeg?auto=webp&s=8ba0359485474ee23ca522d0fdddb59bdec9c250"
                            class="details-gamelogo">
                        <div class="details-game-amount">
                            <p class="game-name">Minecraft</p>
                            <p>Cantidad: 1</p>
                        </div>
                        <p class="price-game">Mex$ 349.00</p>
                    </div>

                    <div class="details-price">
                        <div class="price-subtotal rightdetail">
                            <p>Subtotal</p>
                            <p class="righty">$349.00</p>
                        </div>
                        <div class="price-shipment rightdetail">
                            <p>Envío</p>
                            <p class="righty">$0.00</p>
                        </div>
                        <div class="price-total rightdetail">
                            <p>Total</p>
                            <p class="righty">$349.00</p>
                        </div>
                        <div class="payment-status rightdetail">
                            <p>Estado pago:</p>
                            <p class="pending righty">Pagado</p>
                        </div>
                        <div class="continue-shopping rightdetail">
                            <a href="index.jsp" class="righty btnKeepShp">Seguir comprando</a>
                        </div>
                    </div>

                </div>

            </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>