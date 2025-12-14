<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Seleccionar método pago</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="styles/seleccionar_metodo_pago.css" />
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
  </head>
  <body class="tron-grid grid-container">
    <%@include file="WEB-INF/fragmentos/navbar.jspf" %>
    <main class="container">
      <h1 class="main-title">Seleccionar método de pago</h1>
      <div class="payment-method-container">
        <div class="header-methods">
          <p>Métodos de pago</p>
        </div>
        <div class="methods-content">
          <a href="introducir_datos_bancarios.jsp">
            <button class="method-button">Tarjeta</button>
          </a>
          <button class="method-button" onclick="createOrder(event, 'TRANSFERENCIA')">Transferencia</button>
          <button class="method-button" onclick="createOrder(event, 'CONTRAENTREGA')">Contra entrega</button>
        </div>
      </div>
    </main>

   <script>
      async function createOrder(event, method) {
        event.preventDefault();

        console.log("[DEBUG] Intentando crear orden vía: " + method);

        const token = localStorage.getItem("token");
        if (!token) {
          console.warn("[DEBUG] No hay token. Redirigiendo a login.");
          window.location.href = "login.jsp";
          return;
        }

        try {
          console.log("[DEBUG] Verificando token...");
          const verifyReq = await fetch("http://localhost:8080/API-Videojuegos/api/auth/verify", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ token: token }),
          });

          if (verifyReq.status === 401) {
            alert("Tu sesión ha expirado.");
            localStorage.removeItem("token");
            window.location.href = "login.jsp";
            return;
          }

          if (!verifyReq.ok) {
            throw new Error("Error de verificación de sesión");
          }

          console.log("[DEBUG] Token verificado. Enviando orden...");

          const response = await fetch("http://localhost:8080/API-Videojuegos/api/orders", {
            method: "POST",
            headers: {
              "Authorization": "Bearer " + token, 
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ metodoPago: method }),
          });

          if (response.ok) {
            const order = await response.json();
            console.log("[DEBUG] Orden creada. ID:", order.idPedido);
            window.location.href = "detalles_pedido.jsp?id=" + order.idPedido;
          } else {
            const text = await response.text();
            console.error("[DEBUG] Error creando orden:", text);
            alert("Error al crear el pedido: " + text);
          }
        } catch (e) {
          console.error("[DEBUG] Excepción:", e);
          alert("Error de conexión.");
        }
      }
    </script>
    <%@include file="WEB-INF/fragmentos/footer.jspf" %>
  </body>
</html>
