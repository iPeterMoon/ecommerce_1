<%-- 
    Document   : order
    Created on : 14 nov 2025, 1:37:57 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>Ver Pedido</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" type="text/css" media="screen" href="styles/order.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
   <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
   
  <main class="container">
    <h1 class="tittle">Cuenta de Fulanito</h1>
    <div class="header-container">
      <img src="icons/cart.svg" class="cart-icon" />
      <p class="details-paragraph">Detalles de pedido</p>
      <p>-</p>
      <p class="order-number">N°:10524,</p>
      <p class="order-date">Fecha:15 Oct, 2025</p>
    </div>
    <div class="main-container">
      <div class="order-information">
        <div class="personal-information">
          <div class="shipment-information">
            <p>Dirección de Envío</p>
            <p>Calle Falsa 123, Colonia Centro</p>
            <p>Ciudad Obregón, Son, CP 12345</p>
          </div>
          <div class="payment-information">
            <p>Resumen de Pago</p>
            <p>Método de Pago: Tarjeta de Crédito</p>
            <p>Terminación: **** **** **** 4242</p>
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

            <p class="product-price">$1,499.00</p>
          </div>

          <div class="product-row">
            <div class="product-info-left">
              <img
                src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp" />
              <div class="tittle-quantity">
                <p>GTA VI</p>
                <p>Cantidad: 1</p>
              </div>
            </div>

            <p class="product-price">$2,599.00</p>
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
            <div class="subtotal">$4,098.00</div>
            <div class="shipment-price">$0.00</div>
            <div class="total-price">$4,098.00</div>
          </div>
          <div class="order-status">
            <img src="icons/check.svg" />
            <p class="status">Entregado</p>
          </div>
        </div>
      </div>
    </div>
  </main>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>