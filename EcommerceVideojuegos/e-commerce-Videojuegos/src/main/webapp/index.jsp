<%-- 
    Document   : index
    Created on : 14 nov 2025, 1:35:13 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="styles/global.css">
    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/slider.css">

</head>

<body class="tron-grid grid-container">
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main>
        <section class="slider-container">
            <input type="radio" name="slider-radio" id="slide1" checked>
            <input type="radio" name="slider-radio" id="slide2">
            <input type="radio" name="slider-radio" id="slide3">
            <input type="radio" name="slider-radio" id="slide4">
            <input type="radio" name="slider-radio" id="slide5">
            <input type="radio" name="slider-radio" id="slide6">

            <div class="slides">
                <div class="slide">
                    <a href="#">
                        <img id="the-last-of-us" src="https://i.ytimg.com/vi_webp/eOiUtRF8k28/maxresdefault.webp"
                            alt="the last of us 2">
                    </a>
                </div>
                <div class="slide">
                    <a href="#">
                        <img id="spiderman" src="https://superfan.art/wp-content/uploads/2023/10/Banner-Spiderman-2.jpg"
                            alt="spiderman 2">
                    </a>
                </div>
                <div class="slide">
                    <a href="#">
                        <img id="god-of-war-ragnarok"
                            src="https://www.muycomputer.com/wp-content/uploads/2022/12/god-of-war-ragnarok.jpg"
                            alt="god of war ragnarok">
                    </a>
                </div>
                <div class="slide">
                    <a href="#">
                        <img id="forza"
                            src="https://image.api.playstation.com/vulcan/ap/rnd/202502/1300/6b10b223febf1ccffd55ba6da645f9afad6b027ab5327fbd.jpg?w=440"
                            alt="Forza">
                    </a>
                </div>
                <div class="slide">
                    <a href="#">
                        <img id="fifa" src="https://media.vandal.net/i/1280x720/7-2024/17/202471715564644_1.jpg"
                            alt="fifa">
                    </a>
                </div>
                <div class="slide">
                    <a href="#">
                        <img id="nba2k"
                            src="https://img.asmedia.epimg.net/resizer/v2/H5T6S6D3CBBJDOIWI5SE4ZXWZY.jpg?auth=7152cd5af15d88b9c3983fbd011abe11d671b2bd5bc1cedadd0770a395acd638&width=1472&height=828&smart=true"
                            alt="nba2k">
                    </a>
                </div>
            </div>

            <div class="slider-dots">
                <label for="slide1" class="dot"></label>
                <label for="slide2" class="dot"></label>
                <label for="slide3" class="dot"></label>
                <label for="slide4" class="dot"></label>
                <label for="slide5" class="dot"></label>
                <label for="slide6" class="dot"></label>
            </div>

            <div class="slider-arrows">
                <label class="prev-slide" for="slide6">&lt;</label> <label class="next-slide" for="slide2">&gt;</label>
                <label class="prev-slide" for="slide1">&lt;</label> <label class="next-slide" for="slide3">&gt;</label>
                <label class="prev-slide" for="slide2">&lt;</label> <label class="next-slide" for="slide4">&gt;</label>
                <label class="prev-slide" for="slide3">&lt;</label> <label class="next-slide" for="slide5">&gt;</label>
                <label class="prev-slide" for="slide4">&lt;</label> <label class="next-slide" for="slide6">&gt;</label>
                <label class="prev-slide" for="slide5">&lt;</label> <label class="next-slide" for="slide1">&gt;</label>
            </div>
        </section>

        <section class="featured-games">
            <div class="featured-header">
                <h2 class="section-title">Juegos destacados</h2>
                <a href="catalogo.jsp" class="view-all">Ver todo</a>
            </div>

            <div class="games-grid landing-grid">
                <a href="producto.jsp" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1721500402-minecraft-ps5-0.webp"
                        alt="minecraft">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/0/05/Call_of_Duty_Black_Ops_II_box_artwork.png"
                        alt="call of duty black ops 2">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
                        alt="zelda breath of the wild">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp"
                        alt="gta VI">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://m.media-amazon.com/images/I/81bwynfO98L.jpg" alt="call of duty black ops 3">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://juegosdigitalesmexico.mx/files/images/productos/1744069588-hogwarts-legacy-digital-deluxe-edition-ps5-0.webp"
                        alt="harry potter">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/b/b9/Elden_Ring_Box_art.jpg" alt="elden ring">
                </a>
                <a href="producto.jsp" class="game-card">
                    <img src="https://upload.wikimedia.org/wikipedia/en/b/bb/Dark_souls_3_cover_art.jpg"
                        alt="dark souls 3">
                </a>
            </div>
        </section>

        </div>
    </main>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>
