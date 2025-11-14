<%-- 
    Document   : review
    Created on : 14 nov 2025, 03:48:36
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Escribir Reseña</title>
        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/review.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    </head>

    <body class="tron-grid grid-container">
         <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main>
            <div class="review-container">
                <div class="rating-section">
                    <p class="question-text">¿Con cuantas estrellas calificarías este juego?</p>

                    <form class="star-rating" action="#" method="post">
                        <input type="radio" id="star5" name="rating" value="5" />
                        <label for="star5" title="5 estrellas">
                            <i class="fas fa-star"></i>
                        </label>

                        <input type="radio" id="star4" name="rating" value="4" />
                        <label for="star4" title="4 estrellas">
                            <i class="fas fa-star"></i>
                        </label>

                        <input type="radio" id="star3" name="rating" value="3" />
                        <label for="star3" title="3 estrellas">
                            <i class="fas fa-star"></i>
                        </label>

                        <input type="radio" id="star2" name="rating" value="2" />
                        <label for="star2" title="2 estrellas">
                            <i class="fas fa-star"></i>
                        </label>

                        <input type="radio" id="star1" name="rating" value="1" />
                        <label for="star1" title="1 estrella">
                            <i class="fas fa-star"></i>
                        </label>

                    </form>
                    <div class="rating-focus-box"></div>
                </div>

                <div class="review-section">
                    <label for="videojuego-review" class="review-label">Reseña del videojuego:</label>
                    <textarea id="videojuego-review" class="review-textbox" rows="10"></textarea>
                    <p class="public-notice">El contenido que estas apunto de escribir será publico para todos</p>
                </div>

                <div class="action-buttons">
                    <button class="btn-cancel">Cancelar</button>

                    <a href="#review-published-modal" class="btn-publish">Publicar reseña</a>
                </div>
            </div>

            <div class="modal-published" id="review-published-modal"> <!--modal simulado-->
                <div class="modal-content-published">
                    <h2>Reseña publicada!</h2>

                    <a href="index.jsp" class="btn-back-to-home">Volver al inicio</a>
                </div>
            </div>
        </main>

          <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>
