<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Ver Pedido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/order.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
        <link rel="stylesheet" href="styles/breadcrumb.css">    
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main class="container">
            <nav id="breadcrumb-nav" aria-label="breadcrumb">
                <ol class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="index.jsp">Inicio</a></li>
                    <li class="breadcrumb-item"><a href="account.jsp">Mi Cuenta</a></li>
                    <li class="breadcrumb-item"><a href="purchase-history.jsp">Historial</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Detalle</li>
                </ol>
            </nav>

            <div id="loading-message" style="text-align: center; padding: 40px;">
                <h2>Cargando detalles del pedido...</h2>
            </div>

            <div id="error-container" style="display:none; text-align: center; padding: 40px; color: var(--red-error-text);">
                <h2 id="error-title">Error</h2>
                <p id="error-text">No se pudo cargar el pedido.</p>
                <a href="purchase-history.jsp" style="color: cyan; text-decoration: underline;">Volver al historial</a>
            </div>

            <div id="order-content" style="display: none;">
                <h1 class="tittle">Cuenta de <span id="user-name-title">Cargando...</span></h1>

                <div class="header-container">
                    <img src="icons/cart.svg" class="cart-icon" />
                    <p class="details-paragraph">Detalles de pedido</p>
                    <p>-</p>
                    <p class="order-number">N°: <span id="order-id"></span></p>
                    <p class="order-date">Fecha: <span id="order-date"></span></p>
                </div>

                <div class="main-container">
                    <div class="order-information">
                        <div class="personal-information">
                            <div class="shipment-information">
                                <p><strong>Información del Cliente</strong></p>
                                <p>Nombre: <span id="client-name"></span></p>
                                <p>Correo: <span id="client-email"></span></p>
                                <p id="client-address">Dirección registrada</p> 
                            </div>
                            <div class="payment-information">
                                <p><strong>Resumen de Pago</strong></p>
                                <p>Método de Pago: <span id="payment-method"></span></p>
                                <p>Referencia: <span id="payment-ref"></span></p>
                                <p>Estado: <span id="payment-status"></span></p>
                            </div>
                        </div>
                        <hr />

                        <div class="items-content" id="items-container">
                        </div>

                        <hr />
                        <div class="prices-status-order">
                            <div class="prices-subtittle">
                                <p>Subtotal</p>
                                <p>Envío</p>
                                <p><strong>Total Pagado</strong></p>
                            </div>
                            <div class="prices">                        
                                <div class="subtotal">
                                    <strong id="subtotal-amount">$0.00</strong>
                                </div>
                                <div class="shipment-price">$0.00</div>
                                <div class="total-price">
                                    <strong id="total-amount">$0.00</strong>
                                </div>
                            </div>
                            <div class="order-status" id="status-container">
                                <img src="icons/pending.svg" id="status-icon"/>
                                <p class="status" id="status-text">PENDIENTE</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@include file="WEB-INF/fragmentos/footer.jspf" %>

        <script src="scripts/order-details.js"></script>

    </body>
</html>