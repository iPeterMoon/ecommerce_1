<%-- 
    Document   : crud-products
    Created on : 14 nov 2025, 1:32:33 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Gestionar Productos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/admin.css">

    </head>

    <body class="tron-grid grid-container">
         <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
        <main>
            <h1>Gestionar Productos</h1>
            <div class="search-div">
                <input type="text" id="search" class="search" placeholder="Buscar...">
            </div>
            <div class="main-div">
                <div class="add-videogame">

                </div>
                <div class="videogames" id="videogames-list">
                    <div class="videogame">
                        <img class="game-photo" src="imgs/minecraft.png" alt="Minecraft">
                        <div class="game-info">
                            <div class="game-title">
                                <h2>Minecraft</h2>
                                <a href="#game-edit-modal">
                                    <img src="icons/edit.svg" alt="editar" class="icon-btn">
                                </a>
                                <a href="">
                                    <img src="icons/trash.svg" alt="eliminar" class="icon-btn">
                                </a>
                            </div>
                            <span><strong>Plataforma:</strong> PC</span>
                            <span><strong>Stock:</strong> 150 unidades</span>
                            <span><strong>Precio:</strong> $29.99</span>
                        </div>
                    </div>
                    <div class="videogame">
                        <img class="game-photo" src="imgs/minecraft.png" alt="Minecraft">
                        <div class="game-info">
                            <div class="game-title">
                                <h2>Minecraft</h2>
                                <a href="#game-modal">
                                    <img src="icons/edit.svg" alt="editar" class="icon-btn">
                                </a>
                                <a href="">
                                    <img src="icons/trash.svg" alt="eliminar" class="icon-btn">
                                </a>
                            </div>
                            <span><strong>Plataforma:</strong> PC</span>
                            <span><strong>Stock:</strong> 150 unidades</span>
                            <span><strong>Precio:</strong> $29.99</span>
                        </div>
                    </div>
                </div>

        </main>


        <div class="modal-overlay" id="game-edit-modal">
            <div class="modal-container">
                <h2 id="modal-title-edit">Modificar Producto</h2>
                <form class="modal-form" id="game-form-edit"> <input type="hidden" id="product-id-edit" name="id">

                    <div class="form-group full-width">
                        <label for="nombre-edit">Nombre</label>
                        <input type="text" id="nombre-edit" name="nombre" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="desarrollador-edit">Desarrollador</label>
                            <input type="text" id="desarrollador-edit" name="desarrollador" required>
                        </div>
                        <div class="form-group">
                            <label for="clasificacion-edit">Clasificación</label>
                            <input type="text" id="clasificacion-edit" name="clasificacion" required>
                        </div>
                    </div>

                    <div class="form-group full-width">
                        <label for="categoria-edit">Categoría</label>
                        <input type="text" id="categoria-edit" name="categoria" required>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="plataforma-edit">Plataforma</label>
                            <input type="text" id="plataforma-edit" name="plataforma" required>
                        </div>
                        <div class="form-group">
                            <label for="lanzamiento-edit">Año de lanzamiento</label>
                            <input type="text" id="lanzamiento-edit" name="lanzamiento" placeholder="YYYY" required>
                        </div>
                        <div class="form-group">
                            <label for="stock-edit">Stock</label>
                            <input type="number" id="stock-edit" name="stock" min="0" required>
                        </div>
                    </div>

                    <div class="form-actions">
                        <a href="#" class="form-button cancel">Cancelar</a>
                        <button type="submit" class="form-button save">Guardar</button>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>

    </body>

</html>
