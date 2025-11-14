<%-- 
    Document   : moderar-resenas
    Created on : 14 nov 2025, 1:37:32 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Moderar reseñas videojuego</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/moderar_resenas.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
</head>

<body class="tron-grid grid-container">
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <h1 class="main-title">Buscador prox</h1>
        <div class="card-form-container">
            <div class="header-data">
            </div>
            <div class="form-content">
                <div class="review-container">
                    <div class="review-info">
                        <p class="user">PedritoGamer777</p>
                        <div class="calification" id="user-calification">
                            <span>Calificación:</span>
                            <img src="imgs/stars.png" alt="stars-image" class="stars" />
                        </div>
                        <p class="review-date">Reseña realizada el 13/10/2025</p>
                        <p class="review-message">
                            10/10 - El mejor sandbox de la historia. Llevo más de una década jugando y todavía no he
                            visto
                            todo lo que
                            ofrece. Empiezas golpeando un árbol para hacer una mesa y, sin darte cuenta, ya estás
                            diseñando
                            un circuito
                            de redstone para una granja automática de melones. La libertad es absoluta. Es el juego más
                            relajante del
                            mundo, hasta que escuchas un "ssssssss" y ves cómo un creeper se lleva 4 horas de tu
                            trabajo.
                            Volvería a
                            pasar mi primera noche en un hoyo de tierra otra vez. Recomendado.
                        </p>
                    </div>
                    <div class="review-delete">
                        <img src="icons/trash.svg" alt="delete-image" class="delete-icon" />
                    </div>
                </div>
                <div class="review-container">
                    <div class="review-info">
                        <p class="user">PedritoGamer777</p>
                        <div class="calification" id="user-calification">
                            <span>Calificación:</span>
                            <img src="imgs/stars.png" alt="stars-image" class="stars" />
                        </div>
                        <p class="review-date">Reseña realizada el 13/10/2025</p>
                        <p class="review-message">
                            10/10 - El mejor sandbox de la historia. Llevo más de una década jugando y todavía no he
                            visto
                            todo lo que
                            ofrece. Empiezas golpeando un árbol para hacer una mesa y, sin darte cuenta, ya estás
                            diseñando
                            un circuito
                            de redstone para una granja automática de melones. La libertad es absoluta. Es el juego más
                            relajante del
                            mundo, hasta que escuchas un "ssssssss" y ves cómo un creeper se lleva 4 horas de tu
                            trabajo.
                            Volvería a
                            pasar mi primera noche en un hoyo de tierra otra vez. Recomendado.
                        </p>
                    </div>
                    <div class="review-delete">
                        <img src="icons/trash.svg" alt="delete-image" class="delete-icon" />
                    </div>
            </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>