<%-- 
    Document   : pedidos-pendientes
    Created on : 14 nov 2025, 1:38:30 a.m.
    Author     : moren
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Pedidos pendientes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/pedidos_pendientes.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
        <link rel="stylesheet" href="styles/crud-games.css"/>
        <link rel="stylesheet" href="styles/breadcrumb.css">
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
        <main class="container">
            <nav id="breadcrumb-nav" aria-label="breadcrumb">
                <ol class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="admin-options.jsp">Panel Admin</a></li>

                    <li class="breadcrumb-item active" aria-current="page">Pedidos</li>
                </ol>
            </nav>
            <h1>Pedidos</h1>
            <div class="search-div">
                <form action="consultarPedidos" method="GET" class="buscador">
                    <input type="text" 
                           name="busqueda"  
                           class="search" 
                           placeholder="Buscar por id de usuario o número de pedido" 
                           value="<c:out value='${param.busqueda}'/>" />

                    <div class="items-busqueda">   
                        <button type="submit" class="boton" >
                            <img src="icons/search.svg" alt="buscar" class="search-img"/>
                        </button>

                        <c:if test="${not empty param.busqueda}">
                            <a class="x-button" href="consultarPedidos">✖</a>
                        </c:if>
                    </div>
                </form>
            </div>
            <div class="card-form-container">
                <div class="header-data">
                </div>
                <div class="form-content">
                    <c:forEach var="pedido" items="${listaPedidos}">

                        <div class="payment-container">
                            <div class="payment-info">
                                <div class="pedido">
                                    <p class="pedido-texto">Pedido - </p>
                                    <p class="pedido">N°: <c:out value="${pedido.idPedido}" /></p>
                                    <div><a href="consultarDetallePedido?id=${pedido.idPedido}" class="detalle">Ver detalle</a></div>

                                </div>
                                <div class="pago-metodo">
                                    <p class="payment-method">
                                        Fecha del pedido:
                                        <fmt:formatDate value="${pedido.fechaHora}" 
                                                        pattern="d 'de' MMMM 'de' yyyy 'a las' h:mm a" 
                                                        timeZone="MST" 
                                                        type="both"
                                                        dateStyle="full" 
                                                        />
                                    </p>
                                    <p class="payment-method">Método de pago: <c:out value="${pedido.pago.metodoPago}" /></p>
                                </div>
                                <div class="pedido">
                                    <p class="payment-status">Estado de pago: </p>

                                    <a class="status">
                                        <c:choose>

                                            <c:when test="${pedido.estadoPedido == 'CANCELADO'}">
                                                <img src="icons/refund.svg" class="check" /> 
                                                <c:out value="REEMBOLSADO" />
                                            </c:when>

                                            <c:when test="${pedido.pago.estadoPago == 'REALIZADO'}">
                                                <img src="icons/check.svg" class="check" />
                                                <c:out value="REALIZADO" />
                                            </c:when>

                                            <c:otherwise>
                                                <img src="icons/pending.svg" class="check" />
                                                <c:out value="PENDIENTE" />
                                            </c:otherwise>

                                        </c:choose>
                                    </a>

                                    <p class="payment-status">Estado de Envío: </p>

                                    <a class="status">
                                        <img src="${pedido.estadoPedido == 'ENVIADO' ? 'icons/box.svg' : 'icons/pending.svg'}" class="check" />
                                        <c:out value="${pedido.estadoPedido}" />
                                    </a>
                                </div>
                            </div>
                            <c:if test="${pedido.estadoPedido != 'CANCELADO' && pedido.estadoPedido != 'ENTREGADO'}">
                                <div class="payment-aprrove-button">
                                    <c:if test="${pedido.estadoPedido != 'ENVIADO'}">
                                        <a href="actualizarPedido?id=${pedido.idPedido}&action=enviar" class="approve-btn">Marcar como Enviado</a>
                                    </c:if>
                                    <a href="actualizarPedido?id=${pedido.idPedido}&action=cancelar" 
                                       class="disapprove-btn" 
                                       onclick="return confirm('¿Estás seguro de que quieres cancelar el pedido N°: ${pedido.idPedido}?');">
                                        Cancelar pedido
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach> 
                </div>
            </div>
        </main>
        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>