<%-- 
    Document   : crud-products
    Created on : 14 nov 2025, 1:32:33 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestionar Productos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/admin.css">
        <link rel="stylesheet" href="styles/crud-games.css"/> 
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main>
            <h1>Gestionar Productos</h1>

            <div class="search-div">
                <form action="ProductoServlet" method="GET" class="buscador">
                    <input type="text" name="busqueda" id="search" class="search" 
                           placeholder="Buscar producto..." 
                           value="<c:out value='${param.busqueda}'/>" />
                    <div class="items-busqueda">   
                        <button type="submit" class="boton">
                            <img src="icons/search.svg" alt="buscar" class="search-img"/>
                        </button>
                        <c:if test="${not empty param.busqueda}">
                            <a class="x-button" href="ProductoServlet">✖</a>
                        </c:if>
                    </div>
                </form>
            </div>

            <c:if test="${param.errorEliminar == '1'}">
                <div class="error-message" style="margin-bottom: 1rem;">
                    No se pudo eliminar el producto.
                    Es posible que tenga pedidos o dependencias asociadas.
                </div>
            </c:if>

            <div class="main-div">
                <c:if test="${not empty error && empty productoAEditar}">
                    <div class="error-message" style="margin: 1rem auto; text-align: center; max-width: 800px; padding: 10px; border-radius: 5px;">
                        <c:out value="${error}" />
                    </div>
                </c:if>

                <div class="videogames" id="videogames-list">
                    <c:forEach var="prod" items="${listaProductos}">
                        <div class="videogame">
                            
                            <%-- CORRECCIÓN: Uso directo de Base64 eliminado la lógica condicional antigua --%>
                            <img class="game-photo" 
                                 src="data:image/jpeg;base64,${prod.imagenBase64}" 
                                 alt="<c:out value="${prod.nombreProducto}"/>">
                            
                            <div class="game-info">
                                <div class="game-title">
                                    <h2><c:out value="${prod.nombreProducto}"/></h2>

                                    <a href="ProductoServlet?action=editar&id=${prod.idProducto}#game-edit-modal">
                                        <img src="icons/edit.svg" alt="editar" class="icon-btn">
                                    </a>

                                    <form action="ProductoServlet" method="POST" class="form-delete">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <input type="hidden" name="idProducto" value="${prod.idProducto}">
                                        <button type="submit" class="boton">
                                            <img src="icons/trash.svg" alt="eliminar" class="icon-btn" />
                                        </button>
                                    </form>
                                </div>
                                <span><strong>Videojuego:</strong> <c:out value="${prod.nombreVideojuego}"/></span>
                                <span><strong>Plataforma:</strong> <c:out value="${prod.nombrePlataforma}"/></span>
                                <span><strong>Stock:</strong> <c:out value="${prod.stock}"/> unidades</span>
                                <span><strong>Precio:</strong> $<c:out value="${prod.precio}"/></span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>

        <c:if test="${not empty productoAEditar}">
            <div class="modal-overlay" id="game-edit-modal" style="display: flex;">
                <div class="modal-container">
                    <h2 id="modal-title-edit">Modificar Producto</h2>

                    <c:if test="${param.errorEditar == '1'}">
                        <div class="error-message">
                            No se pudo actualizar el producto. Verifica los datos e intenta de nuevo.
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="error-message">
                            <c:out value="${error}" />
                        </div>
                    </c:if>

                    <form class="modal-form" id="game-form-edit" action="ProductoServlet" method="POST" enctype="multipart/form-data">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="idProducto" value="${productoAEditar.idProducto}">

                        <div class="form-group full-width">
                            <label for="nombre-edit">Nombre del Producto</label>
                            <input type="text" id="nombre-edit" name="nombreProducto" value="${productoAEditar.nombreProducto}" required>
                        </div>

                        <div class="form-group full-width">
                            <label>Videojuego Base (No editable)</label>
                            <input type="text" value="<c:out value="${productoAEditar.nombreVideojuego}"/>" readonly style="background-color: #333; color: #aaa; cursor: not-allowed;">
                        </div>

                        <div class="form-group">
                            <label for="platform-edit">Plataforma</label>
                            <select id="platform-edit" name="idPlataforma" required>
                                <option value="" disabled>Selecciona...</option>
                                <c:forEach var="plat" items="${listaPlataformas}">
                                    <option value="${plat.idPlataforma}" ${productoAEditar.idPlataforma == plat.idPlataforma ? 'selected' : ''}>
                                        ${plat.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="stock-edit">Stock</label>
                            <input type="number" id="stock-edit" name="stock" min="0" value="${productoAEditar.stock}" required>
                        </div>

                        <div class="form-group">
                            <label for="price-edit">Precio ($)</label>
                            <input type="number" id="price-edit" name="precio" min="0" step="0.01" value="${productoAEditar.precio}" required>
                        </div>

                        <div class="form-group">
                            <label for="imagen-edit">Imagen (Dejar vacío para mantener la actual)</label>
                            <input type="file" id="imagen-edit" name="imagen" accept="image/png, image/jpeg, image/jpg" />
                        </div>

                        <div class="form-group full-width">
                            <label for="description-edit">Descripción</label>
                            <textarea id="description-edit" name="description" required><c:out value="${productoAEditar.descripcion}"/></textarea>
                        </div>

                        <div class="form-actions">
                            <a href="ProductoServlet" class="form-button cancel">Cancelar</a>
                            <button type="submit" class="form-button save">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>