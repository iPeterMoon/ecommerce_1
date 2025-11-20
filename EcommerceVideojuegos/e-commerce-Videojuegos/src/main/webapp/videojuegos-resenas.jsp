<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Seleccionar Producto</title>
        <link rel="stylesheet" href="styles/styles.css" />
        <link rel="stylesheet" href="styles/videojuegos_resenas.css" />
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
        <link rel="stylesheet" href="styles/breadcrumb.css">    
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    </head>
    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main class="container">
            <nav id="breadcrumb-nav" aria-label="breadcrumb">
                <ol class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="admin-options.jsp">Panel Admin</a></li>

                    <li class="breadcrumb-item active" aria-current="page">Gestionar Reseñas</li>

                </ol>
            </nav>
            <h1 class="main-title" style="text-align: center; margin-bottom: 1.5rem; max-width: 750px; color: var(--text-white);">Seleccionar Producto</h1>
            <c:if test="${not empty error}">
                <p style="color: red; text-align: center;">${error}</p>
            </c:if>

            <div class="card-form-container">
                <div class="form-content"> 

                    <c:forEach var="producto" items="${listaProductos}">
                        <div class="product-row">                            
                            <c:if test="${not empty producto.imagenBase64}">
                                <img class="product-img" 
                                     src="data:image/jpeg;base64,${producto.imagenBase64}"
                                     alt="${producto.nombreProducto}" />
                            </c:if>

                            <div class="product-info-wrapper">
                                <p class="game-name">
                                    ${producto.nombreProducto}
                                </p>

                                <div class="review-container">
                                    <span class="review-status">
                                        Moderar Reseñas
                                    </span>
                                    <a href="ResenaServlet?accion=cargarResenas&idProducto=${producto.idProducto}">
                                        <img class="edit-icon" src="${pageContext.request.contextPath}/icons/edit.svg" alt="edit" />
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:if test="${empty listaProductos}">
                        <p style="text-align: center; color: var(--text-white);">
                            No hay productos registrados en la base de datos.
                        </p>
                    </c:if>

                </div>
            </div>
        </main>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>