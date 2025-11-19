<%-- 
    Document   : catalogo
    Created on : 14 nov 2025, 1:30:48 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/catalogo.css">
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    <title>Catálogo de videojuegos</title>

</head>

<body class="tron-grid grid-container">
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="main-catalogo">
        <aside class="sidebar">
            <div class="sidebar-content">
                <p>Filtrar por:</p>

                <a href="">Plataforma</a>
                <a href="">Año</a>
                <a href="">Rating</a>
                <a href="">Género</a>
            </div>

        </aside>
        <section class="featured-games">
            <div class="featured-filters">
                <h2 class="section-title">Catálogo</h2>
                <a href="#filter-modal">
                    <img src="icons/filter.svg" alt="filter">
                </a>
            </div>

            <div class="games-grid catalogo-grid">
                <a href="#" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1721500402-minecraft-ps5-0.webp"
                        alt="minecraft">
                </a>
                <a href="#" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/0/05/Call_of_Duty_Black_Ops_II_box_artwork.png"
                        alt="Call of Duty Black Ops 2">
                </a>
                <a href="#" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
                        alt="Zelda Breath of the Wild">
                </a>
                <a href="#" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp"
                        alt="GTA VI">
                </a>
                <a href="#" class="game-card">
                    <img src="https://m.media-amazon.com/images/I/81bwynfO98L.jpg" alt="Call of Duty Black Ops 3">
                </a>
                <a href="#" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1744069588-hogwarts-legacy-digital-deluxe-edition-ps5-0.webp"
                        alt="Hogwarts Legacy">
                </a>
                <a href="#" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/b/b9/Elden_Ring_Box_art.jpg" alt="Elden Ring">
                </a>
                <a href="#" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/b/bb/Dark_souls_3_cover_art.jpg"
                        alt="Dark Souls 3">
                </a>
            </div>
        </section>
        </div>
    </main>
    <div class="modal-overlay" id="filter-modal">
        <div class="modal-container sidebar-modal">
            <a href="#" class="close-modal-button">X</a>
            <div class="sidebar-content">
                <p>Filtrar por:</p>
                <a href="#" class="filter-option">Plataforma</a>
                <a href="#" class="filter-option">Género</a>
                <a href="#" class="filter-option">Año</a>
            </div>
        </div>
    </div>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>

