<%-- 
    Document   : videojuegos-resenas
    Created on : 14 nov 2025, 1:41:36 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Videojuegos reseñas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/videojuegos_resenas.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <div class="card-form-container">
            <div class="header-data">
            </div>
            <div class="form-content">
                <div class="product-row">
                    <img src="imgs/minecraft.png" alt="Minecraft" class="product-img" />
                    <div class="product-info-wrapper">
                        <p class="game-name">Minecraft</p>
                        <div class="review-container">
                            <span class="review-status">Gestionar reseñas</span>
                            <a href="resenas-videojuego.jsp" class="edit-icon-link">
                                <img src="icons/edit.svg" alt="Editar" class="edit-icon" />
                            </a>
                        </div>
                    </div>
                </div>

                <div class="product-row">
                    <img src="https://preview.redd.it/will-gta-vi-cover-art-follow-tradition-or-do-something-new-v0-vsd1bzi5fz3e1.jpg?width=493&format=pjpg&auto=webp&s=e43544cbd7e751aae8bdf4a37e84bb38d3c18322"
                        alt="GTA6" class="product-img" />
                    <div class="product-info-wrapper">
                        <p class="game-name">GTA 6</p>
                        <div class="review-container">
                            <span class="review-status">Gestionar reseñas</span>
                            <a href="resenas-videojuego.jsp" class="edit-icon-link">
                                <img src="icons/edit.svg" alt="Editar" class="edit-icon" />
                            </a>
                        </div>
                    </div>
                </div>

                <div class="product-row">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1644881681-1637887722-the-legend-of-zelda-breath-of-the-wild-nintendo-switch.jpg"
                        alt="Zelda-BreathOfTheWild" class="product-img" />
                    <div class="product-info-wrapper">
                        <p class="game-name">Zelda: Breath of the Wild</p>
                        <div class="review-container">
                            <span class="review-status">Gestionar reseñas</span>
                            <a href="resenas-videojuego.jsp" class="edit-icon-link">
                                <img src="icons/edit.svg" alt="Editar" class="edit-icon" />
                            </a>
                        </div>
                    </div>
                </div>
            </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>