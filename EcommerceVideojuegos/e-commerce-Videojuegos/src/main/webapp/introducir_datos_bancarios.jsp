<%-- 
    Document   : introducir_datos_bancarios
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Introducir datos bancarios</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/introducir_datos_bancarios.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="styles/styles.css" />
    <link rel="icon" type="image/png" sizes="32x32" href="imgs/favicon-32x32.png">
</head>

<body class="tron-grid grid-container">
     <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
        <h1 class="main-title">Ingresar datos bancarios</h1>
        
        <div class="card-form-container" id="payment-form">
            <div class="header-data">
                <p>Datos bancarios</p>
            </div>
            <div class="form-content">
                <div class="form-row">
                    <div class="form-group large-width">
                        <label for="cardNumber">Número de tarjeta</label>
                        <input type="text" id="cardNumber" placeholder="0000 0000 0000 0000" maxlength="19">
                    </div>
                    <div class="form-group small-width">
                        <label for="cvv">CVV</label>
                        <input type="text" id="cvv" placeholder="123" maxlength="4">
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group large-width">
                        <label for="cardName">Nombre del titular</label>
                        <input type="text" id="cardName" placeholder="Como aparece en la tarjeta">
                    </div>
                    <div class="form-group small-width">
                        <label for="expiryDate">Fecha de vencimiento</label>
                        <input type="text" id="expiryDate" placeholder="MM/YY" maxlength="5">
                    </div>
                </div>
            </div>

            <div class="form-buttons-container">
                <a href="seleccionar_metodo_pago.jsp"><button class="cancel-button">Cancelar</button></a>
                
                <button class="confirm-button" onclick="processCardPayment(event)">Confirmar</button>
            </div>
        </div>
    </main>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>

    <script>
        async function processCardPayment(event) {
            event.preventDefault(); 

            const cardNum = document.getElementById("cardNumber").value;
            const cardCvv = document.getElementById("cvv").value;
            if(!cardNum || !cardCvv) {
                alert("Por favor completa los datos de la tarjeta.");
                return;
            }

            const token = localStorage.getItem("token");
            if (!token) {
                alert("Sesión no válida.");
                window.location.href = "login.jsp";
                return;
            }

            try {
                const response = await fetch("http://localhost:8080/API-Videojuegos/api/orders", {
                    method: "POST",
                    headers: {
                        "Authorization": "Bearer " + token,
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({ metodoPago: "TARJETA" })
                });

                if (response.ok) {
                    const order = await response.json();                    
                    window.location.href = "detalles_pedido.jsp?id=" + order.idPedido;
                } else {
                    const text = await response.text();
                    alert("Error al procesar el pago: " + text);
                }

            } catch (e) {
                alert("Error de conexión con el servidor.");
            }
        }
    </script>
</body>
</html>