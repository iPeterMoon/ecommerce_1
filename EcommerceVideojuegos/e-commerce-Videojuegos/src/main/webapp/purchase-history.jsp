<%-- 
    Document   : purchase-history
    Created on : 14 nov 2025, 1:39:16 a.m.
    Author     : benja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="styles/styles.css">
    <link rel="stylesheet" href="styles/account.css">
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">

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
                <div class="edit-bg purchase-history">
                    <div class="purchase">
                         <div class="purchase-header">
                            <p>Pedido N°: 10524</p>
                            <p>Total: <strong>$2,498.00</strong></p>
                         </div>
                         <div class="purchase-info">
                            <div class="purchase-item">
                                <img src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp" alt="gta6">
                                <div class="purchase-details">
                                    <p>Halo Infinite</p>
                                    <p>Cantidad: 1</p>
                                </div>
                                <p class="cost">$1,4999</p>
                            </div>
                            <div class="purchase-item">
                                <img src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp" alt="gta6">
                                <div class="purchase-details">
                                    <p>Grand Theft Auto 6</p>
                                    <p>Cantidad: 1</p>
                                </div>
                                <p class="cost">$999.00</p>
                            </div>
                         </div>
                         <div class="purchase-footer">
                            <div class="delivery-state delivered">
                                <img src="icons/check.svg" alt="check">
                                <p>Entregado</p>
                            </div>
                            <a href="order.jsp">Ver detalles</a>
                         </div>
                    </div>
                    <div class="purchase">
                         <div class="purchase-header">
                            <p>Pedido N°: 10524</p>
                            <p>Total: <strong>$2,498.00</strong></p>
                         </div>
                         <div class="purchase-info">
                            <div class="purchase-item">
                                <img src="https://juegosdigitalesmexico.mx/files/images/productos/1746638096-grand-theft-auto-vi-xbox-series-xs-pre-orden-0.webp" alt="gta6">
                                <div class="purchase-details">
                                    <p>Halo Infinite</p>
                                    <p>Cantidad: 1</p>
                                </div>
                                <p class="cost">$1,4999</p>
                            </div>
                         </div>
                         <div class="purchase-footer">
                            <div class="delivery-state sent">
                                <img src="icons/box.svg" alt="box">
                                <p>Enviado</p>
                            </div>
                            <a href="order.jsp">Ver detalles</a>
                         </div>
                    </div>
                </div>
        </div>
    </main>
      <%@include file="WEB-INF/fragmentos/footer.jspf" %>
</body>

</html>