<%-- 
    Document   : pedidos-pendientes
    Created on : 14 nov 2025, 1:38:30 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Pedidos pendientes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/pedidos_pendientes.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <div class="search-div">
            <input type="text" id="search" class="search" placeholder="Buscar...">
        </div>
        <div class="card-form-container">
            <div class="header-data">
            </div>
            <div class="form-content">
                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 10733</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 15 Oct, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.jsp" class="approve-btn">Validar pago</a>
                    </div>
                </div>

                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 1921</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 07 Dic, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.html" class="approve-btn">Validar pago</a>
                    </div>
                </div>

                <div class="payment-container">
                    <div class="payment-info">
                        <div class="pedido">
                            <p class="pedido-texto">Pedido - </p>
                            <p class="pedido">N°: 1267</p>
                        </div>
                        <div class="pago-metodo">
                            <p class="payment-method">Fecha del pedido: 29 Feb, 2025</p>
                            <p class="payment-method">Método de pago: Tarjeta</p>
                        </div>
                        <div class="pedido">
                            <p class="payment-status">Estado de pago: </p>
                            <a class="status"><img src="icons/pending.svg" class="check" />Pendiente</a>
                        </div>
                    </div>
                    <div class="payment-aprrove-button">
                        <a href="???.html" class="approve-btn">Validar pago</a>
                    </div>
                </div>
            </div>
    </main>

      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>