document.addEventListener("DOMContentLoaded", loadPurchaseHistory);

async function loadPurchaseHistory() {
    const container = document.querySelector(".edit-bg.purchase-history");

    // Validar existencia del contenedor
    if (!container) return;

    try {
        const token = localStorage.getItem("token");

        if (!token) {
            container.innerHTML =
                '<p style="text-align:center; padding:20px;">Inicia sesión para ver tus compras.</p>';
            return;
        }

        // 1. Verificación previa del token (siguiendo patrón de cart.js)
        const verify = await fetch(
            "http://localhost:8080/API-Videojuegos/api/auth/verify",
            {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    token: token
                })
            }
        );

        if (!verify.ok) {
            const data = await verify.json();
            // Si el token no es válido, redirigimos o mostramos error
            if (verify.status === 401) {
                alert("Tu sesión ha expirado.");
                window.location.href = "login.jsp";
            } else {
                alert(data.error);
            }
            return;
        }

        // Indicador de carga
        container.innerHTML =
            '<p style="text-align:center; padding:20px;">Cargando historial...</p>';

        // 2. Petición principal para obtener pedidos
        const response = await fetch(
            "http://localhost:8080/API-Videojuegos/api/mis-pedidos",
            {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json"
                }
            }
        );

        if (!response.ok) {
            const data = await response.json();
            console.log(data.error);
            throw new Error("Error al obtener el historial de pedidos");
        }

        const pedidos = await response.json();
        renderHistory(pedidos, container);
    } catch (error) {
        console.error("Error:", error);
        container.innerHTML = `<p style="text-align:center; color:red; padding:20px;">Ocurrió un error al cargar tus compras.</p>`;
    }
}

function renderHistory(pedidos, container) {
    if (pedidos.length === 0) {
        container.innerHTML =
            '<p style="text-align:center; padding:20px;">No has realizado ninguna compra aún.</p>';
        return;
    }

    container.innerHTML = ""; // Limpiar loader

    // Invertir orden para ver los más recientes primero
    pedidos.reverse();

    pedidos.forEach((pedido) => {
        // Calcular total sumando items
        let total = 0;
        if (pedido.items) {
            total = pedido.items.reduce((sum, item) => sum + item.subtotal, 0);
        }

        const totalFormatted = total.toLocaleString("es-MX", {
            style: "currency",
            currency: "MXN",
        });

        // Lógica de estado visual
        let statusClass = "sent";
        let statusIcon = "box.svg";
        let statusText = pedido.estadoPedido;

        if (["ENTREGADO", "COMPLETADO"].includes(pedido.estadoPedido)) {
            statusClass = "delivered";
            statusIcon = "check.svg";
        } else if (pedido.estadoPedido === "CANCELADO") {
            statusClass = "cancelled";
            statusIcon = "x-button.svg";
        }

        // Generar HTML de items
        let itemsHtml = "";
        if (pedido.items) {
            pedido.items.forEach((item) => {
                const precioItem = item.precioUnitario.toLocaleString("es-MX", {
                    style: "currency",
                    currency: "MXN",
                });
                // Manejo de imagen base64
                const img = item.imagenBase64 || "imgs/placeholder.png";

                itemsHtml += `
                    <div class="purchase-item">
                        <img src="${img}" alt="${item.nombreProducto}" style="width: 60px; height: 80px; object-fit: cover; border-radius: 4px;">
                        <div class="purchase-details">
                            <p>${item.nombreProducto}</p>
                            <p>Cantidad: ${item.cantidad}</p>
                        </div>
                        <p class="cost">${precioItem}</p>
                    </div>
                `;
            });
        }

        const pedidoCard = document.createElement("div");
        pedidoCard.className = "purchase";
        pedidoCard.innerHTML = `
             <div class="purchase-header">
                <p>Pedido N°: ${pedido.idPedido}</p>
                <p>Total: <strong>${totalFormatted}</strong></p>
             </div>
             <div class="purchase-info">
                ${itemsHtml}
             </div>
             <div class="purchase-footer">
                <div class="delivery-state ${statusClass}">
                    <img src="icons/${statusIcon}" alt="status">
                    <p>${statusText}</p>
                </div>
                <a href="order.jsp?id=${pedido.idPedido}">Ver detalles</a>
             </div>
        `;

        container.appendChild(pedidoCard);
    });
}
