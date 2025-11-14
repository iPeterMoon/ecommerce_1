<%-- 
    Document   : producto
    Created on : 14 nov 2025, 1:38:49 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Producto Específico</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="styles/styles.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/producto.css" />
  </head>
  <body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
      <article class="grid-item-info">
        <div class="image-and-price">
          <img src="imgs/minecraft.png" alt="Minecraft-Logo" />
          <div class="calification" id="calificacion-general">
            <span>Calificación global:</span>
            <img src="imgs/stars.png" alt="stars-image" class="stars" />
          </div>

          <div class="button-with-price">
            <span class="price">MEX 349.00</span>
            <a href="#item-agregado-modal" class="add-to-car"> Añadir al Carrito </a>
          </div>
          <div class="specifications" id="a">
            <p>Plataforma: PlayStation 5</p>
            <p>Desarrollador: Mojang</p>
            <p>Año de Lanzamiento: 2009</p>
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
        <div class="review-container">
          <p class="user">PedritoGamer777</p>
          <div class="calification" id="user-calification">
            <span>Calificación:</span>
            <img src="imgs/stars.png" alt="stars-image" class="stars" />
          </div>
          <p class="review-date">Reseña realizada el 13/10/2025</p>
          <p class="review-message">
            10/10 - El mejor sandbox de la historia. Llevo más de una década jugando y todavía no he visto todo lo que
            ofrece. Empiezas golpeando un árbol para hacer una mesa y, sin darte cuenta, ya estás diseñando un circuito
            de redstone para una granja automática de melones. La libertad es absoluta. Es el juego más relajante del
            mundo, hasta que escuchas un "ssssssss" y ves cómo un creeper se lleva 4 horas de tu trabajo. Volvería a
            pasar mi primera noche en un hoyo de tierra otra vez. Recomendado.
          </p>
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
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
  </body>
</html>
