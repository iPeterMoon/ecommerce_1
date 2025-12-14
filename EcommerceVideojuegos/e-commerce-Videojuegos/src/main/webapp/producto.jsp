<%-- Document : producto Created on : 14 nov 2025, 1:38:49 a.m. Author : benja --%> <%@page contentType="text/html"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Producto Específico</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="styles/styles.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/producto.css" />
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  </head>
  <body class="tron-grid grid-container">
    <script src="scripts/cart.js"></script>
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
      <article class="grid-item-info">
        <div class="image-and-price">
          <img src="imgs/minecraft.png" alt="Minecraft-Logo" />
          <div class="calification" id="calificacion-general">
            <span>Calificación global:</span>
            <div id="global-stars-container" style="display: inline-flex; gap: 2px"></div>
            <span id="global-score-text" style="margin-left: 5px; font-weight: bold"></span>
          </div>

          <div class="button-with-price">
            <span class="price">MEX 349.00</span>
            <a href="#item-agregado-modal" onclick="addToCart(502)" class="add-to-car"> Añadir al Carrito </a>
          </div>
          <div class="specifications" id="a">
            <p>Plataforma: <span id="spec-platform">Cargando...</span></p>
            <p>Desarrollador: <span id="spec-developer">Cargando...</span></p>
            <p>Año de Lanzamiento: <span id="spec-year">Cargando...</span></p>
          </div>
        </div>
        <div class="item-description">
          <h2 class="game-tittle">Minecraft</h2>
          <p class="description">
            Minecraft es un juego formado por bloques, criaturas y comunidades. La elección es tuya: sobrevivir a la
            noche o crear una obra de arte. Pero, si te abruma tener que explorar un nuevo y vasto mundo por tu cuenta,
            ¡no temas! ¡Vamos a explorar de qué va Minecraft!
          </p>
        </div>
        <div class="specifications" id="specifications-hide">
          <p>Plataforma: PlayStation 5</p>
          <p>Desarrollador: Mojang</p>
          <p>Año de Lanzamiento: 2009</p>
        </div>
      </article>
      <section class="user-reviews">
        <h2>Reseñas de usuarios</h2>
        <div class="review-container" id="reviews-list">
          <p>Cargando reseñas...</p>
        </div>
      </section>
      
      <div class="item-added" id="item-agregado-modal">
        <div class="modal-content">
          <h2>¡Añadido a tu carrito!</h2>
          <div class="item-cart-info">
            <img src="imgs/minecraft.png" alt="" class="item-img" />
            <div class="product-cart-information">
              <p class="game-tittle">Minecraft</p>
              <p class="quantity">Cantidad: 1</p>
              <p class="price-item">Precio: MEX $349.00</p>
            </div>
          </div>
          <div class="modal-buttons">
            <a href="index.jsp" class="modal-close">Seguir comprando</a>
            <a href="shopping-cart.jsp" class="modal-close" id="color-blue">Ver mi carro</a>
          </div>
        </div>
      </div>
    </main>
    <script src="scripts/product-detail.js"></script>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>
  </body>
</html>
