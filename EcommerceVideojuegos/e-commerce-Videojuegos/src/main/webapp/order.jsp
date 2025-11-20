<%--
    Document   : order
    Created on : 14 nov 2025, 1:37:57 a.m.
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
        <title>Ver Pedido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/order.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
        <link rel="stylesheet" href="styles/breadcrumb.css">    
        <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
    </head>

    <body class="tron-grid grid-container">
        <%@include file="WEB-INF/fragmentos/navbar.jspf" %>

        <main class="container">

            <nav id="breadcrumb-nav" aria-label="breadcrumb">
                <ol class="breadcrumb-list">
                    <li class="breadcrumb-item"><a href="admin-options.jsp">Panel Admin</a></li>

                    <li class="breadcrumb-item"><a href="consultarPedidos">Pedidos</a></li>

                    <li class="breadcrumb-item active" aria-current="page">Detalle de pedido</li>

                </ol>
            </nav>
            <c:if test="${empty pedidoDetalle}">
                <h1 class="tittle">Error</h1>
                <p>El pedido que buscas no fue encontrado o no existe.</p>
                <a href="consultarPedidos">Volver a la lista</a>
            </c:if>

            <c:if test="${not empty pedidoDetalle}">
                <h1 class="tittle">Cuenta de <c:out value="${pedidoDetalle.usuario.nombre}"/></h1>
                <div class="header-container">
                    <img src="icons/cart.svg" class="cart-icon" />
                    <p class="details-paragraph">Detalles de pedido</p>
                    <p>-</p>
                    <p class="order-number">N°: <c:out value="${pedidoDetalle.idPedido}"/></p>
                    <p class="order-date">Fecha: 
                        <fmt:formatDate value="${pedidoDetalle.fechaHora}" pattern="dd MMM, yyyy hh:mm a" />
                    </p>
                </div>
                <div class="main-container">
                    <div class="order-information">
                        <div class="personal-information">
                            <div class="shipment-information">
                                <p><strong>Información del Cliente</strong></p>
                                <p>Nombre: <c:out value="${pedidoDetalle.usuario.nombre}"/></p>
                                <p>Correo: <c:out value="${pedidoDetalle.usuario.correo}"/></p>
                                <p>Calle Falsa 123, Colonia Centro</p> 
                            </div>
                            <div class="payment-information">
                                <p><strong>Resumen de Pago</strong></p>
                                <p>Método de Pago: <c:out value="${pedidoDetalle.pago.metodoPago}"/></p>
                                <p>Referencia:<c:out value="${pedidoDetalle.pago.referencia}"/></p>
                                <p>Estado: <c:out value="${pedidoDetalle.pago.estadoPago}"/></p>
                            </div>
                        </div>
                        <hr />

                        <div class="items-content">
                            <c:forEach var="item" items="${pedidoDetalle.items}">
                                <div class="product-row">
                                    <div class="product-info-left"> 

                                        <img src="data:image/jpeg;base64,${item.imagenBase64}" 
                                             alt="<c:out value='${item.nombreProducto}'/>" 
                                             style="width: 80px; height: 80px; object-fit: cover;" />

                                        <div class="tittle-quantity">
                                            <p><c:out value="${item.nombreProducto}"/></p>
                                            <p>Cantidad: <c:out value="${item.cantidad}"/></p>
                                        </div>
                                    </div>
                                    <p class="product-price">
                                        <fmt:formatNumber value="${item.precioUnitario}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/>
                                    </p>
                                </div>
                            </c:forEach>
                        </div>

                        <hr />
                        <div class="prices-status-order">
                            <div class="prices-subtittle">
                                <p>Subtotal</p>
                                <p>Envío</p>
                                <p><strong>Total Pagado</strong></p>
                            </div>
                            <div class="prices">                        
                                <div class="subtotal">
                                    <strong>
                                        <fmt:formatNumber value="${pedidoDetalle.pago.monto}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/>
                                    </strong>
                                </div>
                                <div class="shipment-price"> <fmt:formatNumber value="0.00" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/></div>
                                <div class="total-price">
                                    <strong>
                                        <fmt:formatNumber value="${pedidoDetalle.pago.monto}" type="currency" currencySymbol="$" minFractionDigits="2" maxFractionDigits="2"/>
                                    </strong>
                                </div>
                            </div>
                            <div class="order-status">
                                <c:choose>
                                    <c:when test="${pedidoDetalle.estadoPedido == 'ENVIADO'}">
                                        <img src="icons/box.svg" />
                                    </c:when>
                                    <c:when test="${pedidoDetalle.estadoPedido == 'ENTREGADO'}">
                                        <img src="icons/check.svg" />
                                    </c:when>
                                    <c:when test="${pedidoDetalle.estadoPedido == 'CANCELADO'}">
                                        <img src="icons/refund.svg" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="icons/pending.svg" />
                                    </c:otherwise>
                                </c:choose>
                                <p class="status"><c:out value="${pedidoDetalle.estadoPedido}"/></p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </main>
        <%@include file="WEB-INF/fragmentos/footer.jspf" %>
    </body>
</html>