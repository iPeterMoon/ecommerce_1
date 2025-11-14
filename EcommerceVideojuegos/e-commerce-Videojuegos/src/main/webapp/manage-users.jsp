<%-- 
    Document   : manage-users
    Created on : 14 nov 2025, 1:37:07 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Gestión de Usuarios</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/manage-users.css" />
  </head>
  <body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <ul class="users-information">
      <li class="user-header">
        <h2></h2>
        <h2>Nombre</h2>
        <h2>Estado</h2>
        <h2>Rol</h2>
      </li>

      <li class="user-row">
        <a href="#modal-fulanito">
          <img src="icons/avatar.svg" alt="avatar" />
        </a>
        <span>Fulanito de tal</span>
        <span class="status active">Activo</span>
        <span>Cliente</span>
      </li>

      <li class="user-row">
        <a href="">
          <img src="icons/avatar.svg" alt="avatar" />
        </a>

        <span>ElGamerMexican0</span>
        <span class="status inactive">Inactivo</span>
        <span>Cliente</span>
      </li>
    </ul>
    <div id="modal-fulanito" class="modal-overlay">
      <div class="modal-content">
        <a href="#" class="modal-close">&times;</a>
        <h3>Modificar estado del usuario:</h3>
        <div class="modal-actions">
          <button class="btn-modal active">Activo</button>
          <a href="#modal-inactivo">
            <button class="btn-modal inactive">Inactivo</button>
          </a>
        </div>
        <a href="#modal-eliminar">
          <button class="btn-modal delete">Eliminar</button>
        </a>
      </div>
    </div>
    <div id="modal-inactivo" class="modal-overlay">
      <div class="modal-content">
        <a href="#" class="modal-close">&times;</a>
        <h3>Confirmar el nuevo estado para:</h3>
        <div class="information-modal">
          <p>Fulanito de tal</p>
          <p class="status inactive">Inactivo</p>
        </div>
        <div class="modal-actions" id="status">
          <a href="manage-users.jsp"> <button class="btn-modal red">Cancelar</button> </a>
          <a href="manage-users.jsp"><button class="btn-modal green">Confirmar</button></a>
        </div>
      </div>
    </div>

    <div id="modal-eliminar" class="modal-overlay">
      <div class="modal-content">
        <a href="#" class="modal-close">&times;</a>
        <h3>Confirmar el nuevo estado para:</h3>
        <div class="information-modal">
          <p>Fulanito de tal</p>
        </div>
        <div class="modal-actions" id="status">
          <a href="manage-users.jsp"> <button class="btn-modal red">Cancelar</button> </a>
          <a href="manage-users.jsp"><button class="btn-modal green">Confirmar</button></a>
        </div>
      </div>
    </div>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
  </body>
</html>

