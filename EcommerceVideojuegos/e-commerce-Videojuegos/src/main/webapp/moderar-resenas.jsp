<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Moderar reseñas videojuego</title>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/moderar_resenas.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    </head>
    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
        <main class="container">
            <h1 class="main-title">Moderar Reseñas</h1>

            <c:if test="${not empty error}">
                <p class="errorTxt">${error}</p>
            </c:if>

            <div class="card-form-container">
                <div class="header-data">
                    <%-- aquí podria ir un buyscador por juego maybe --%>
                </div>
                <div class="form-content">

                    <c:forEach var="resena" items="${listaResenas}">
                        <div class="review-container">
                            <div class="review-info">
                                <p class="user">${resena.nombreUsuario}</p>
                                <div class="calification">
                                    <span>Calificación: ${resena.calificacion}</span>
                                    <img src="${pageContext.request.contextPath}/imgs/stars.png" alt="stars-image" class="stars" />
                                </div>
                                <p class="review-date">Reseña realizada el ${resena.fecha}</p>
                                <p class="review-message">
                                    ${resena.comentario}
                                </p>
                            </div>

                            <div class="review-actions">

                                <input type="checkbox" 
                                       class="modal-toggle-edit"
                                       id="modal-toggle-${resena.idResena}">

                                <label for="modal-toggle-${resena.idResena}" class="edit-label">
                                    <img src="${pageContext.request.contextPath}/icons/edit.svg" alt="Editar" class="action-icon" />
                                </label>

                                <input type="checkbox" 
                                       class="modal-toggle-delete" <%-- ¡CAMBIO! --%>
                                       id="delete-toggle-${resena.idResena}">

                                <label for="delete-toggle-${resena.idResena}" class="delete-label">
                                    <img src="${pageContext.request.contextPath}/icons/trash.svg" alt="Eliminar" class="action-icon" />
                                </label>

                                <%-- modal de eliminar para no perdermne --%>
                                <div class="modal-container modal-delete-container">
                                    <div class="modal-content-delete">
                                        <label for="delete-toggle-${resena.idResena}" class="modal-close-label">&times;</label>

                                        <h2 style="margin-top:0;">Confirmar Eliminación</h2>
                                        <p>¿Estás seguro de que deseas eliminar esta reseña?</p>

                                        <div class="modal-delete-actions">
                                            <label for="delete-toggle-${resena.idResena}" class="modal-delete-cancel">
                                                Cancelar
                                            </label>

                                            <form action="ResenaServlet" method="POST" style="display: inline;">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="idResena" value="${resena.idResena}">
                                                <button type="submit" class="modal-delete-confirm">
                                                    Confirmar
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                                <%-- modal de editar para no perdermne --%>                
                                <div class="modal-container modal-edit-container"> 
                                    <div class="modal-content-pure">
                                        <label for="modal-toggle-${resena.idResena}" class="modal-close-label">&times;</label>
                                        <h2>Editar Reseña</h2>
                                        <form class="modal-form" action="ResenaServlet" method="POST">
                                            <input type="hidden" name="accion" value="actualizar">
                                            <input type="hidden" name="idResena" value="${resena.idResena}">
                                            <div>
                                                <label for="modal_calificacion-${resena.idResena}">Calificación:</label>
                                                <input type="number" 
                                                       id="modal_calificacion-${resena.idResena}" 
                                                       name="calificacion" 
                                                       min="1" max="5" 
                                                       value="${resena.calificacion}" 
                                                       required>
                                            </div>
                                            <div>
                                                <label for="modal_comentario-${resena.idResena}">Comentario:</label>
                                                <textarea id="modal_comentario-${resena.idResena}" 
                                                          name="comentario" 
                                                          required>${resena.comentario}</textarea>
                                            </div>
                                            <button type="submit" class="modal-save-button">Guardar Cambios</button>
                                        </form>
                                    </div>
                                </div>

                            </div> </div> </c:forEach>

                    <c:if test="${empty listaResenas}">
                        <p class="noResenas" style="color: var(--text-white); text-align: center; padding: 20px;">No hay reseñas para moderar.</p>
                    </c:if>

                </div>
            </div>
        </main>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>