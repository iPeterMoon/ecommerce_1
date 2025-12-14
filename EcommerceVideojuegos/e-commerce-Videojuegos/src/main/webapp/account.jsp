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
            <!-- Loader de carga -->
            <div id="loading-overlay" style="display: flex; justify-content: center; padding: 50px;">
                <h2>Cargando información...</h2>
            </div>

            <div class="info-account" id="account-content" style="display: none;">
                <h2>Cuenta de <span id="header-user-name">Usuario</span></h2>

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

                        <form id="profile-form" onsubmit="updateProfile(event)">
                            <!-- Mensajes de Feedback -->
                            <div id="profile-error" class="error-msg"></div>
                            <div id="profile-success" class="success-msg">Datos actualizados correctamente.</div>

                            <div class="contact-info">
                                <h3>Información de contacto</h3>
                                <hr>

                                <div class="two-column-wrapper">
                                    <div class="name-info">
                                        <label for="name">Nombre completo:</label>
                                        <input id="name" name="name" type="text" placeholder="Cargando...">
                                    </div>
                                    <div class="phone-info">
                                        <label for="phone">Teléfono:</label>
                                        <input id="phone" name="phone" type="tel" placeholder="Cargando...">
                                    </div>
                                </div>

                                <div class="mail-info">
                                    <label for="mail">Correo electrónico:</label>
                                    <input id="mail" name="mail" type="email" readonly style="background-color: #f0f0f0; color: #333; cursor: not-allowed;">
                                </div>
                            </div>

                            <div class="addresses">
                                <div class="addresses-title">
                                    <h3>Mis direcciones</h3>
                                    <a href="#" onclick="openAddAddressModal(event)" style="cursor: pointer; text-decoration: none;"><span>+</span></a>
                                </div>
                                <hr>

                                <div id="addresses-container">
                                    <p>Cargando direcciones...</p>
                                </div>
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
                        <form action="CerrarSesion" method="post" onsubmit="handleLogout(event)">
                            <button type="submit" style="color: var(--red-error-text)">Cerrar sesión</button>
                        </form>
                    </div>
                </section>
            </div>
        </main>

        <!-- Modal para agregar/editar dirección -->
        <div id="add-address-modal" class="modal-overlay">
            <div class="modal-content">
                <h3 id="modal-title">Agregar Dirección</h3>
                
                <form id="address-form" onsubmit="handleAddressSubmit(event)">
                    <input type="hidden" id="address-id" name="idDireccion">

                    <div id="address-error" class="error-msg"></div>

                    <div class="modal-form-group full-width">
                        <label for="address-name">Nombre de la dirección</label>
                        <input type="text" id="address-name" name="nombre">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="street">Calle</label>
                            <input type="text" id="street" name="calle">
                        </div>
                        <div class="modal-form-group">
                            <label for="ext-number">Número exterior</label>
                            <input type="text" id="ext-number" name="numeroExterior">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="colonia">Colonia</label>
                        <input type="text" id="colonia" name="colonia">
                    </div>

                    <div class="modal-form-row">
                        <div class="modal-form-group">
                            <label for="postal-code">Código Postal</label>
                            <input type="text" id="postal-code" name="codigoPostal">
                        </div>
                        <div class="modal-form-group">
                            <label for="city">Ciudad</label>
                            <input type="text" id="city" name="ciudad">
                        </div>
                    </div>

                    <div class="modal-form-group full-width">
                        <label for="state">Estado</label>
                        <input type="text" id="state" name="estado">
                    </div>

                    <div class="modal-buttons">
                        <a href="#" class="modal-button cancel" onclick="closeModal(event)">Cancelar</a>
                        <button type="submit" class="modal-button save">Guardar</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
        
        <script src="scripts/account.js"></script>
    </body>

</html>