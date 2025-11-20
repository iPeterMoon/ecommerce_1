<%--
    Document   : crud-games
    Created on : 14 nov 2025, 1:31:19 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Gestionar Videojuegos</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="styles/styles.css" />
        <link rel="stylesheet" href="styles/admin.css" />
        <link rel="stylesheet" href="styles/crud-games.css"/>
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main>
            <h1>Gestionar Videojuegos</h1>

            <div class="search-div">
                <form action="VideojuegoServlet" method="GET" class="buscador">
                    <input type="text" name="busqueda" id="search" class="search" 
                           placeholder="Buscar videojuego..." 
                           value="<c:out value='${param.busqueda}'/>" />

                    <div class="items-busqueda">   
                        <button type="submit" class="boton">
                            <img src="icons/search.svg" alt="buscar" class="search-img"/>
                        </button>
                        <c:if test="${not empty param.busqueda}">
                            <a class="x-button" href="VideojuegoServlet">✖</a>
                        </c:if>
                    </div>
                </form>
            </div>

            <c:if test="${param.errorEliminar == '1'}">
                <div class="error-message">
                    No se puede eliminar un videojuego con un producto asociado.
                </div>
            </c:if>

            <c:if test="${param.exito == '1'}">
                <div class="success-message" style="text-align:center; color: green; margin: 1rem; border: 1px solid green; padding: 10px;">
                    Producto agregado exitosamente.
                </div>
            </c:if>

            <div class="main-div">
                <div class="add-videogame">
                    <a href="#game-modal"><img src="icons/+.svg" alt="add" /></a>
                </div>

                <c:if test="${not empty error && empty videojuegoAEditar && empty abrirModalAgregar}">
                    <div class="error-message" style="margin: 1rem auto;">
                        <c:out value="${error}" />
                    </div>
                </c:if>

                <div class="videogames">
                    <c:forEach var="vj" items="${listaVideojuegos}">
                        <div class="videogame">
                            <div class="game-info">
                                <div class="game-title">
                                    <h2><c:out value="${vj.nombre}" /></h2>

                                    <a href="VideojuegoServlet?id=${vj.idVideojuego}">
                                        <img src="icons/edit.svg" alt="editar" class="icon-btn" />
                                    </a>

                                    <form action="VideojuegoServlet" method="POST" class="form-delete">
                                        <input type="hidden" name="_method" value="DELETE">
                                        <input type="hidden" name="idVideojuego" value="${vj.idVideojuego}">
                                        <button type="submit" class="boton">
                                            <img src="icons/trash.svg" alt="eliminar" class="icon-btn" />
                                        </button>
                                    </form>

                                    <a href="#game-modal-producto-${vj.idVideojuego}">
                                        <img src="icons/+.svg" alt="Añadir Producto" class="icon-btn" />
                                    </a>
                                </div>
                                <span><strong>Desarrollador: </strong> <c:out value="${vj.desarrollador}" /></span>
                                <span><strong>Clasificación: </strong> <c:out value="${vj.nombreClasificacion}" /></span>
                                <span><strong>Categorías: </strong> <c:out value="${vj.nombresCategorias}" /></span>
                                <span><strong>Año de lanzamiento: </strong> <c:out value="${vj.anoLanzamiento}" /></span>
                            </div>
                        </div>

                        <div class="modal-overlay" id="game-modal-producto-${vj.idVideojuego}">
                            <div class="modal-container">
                                <h2 id="modal-title">Añadir Producto para: <c:out value="${vj.nombre}"/></h2>
                                <form class="modal-form" action="ProductoServlet" method="POST" enctype="multipart/form-data">
                                    <input type="hidden" name="idVideojuego" value="${vj.idVideojuego}" />
                                    <div class="form-group full-width">
                                        <label>Videojuego Base</label>
                                        <input type="text" value="<c:out value="${vj.nombre}"/>" readonly />
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Nombre del Producto</label>
                                        <input type="text" name="nombreProducto" value="<c:out value="${vj.nombre}"/>" required />
                                    </div>
                                    <div class="form-group">
                                        <label>Plataforma</label>
                                        <select name="idPlataforma" required>
                                            <option value="" disabled selected>Selecciona...</option>
                                            <c:forEach var="plat" items="${listaPlataformas}">
                                                <option value="${plat.idPlataforma}">${plat.nombre}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Stock</label>
                                        <input type="number" name="stock" min="0" required />
                                    </div>
                                    <div class="form-group">
                                        <label>Precio ($)</label>
                                        <input type="number" name="precio" min="0" step="0.01" required />
                                    </div>
                                    <div class="form-group">
                                        <label>Imagen</label>
                                        <input type="file" name="imagen" accept="image/png, image/jpeg, image/jpg" required />
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Descripción</label>
                                        <textarea name="description"></textarea>
                                    </div>
                                    <div class="form-actions">
                                        <a href="#" class="form-button cancel">Cancelar</a>
                                        <button type="submit" class="form-button save">Guardar Producto</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>          
                </div>
            </div>
        </main>

        <div class="modal-overlay" id="game-modal" style="${not empty abrirModalAgregar ? 'display: flex;' : ''}">
            <div class="modal-container">
                <h2>Agregar Videojuego</h2>

                <c:if test="${not empty abrirModalAgregar && not empty error}">
                    <div class="error-message">
                        <c:out value="${error}"/>
                    </div>
                </c:if>
                <c:if test="${param.errorCrear == '1'}">
                    <div class="error-message">Ya existe un videojuego con ese nombre.</div>
                </c:if>

                <form class="modal-form" action="VideojuegoServlet" method="POST">
                    <div class="form-group full-width">
                        <label for="game-name">Nombre</label>
                        <input type="text" id="game-name" name="game-name" required/>
                    </div>

                    <div class="form-group">
                        <label for="developer">Desarrollador</label>
                        <input type="text" id="developer" name="developer" required/>
                    </div>

                    <div class="form-group">
                        <label for="classification">Clasificación</label>
                        <select id="classification" name="classification">
                            <option value="" selected >-- Sin clasificación --</option>
                            <c:forEach var="clas" items="${listaClasificaciones}">
                                <option value="${clas.idClasificacion}">${clas.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group full-width">
                        <label>Categorías</label>
                        <div class="checkbox-container">
                            <c:forEach var="cat" items="${listaCategorias}">
                                <div class="checkbox-item">
                                    <input type="checkbox" 
                                           name="category" 
                                           value="${cat.idCategoria}" 
                                           id="cat-new-${cat.idCategoria}" 
                                           class="input-checkbox-style">

                                    <label for="cat-new-${cat.idCategoria}" style="cursor: pointer;">
                                        ${cat.nombre}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="release-year">Año de lanzamiento</label>
                        <input type="text" 
                               id="release-year" 
                               name="release-year" 
                               placeholder="Ej: 2025" 
                               maxlength="4" 
                               pattern="[0-9]*" 
                               inputmode="numeric" 
                               title="Ingresa un año válido de 4 números"
                               required/>
                    </div>

                    <div class="form-actions">
                        <a href="VideojuegoServlet" class="form-button cancel">Cancelar</a>
                        <button type="submit" class="form-button save">Guardar</button>
                    </div>
                </form>
            </div>
        </div>

        <c:if test="${not empty videojuegoAEditar}">
            <div class="modal-overlay" id="edit-game-modal" style="display: flex;">
                <div class="modal-container">
                    <h2>Modificar Videojuego</h2>

                    <c:if test="${not empty error}">
                        <div class="error-message">
                            <c:out value="${error}" />
                        </div>
                    </c:if>
                    <c:if test="${param.errorEditar == '1'}">
                        <div class="error-message">
                            No se pudo actualizar. Verifique que el nombre no esté duplicado.
                        </div>
                    </c:if>

                    <form class="modal-form" action="VideojuegoServlet" method="POST">
                        <input type="hidden" name="_method" value="PUT">
                        <input type="hidden" name="idVideojuego" value="${videojuegoAEditar.idVideojuego}">

                        <div class="form-group full-width">
                            <label for="game-name-edit">Nombre</label>
                            <input type="text" id="game-name-edit" name="game-name" value="${videojuegoAEditar.nombre}" required/>
                        </div>

                        <div class="form-group">
                            <label for="developer-edit">Desarrollador</label>
                            <input type="text" id="developer-edit" name="developer" value="${videojuegoAEditar.desarrollador}"/>
                        </div>

                        <div class="form-group">
                            <label for="classification-edit">Clasificación</label>
                            <select id="classification-edit" name="classification">
                                <option value="">-- Sin clasificación --</option>
                                <c:forEach var="clas" items="${listaClasificaciones}">
                                    <option value="${clas.idClasificacion}" 
                                            ${videojuegoAEditar.idClasificacion == clas.idClasificacion ? 'selected' : ''}>
                                        ${clas.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group full-width">
                            <label>Categorías</label>
                            <div class="checkbox-container">
                                <c:forEach var="cat" items="${listaCategorias}">
                                    <c:set var="isSelected" value="false" />
                                    <c:forEach var="idSel" items="${videojuegoAEditar.idsCategorias}">
                                        <c:if test="${idSel == cat.idCategoria}">
                                            <c:set var="isSelected" value="true" />
                                        </c:if>
                                    </c:forEach>

                                    <div class="checkbox-item">
                                        <input type="checkbox" 
                                               name="category" 
                                               value="${cat.idCategoria}" 
                                               id="cat-edit-${cat.idCategoria}" 
                                               class="input-checkbox-style" 
                                               ${isSelected ? 'checked' : ''}>

                                        <label for="cat-edit-${cat.idCategoria}" style="cursor: pointer;">
                                            ${cat.nombre}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="release-year-edit">Año de lanzamiento</label>
                            <input type="text" 
                                   id="release-year-edit" 
                                   name="release-year" 
                                   placeholder="Ej: 2025" 
                                   value="${videojuegoAEditar.anoLanzamiento}" 
                                   maxlength="4" 
                                   pattern="[0-9]*" 
                                   required />
                        </div>

                        <div class="form-actions">
                            <a href="VideojuegoServlet" class="form-button cancel">Cancelar</a>
                            <button type="submit" class="form-button save">Guardar Cambios</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>