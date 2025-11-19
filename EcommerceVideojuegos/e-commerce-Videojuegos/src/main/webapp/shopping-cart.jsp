<%-- 
    Document   : shopping-cart
    Created on : 14 nov 2025, 1:41:03 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>Carrito</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="styles/shopping-cart.css" />
  <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
</head>

<body class="tron-grid grid-container">
   <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
  <main>

    <div class="container">
      <h1>Carrito</h1>
      <div class="products">
        <div class="product-row">
          <img src="icons/x-button.svg" alt="" class="delete-button" />
          <img src="imgs/minecraft.png" alt="" class="product-img" />
          <div class="tittle">
            <p>Título</p>
            <p class="game-name">Minecraft</p>
          </div>
          <div class="quantity">
            <p>Cantidad</p>
            <p class="product-quantity">1</p>
          </div>
          <div class="price">
            <p>Precio</p>
            <p class="product-price">349.00</p>
          </div>
          <div class="subtotal">
            <p>Subtotal</p>
            <p class="product-subtotal">349.00</p>
          </div>
        </div>
        <div class="product-row">
          <img src="icons/x-button.svg" alt="" class="delete-button" />
          <img src="imgs/minecraft.png" alt="" class="product-img" />
          <div class="tittle">
            <p>Título</p>
            <p class="game-name">Minecraft</p>
          </div>
          <div class="quantity">
            <p>Cantidad</p>
            <p class="product-quantity">1</p>
          </div>
          <div class="price">
            <p>Precio</p>
            <p class="product-price">349.00</p>
          </div>
          <div class="subtotal">
            <p>Subtotal</p>
            <p class="product-subtotal">349.00</p>
          </div>
        </div>

        <div class="product-row">
          <img src="icons/x-button.svg" alt="" class="delete-button" />
          <img src="imgs/minecraft.png" alt="" class="product-img" />
          <div class="tittle">
            <p>Título</p>
            <p class="game-name">Minecraft</p>
          </div>
          <div class="quantity">
            <p>Cantidad</p>
            <p class="product-quantity">1</p>
          </div>
          <div class="price">
            <p>Precio</p>
            <p class="product-price">349.00</p>
          </div>
          <div class="subtotal">
            <p>Subtotal</p>
            <p class="product-subtotal">349.00</p>
          </div>
        </div>
      </div>
      <section class="resume-products">
        <div class="subtittles">
          <p>Artículos totales</p>
          <p>Total estimado:</p>
          <p>Envío estimado:</p>
        </div>

        <div class="results">
          <p class="total-products">(1)</p>
          <p class="estimated-total">$349.00</p>
          <p class="estimated-shipment">5 días hábiles.</p>
        </div>
        <div class="information">
          <div class="purchase-information">
            <img src="icons/lock.svg" />
            <p>No se te cobrará hasta que revises este pedido en la página siguiente</p>
          </div>
          <div class="button-with-price">
            <span class="price-purchase">MEX 349.00</span>
            <a href="realizar_pedido.jsp" class="add-to-car"> Realizar Pedido </a>
          </div>
        </div>
      </section>
    </div>
  </main>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>