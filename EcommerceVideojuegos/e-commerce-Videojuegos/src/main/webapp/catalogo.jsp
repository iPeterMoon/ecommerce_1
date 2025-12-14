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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <title>Catálogo de videojuegos</title>

</head>

<body class="tron-grid grid-container">
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="main-catalogo">
        <aside class="sidebar">
            <div class="sidebar-content">
                <p class="option anim1">Filtrar por:</p>

                <a href="">Plataforma</a>
                <div class="loaded-plattaforms">
                    <div class="checkbox-group">
                        <input id="plat-1" type="checkbox" class="filter-platform" value="PlayStation 5"/>
                        <label for="plat-1">PlayStation 5</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="plat-2" type="checkbox" class="filter-platform" value="PC"/>
                        <label for="plat-2">PC</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="plat-3" type="checkbox" class="filter-platform" value="Xbox Series X"/>
                        <label for="plat-3">Xbox Series X</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="plat-4" type="checkbox" class="filter-platform" value="Nintendo Switch"/>
                        <label for="plat-4">Nintendo Switch</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="plat-5" type="checkbox" class="filter-platform" value="Móvil"/>
                        <label for="plat-5">Móvil</label>
                    </div>
                </div>
                <a href="">Año</a>
               <div class="year-input">
                    <input type="number" id="filter-year" step="1" placeholder="Ej: 2005"/>
               </div>
                <a href="">Rating</a>
                <div class="rating-stars">
                    <span id="star1" class="material-symbols-outlined">star</span>
                    <span id="star2" class="material-symbols-outlined">star</span>
                    <span id="star3" class="material-symbols-outlined">star</span>
                    <span id="star4" class="material-symbols-outlined">star</span>
                    <span id="star5" class="material-symbols-outlined">star</span>
                    <span id="trashcan" class="material-symbols-outlined trash-icon">delete</span>
                </div>
                <a href="">Género</a>
                <div class="loaded-genres">
                    <div class="checkbox-group">
                        <input id="gen-1" type="checkbox" class="filter-genre" value="Acción"/>
                        <label for="gen-1">Acción</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-2" type="checkbox" class="filter-genre" value="Aventura"/>
                        <label for="gen-2">Aventura</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-3" type="checkbox" class="filter-genre" value="RPG"/>
                        <label for="gen-3">RPG</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-4" type="checkbox" class="filter-genre" value="Estrategia"/>
                        <label for="gen-4">Estrategia</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-5" type="checkbox" class="filter-genre" value="Deportes"/>
                        <label for="gen-5">Deportes</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-6" type="checkbox" class="filter-genre" value="Sandbox"/>
                        <label for="gen-6">Sandbox</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-7" type="checkbox" class="filter-genre" value="Shooter"/>
                        <label for="gen-7">Shooter</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-8" type="checkbox" class="filter-genre" value="Puzzle"/>
                        <label for="gen-8">Puzzle</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-9" type="checkbox" class="filter-genre" value="Simulación"/>
                        <label for="gen-9">Simulación</label>
                    </div>
                    <div class="checkbox-group">
                        <input id="gen-10" type="checkbox" class="filter-genre" value="Terror"/>
                        <label for="gen-10">Terror</label>
                    </div>
                </div>
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

