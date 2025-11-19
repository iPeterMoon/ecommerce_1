<%-- 
    Document   : admin-options
    Created on : 14 nov 2025, 1:29:32 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="styles/styles.css">
        <link rel="stylesheet" href="styles/admin.css">
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
        <title>Panel de Administrador</title>
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main>
            <div class="options">
                <a href="manage-users.jsp"><button>Gestionar usuarios</button></a>
                <a href="VideojuegoServlet"><button>Gestionar videojuegos</button></a>
                <a href="ProductoServlet"><button>Gestionar catálogo</button></a>
                <a href="consultarPedidos"><button>Gestionar pedidos</button></a>
                <a href="ResenaServlet"><button>Moderar reseñas</button></a>
            </div>
        </main>

        <%@include file="WEB-INF/fragmentos/footer.jspf" %>

    </body>

</html>
