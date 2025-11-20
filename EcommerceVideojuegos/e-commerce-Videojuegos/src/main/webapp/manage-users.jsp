<%-- 
    Document   : manage-users
    Created on : 14 nov 2025, 1:37:07 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Gestión de Usuarios</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/manage-users.css" />
        <link rel="stylesheet" href="styles/breadcrumb.css">
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    </head>
    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
        <div class="container">

            <nav id="breadcrumb-nav" aria-label="breadcrumb">
                <ol class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="admin-options.jsp">Panel Admin</a></li>

                    <li class="breadcrumb-item active" aria-current="page">Gestionar Usuarios</li>
                </ol>
            </nav>
        </div>

        <ul class="users-information">
            <li class="user-header">
                <h2></h2>
                <h2>Nombre</h2>
                <h2>Email</h2>
                <h2>Estado</h2>
                <h2>Rol</h2>
            </li>

            <c:forEach var="usuario" items="${listaUsuarios}">
                <c:if test="${usuario.rol != 'ADMIN'}">
                    <c:set var="modalId" value="modal-${usuario.idUsuario}" />
                    <c:set var="estadoClase" value="${usuario.cuentaActiva ? 'active' : 'inactive'}" />
                    <c:set var="estadoTexto" value="${usuario.cuentaActiva ? 'Activo' : 'Inactivo'}" />
                    <a href="#${modalId}">
                        <li class="user-row">
                            <img src="icons/avatar.svg" alt="avatar" />
                            <span>${usuario.nombre}</span>
                            <span>${usuario.correo}</span>
                            <span class="status ${estadoClase}">${estadoTexto}</span>
                            <span>${usuario.rol}</span>
                        </li>
                    </a>
                </c:if>
            </c:forEach>
        </ul>
        <c:forEach var="usuario" items="${listaUsuarios}">
            <c:if test="${usuario.rol != 'ADMIN'}">
                <c:set var="modalId" value="modal-${usuario.idUsuario}" />
                <c:set var="modalInactivoId" value="modal-inactivo-${usuario.idUsuario}" />
                <c:set var="modalActivoId" value="modal-activo-${usuario.idUsuario}" />
                <c:set var="modalEliminarId" value="modal-eliminar-${usuario.idUsuario}" />
                <c:set var="estadoClase" value="${usuario.cuentaActiva ? 'active' : 'inactive'}" />
                <c:set var="estadoTexto" value="${usuario.cuentaActiva ? 'Activo' : 'Inactivo'}" />
                
                <div id="${modalId}" class="modal-overlay">
                    <div class="modal-content">
                        <a href="#" class="modal-close">&times;</a>
                        <h3>Modificar estado del usuario:</h3>
                        <div class="modal-actions">
                            <c:choose>
                                <c:when test="${usuario.cuentaActiva}">
                                    <button class="btn-modal active" disabled>Activo</button>
                                    <a href="#${modalInactivoId}">
                                        <button class="btn-modal inactive">Inactivo</button>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#${modalActivoId}">
                                        <button class="btn-modal active">Activo</button>
                                    </a>
                                    <button class="btn-modal inactive" disabled>Inactivo</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a href="#${modalEliminarId}">
                            <button class="btn-modal delete">Eliminar</button>
                        </a>
                    </div>
                </div>

                <div id="${modalInactivoId}" class="modal-overlay">
                    <div class="modal-content">
                        <a href="#" class="modal-close">&times;</a>
                        <h3>Confirmar el nuevo estado para:</h3>
                        <div class="information-modal">
                            <p>${usuario.nombre}</p>
                            <p class="status inactive">Inactivo</p>
                        </div>
                        <div class="modal-actions" id="status">
                            <a href="#${modalId}"> <button class="btn-modal red">Cancelar</button> </a>
                            <form method="POST" action="ActualizarEstadoUsuario" style="display:inline;">
                                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                                <input type="hidden" name="nuevoEstado" value="false" />
                                <button type="submit" class="btn-modal green">Confirmar</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="${modalActivoId}" class="modal-overlay">
                    <div class="modal-content">
                        <a href="#" class="modal-close">&times;</a>
                        <h3>Confirmar el nuevo estado para:</h3>
                        <div class="information-modal">
                            <p>${usuario.nombre}</p>
                            <p class="status active">Activo</p>
                        </div>
                        <div class="modal-actions" id="status">
                            <a href="#${modalId}"> <button class="btn-modal red">Cancelar</button> </a>
                            <form method="POST" action="ActualizarEstadoUsuario" style="display:inline;">
                                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                                <input type="hidden" name="nuevoEstado" value="true" />
                                <button type="submit" class="btn-modal green">Confirmar</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div id="${modalEliminarId}" class="modal-overlay">
                    <div class="modal-content">
                        <a href="#" class="modal-close">&times;</a>
                        <h3>Confirmar eliminación de usuario:</h3>
                        <div class="information-modal">
                            <p>${usuario.nombre}</p>
                            <p>${usuario.correo}</p>
                        </div>
                        <div class="modal-actions" id="status">
                            <a href="#${modalId}"> <button class="btn-modal red">Cancelar</button> </a>
                            <form method="POST" action="EliminarUsuario" style="display:inline;">
                                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
                                <button type="submit" class="btn-modal green">Confirmar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>

