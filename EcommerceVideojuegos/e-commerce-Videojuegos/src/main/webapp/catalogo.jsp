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

    <div id="product-container" class="games-grid catalogo-grid">
        </div>

    <div id="sentinel" style="height: 50px; text-align: center; padding: 20px;">
        <p>Cargando más juegos...</p>
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
    <script src="scripts/catalogo.js"></script>

    </body>

</html>

