<%-- 
    Document   : account
    Created on : 14 nov 2025, 1:28:14 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/account.css">
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
        <title>Mi Cuenta</title>

    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main>
            <div class="info-account">
                <h2>Cuenta de Fulanito</h2>

                <section id="bought-history">
                    <div class="section-title">
                        <img src="icons/cart.svg" alt="carrito">
                        <h3>Historial de Compras</h3>
                    </div>
                    <div class="edit-bg">
                        <a href="purchase-history.jsp"><button class="history-button">Ver historial de compras</button></a>
                    </div>
                </section>

                <section id="edit_account">
                    <div class="section-title">
                        <img src="icons/edit.svg" alt="edit">
                        <h3>Editar Cuenta</h3>
                    </div>
                    <div class="edit-bg">

                        <form name="edit_acc_form" action="">
                            <div class="contact-info">
                                <h3>Información de contacto</h3>
                                <hr>

                                <div class="two-column-wrapper">
                                    <div class="name-info">
                                        <label for="name">Nombre completo:</label>
                                        <input id="name" name="name" type="text" value="${sessionScope.usuario.nombre}">
                                    </div>
                                    <div class="phone-info">
                                        <label for="phone">Teléfono:</label>
                                        <input id="phone" name="phone" type="tel" value="${sessionScope.usuario.telefono}">
                                    </div>
                                </div>

                                <div class="mail-info">
                                    <label for="mail">Correo electrónico:</label>
                                    <input id="mail" name="mail" type="email" value="${sessionScope.usuario.correo}">
                                </div>
                            </div>

                            <div class="addresses">
                                <div class="addresses-title">
                                    <h3>Mis direcciones</h3>
                                    <a href="#add-address-modal" style="cursor: pointer; text-decoration: none;"><span>+</span></a>
                                </div>
                                <hr>

                                <c:choose>
                                    <c:when test="${not empty sessionScope.usuario.direcciones}">
                                        <c:forEach var="direccion" items="${sessionScope.usuario.direcciones}">
                                            <div class="address">
                                                <div class="address-info">
                                                    <span class="address-name">${direccion.nombre}</span>
                                                    <div class="two-column-wrapper">
                                                        <span>
                                                            <p>${direccion.calle} ${direccion.numeroExterior}</p>
                                                        </span>
                                                        <span>
                                                            <p>${direccion.colonia}</p>
                                                        </span>
                                                    </div>
                                                    <div class="two-column-wrapper">
                                                        <span>
                                                            <p>${direccion.ciudad}</p>
                                                        </span>
                                                        <span>
                                                            <p>${direccion.estado}, C.P. ${direccion.codigoPostal}</p>
                                                        </span>
                                                    </div>
                                                </div>
                                                <a href="#edit-address-modal" class="edit-icon">
                                                    <img src="icons/edit.svg" alt="editar">
                                                </a>
                                                <a href="EliminarDireccion?id=${direccion.idDireccion}" class="delete-icon" onclick="return confirm('¿Eliminar esta dirección?')">
                                                    <img src="icons/trash.svg" alt="eliminar">
                                                </a>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <p>No tienes direcciones guardadas. <a href="#add-address-modal">Agrega una</a></p>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="change-pswd">
                                <h3>Cambiar contraseña</h3>
                                <hr>

                                <div class="pswd">
                                    <label for="actual-pswd">Contraseña actual:</label>
                                    <input id="actual-pswd" name="actual-pswd" type="password">
                                </div>

                                <div class="pswd">
                                    <label for="new-pswd">Nueva contraseña:</label>
                                    <input id="new-pswd" name="new-pswd" type="password">
                                </div>
                            </div>

                            <hr>
                            <button type="submit">Guardar cambios</button>
                        </form>
                        <hr>
                        <form action="CerrarSesion" method="post">
                            <button type="submit" style="color: var(--red-error-text)">Cerrar sesión</button>
                        </form>
                    </div>
                </section>
            </div>
        </main>

        <!-- Modal para agregar dirección -->
        <div id="add-address-modal" class="modal-overlay">
            <div class="modal-content">
                <h3>Agregar Dirección</h3>

                <form action="">
                    <div class="modal-form-group full-width">
                        <label for="address-name">Nombre de la dirección</label>
                        <input type="text" id="address-name" name="address-name">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="street">Calle</label>
                            <input type="text" id="street" name="street">
                        </div>
                        <div class="modal-form-group">
                            <label for="ext-number">Número exterior</label>
                            <input type="text" id="ext-number" name="ext-number">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="colonia">Colonia</label>
                        <input type="text" id="colonia" name="colonia">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="postal-code">Código Postal</label>
                            <input type="text" id="postal-code" name="postal-code">
                        </div>
                        <div class="modal-form-group">
                            <label for="city">Ciudad</label>
                            <input type="text" id="city" name="city">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="state">Estado</label>
                        <input type="text" id="state" name="state">
                    </div>

                    <div class="modal-buttons">
                        <a href="#" class="modal-button cancel">Cancelar</a>
                        <button type="submit" class="modal-button save">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- Modal para editar dirección -->
        <div id="edit-address-modal" class="modal-overlay">
            <div class="modal-content">
                <h3>Editar Dirección</h3>

                <form action="">
                    <div class="modal-form-group full-width">
                        <label for="address-name">Nombre de la dirección</label>
                        <input type="text" id="address-name" name="address-name" value="Casa">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="street">Calle</label>
                            <input type="text" id="street" name="street" value="Calle Falsa 123">
                        </div>
                        <div class="modal-form-group">
                            <label for="ext-number">Número exterior</label>
                            <input type="text" id="ext-number" name="ext-number" value="123">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="colonia">Colonia</label>
                        <input type="text" id="colonia" name="colonia" value="Colonia Centro">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="postal-code">Código Postal</label>
                            <input type="text" id="postal-code" name="postal-code" value="12345">
                        </div>
                        <div class="modal-form-group">
                            <label for="city">Ciudad</label>
                            <input type="text" id="city" name="city" value="Ciudad Ejemplo">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="state">Estado</label>
                        <input type="text" id="state" name="state" value="SON">
                    </div>

                    <div class="modal-buttons">
                        <a href="#" class="modal-button cancel">Cancelar</a>
                        <button type="submit" class="modal-button save">Guardar</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>

    </body>

</html>