<%-- 
    Document   : realizar_pedido
    Created on : 14 nov 2025, 1:40:01 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Realizar pedido</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/realizar_pedido.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

    <main class="container">
        <h1 class="tittle">Detalles de pedido</h1>
        <div class="header-container">
            <p class="details-paragraph">Confirmar pedido</p>
        </div>
        <div class="main-container">
            <div class="order-information">
                <div class="personal-information">
                    <div class="shipment-information">
                        <p>Dirección de Envío</p>
                        <p>Calle Falsa 123, Colonia Centro</p>
                        <p>Ciudad Obregón, SON, CP 12345</p>
                    </div>
                </div>
                <hr />
                <div class="items-content">
                    <div class="product-row">
                        <div class="product-info-left">
                            <img src="imgs/minecraft.png" />
                            <div class="tittle-quantity">
                                <p>Minecraft</p>
                                <p>Cantidad: 1</p>
                            </div>
                        </div>

                        <p class="product-price">Mex$ 349.00</p>
                    </div>
                </div>
                <hr />
                <div class="prices-status-order">
                    <div class="prices-subtittle">
                        <p>Subtotal</p>
                        <p>Envío</p>
                        <p>Total</p>
                    </div>
                    <div class="prices">
                        <div class="subtotal">$349.00</div>
                        <div class="shipment-price">$0.00</div>
                        <div class="total-price">$349.00</div>
                    </div>
                </div>

                <div class="buttons-container">
                    <a href="shopping-cart.jsp"><button class="cancel-button">Cancelar</button></a>
                    <a href="seleccionar_metodo_pago.jsp" ><button class="payment-button">Proceder al pago</button></a>
                </div>
            </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>
