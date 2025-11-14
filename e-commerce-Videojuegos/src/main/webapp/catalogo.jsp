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

</head>

<body class="tron-grid grid-container">
    <header>

        <div class="brand-mobile">
            <a href="index.html">
                <img class="logo" src="imgs/logo-mundito.svg" alt="logo">
            </a>
            <a href="index.html">
                <h1>MunditoGames</h1>
            </a>
        </div>
        <div class="brand-title-desktop">
            <a href="index.html">
                <h1>MunditoGames</h1>
            </a>
        </div>


        <nav>
            <ul>
                <li class="nav-element">
                    <a href="">
                        <img src="icons/search.svg" alt="search">
                    </a>
                </li>

                <li class="nav-element">
                    <a href="shopping-cart.html">
                        <img src="icons/cart.svg" alt="cart">
                    </a>
                </li>
                <li class="nav-element">
                    <a href="login.html">
                        <img src="icons/user.svg" alt="user">
                    </a>
                </li>
            </ul>
        </nav>
    </header>
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
    <footer class="site-footer">
        <div class="footer-container">

            <div class="footer-column">
                <a href="index.html" class="logo-container">
                    <img src="imgs/logo-mundito.svg" alt="logo">
                    <h4>MunditoGames</h4>
                </a>
                <p>Tu universo de videojuegos. Encuentra los mejores títulos, ofertas y la comunidad más apasionada del
                    gaming.</p>
            </div>

            <div class="footer-column">
                <h4>Navegación</h4>
                <ul class="footer-links">
                    <li><a href="index.html">Inicio</a></li>
                    <li><a href="catalogo.html">Catálogo de Productos</a></li>
                    <li><a href="account.html">Mi Cuenta</a></li>
                    <li><a href="shopping-cart.html">Carrito de Compras</a></li>
                </ul>
            </div>

            <div class="footer-column">
                <h4 style="margin-top: 1.5rem;">Síguenos</h4>
                <ul class="social-icons">
                    <li><a href="#" aria-label="Facebook"><img src="icons/facebook.svg" alt="Facebook"></a></li>
                    <li><a href="#" aria-label="Instagram"><img src="icons/instagram.svg" alt="Instagram"></a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2025 MunditoGames. Todos los derechos reservados.</p>
        </div>
    </footer>
</body>

</html>

